package com.apollo.merger.service;

import com.apollo.merger.model.github.GithubCommitFile;
import com.apollo.merger.model.snippets.Snippets;
import com.apollo.merger.repository.FileRepository;
import com.apollo.merger.repository.snippet.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommitFileService {

    private final FileRepository fileRepository;
    private final PythonSnippetsRepository pythonSnippetsRepository;
    private final CSnippetsRepository cSnippetsRepository;
    private final CppSnippetsRepository cppSnippetsRepository;
    private final GoSnippetsRepository goSnippetsRepository;
    private final JavaSnippetsRepository javaSnippetsRepository;
    private final JavascriptSnippetsRepository javascriptSnippetsRepository;
    private final JsonSnippetsRepository jsonSnippetsRepository;
    private final JupyterSnippetsRepository jupyterSnippetsRepository;
    private final MarkdownSnippetsRepository markdownSnippetsRepository;
    private final RubySnippetsRepository rubySnippetsRepository;
    private final RustSnippetsRepository rustSnippetsRepository;
    private final ShellSnippetsRepository shellSnippetsRepository;
    private final YamlSnippetsRepository yamlSnippetsRepository;

    public List<String> getFileNamesByIdList(List<String> idList) {
        Iterable<GithubCommitFile> files = fileRepository.findAllById(idList);
        List<String> fileNames = new ArrayList<>();
        files.forEach(e -> fileNames.add(e.getFileName()));
        return fileNames;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean recreateFileWithId(String anomalyId) {
        Optional<GithubCommitFile> optional = fileRepository.findById(anomalyId);

        if (optional.isEmpty())
            return false;

        return recreateFile(optional.get().getFileName(), optional.get().getLanguage(), anomalyId);
    }

    public boolean recreateFileFromFileName(String fileName) {
        boolean exists = fileRepository.existsByFileName(fileName);

        if (exists)
            return false;

        String[] splitFileName = fileName.split("\\.");
        String extension = splitFileName[splitFileName.length-1];

        return recreateFile(fileName, extension, null);
    }

    private boolean recreateFile(String fileName, String language, String fileId) {
        BaseSnippetRepository repository = getRepository(language);

        if (repository == null)
            return false;

        List<? extends Snippets> snippets = repository.findAllByFileName(fileName);

        Map<Object, ? extends List<? extends Snippets>> grouped = snippets.stream()
                .collect(Collectors.groupingBy(e -> Long.valueOf(e.getLineNumber())));

        TreeMap<Long, List<? extends Snippets>> treeMap = new TreeMap(grouped);


        GithubCommitFile file = new GithubCommitFile();
        file.setFileName(fileName);
        file.setLanguage(language);

        List<String> orderLine = new ArrayList<>();

        for (Map.Entry<Long, List<? extends Snippets>> entry : treeMap.entrySet()) {
            file.setContent(file.getContent().concat(entry.getValue().get(0).getSnippet()));
            orderLine.add(String.valueOf(entry.getValue().get(0).getLineNumber()));
        }

        file.setOrderLine(StringUtils.join(orderLine, ","));

        if (fileId != null)
            fileRepository.deleteById(fileId);

        fileRepository.save(file);

        return true;
    }

    private BaseSnippetRepository getRepository(String language) {
        return switch (language.toLowerCase().trim()) {
            case "python", "py" -> pythonSnippetsRepository;
            case "c", "h" -> cSnippetsRepository;
            case "c++", "cc" -> cppSnippetsRepository;
            case "go" -> goSnippetsRepository;
            case "java" -> javaSnippetsRepository;
            case "javascript", "js" -> javascriptSnippetsRepository;
            case "json" -> jsonSnippetsRepository;
            case "jupyter", "ipynb" -> jupyterSnippetsRepository;
            case "markdown", "md" -> markdownSnippetsRepository;
            case "ruby", "rb" -> rubySnippetsRepository;
            case "rust", "rs" -> rustSnippetsRepository;
            case "shell", "sh" -> shellSnippetsRepository;
            case "yaml", "yml" -> yamlSnippetsRepository;
            default -> null;
        };
    }
}
