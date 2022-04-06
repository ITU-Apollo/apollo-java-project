package com.apollo.merger.service;

import com.apollo.merger.model.github.*;
import com.apollo.merger.model.snippets.Snippets;
import com.apollo.merger.repository.*;
import com.apollo.merger.repository.snippet.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MergeService {

    private final GithubRequestService githubRequestService;
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
    private final UndefinedSnippetsRepository undefinedSnippetsRepository;
    private final CommitRepository commitRepository;
    private final FileRepository fileRepository;

    public void mergeCommitsByLanguage (String language) {
        MongoRepository repository = getRepository(language);
        mergeCommitsByLanguage(repository);
    }

    public <T extends Snippets> T mergeCommitsByLanguage(MongoRepository repository) {
        Pageable pageable = PageRequest.of(0, 1000, Sort.by("commitHash", "fileName"));
        boolean flag = true;

        while (flag) {
            Page<T> page = repository.findAll(pageable);
            log.info("Page: {} , Total Pages: {}", pageable.getPageNumber(), page.getTotalPages());
            List<T> snippets = page.getContent();

            Map<String, List<T>> groupedByCommitHash = snippets.stream()
                    .collect(Collectors.groupingBy(T::getCommitHash));

            for (Map.Entry<String, List<T>> entry : groupedByCommitHash.entrySet()) {
                String commitHash = entry.getKey().replace("\n", "");

                T snippet = entry.getValue().stream().findFirst().orElse(null);
                if (snippet != null) {
                    GenericGithubCommits githubCommit = initGithubCommit(commitHash, snippet);

                    Set<String> files = CollectionUtils.isNotEmpty(githubCommit.getFiles())
                            ? githubCommit.getFiles()
                            : new HashSet<>();

                    Map<String, List<T>> groupedByFileName = entry.getValue().stream()
                            .collect(Collectors.groupingBy(T::getFileName));

                    for (Map.Entry<String, List<T>> entry1 : groupedByFileName.entrySet()) {
                        initCommitFile(entry1, files);
                    }

                    githubCommit.setFiles(files);

                    commitRepository.save(githubCommit);
                }
            }

            if (page.hasNext())
                pageable = page.nextPageable();
            else
                flag = false;
        }
        log.info("Merge Finished for {}", repository.getClass().getSimpleName());
        return null;
    }

    private GenericGithubCommits initGithubCommit (String commitHash, Snippets snippet) {
        log.info("CommitHash is : {}", commitHash);
        Optional<GenericGithubCommits> commit = commitRepository.findByCommitHash(commitHash);
        GenericGithubCommits githubCommit = null;

        if (commit.isEmpty()) {
            githubCommit = new GenericGithubCommits();
            githubCommit.setLanguage(snippet.getLanguage());
            githubCommit.setCommitHash(commitHash);
            githubCommit.setRepoUrl(snippet.getRepoUrl());
            githubCommit.setLicense(snippet.getLicense());

            GithubApiCommitResponse response = githubRequestService.getCommitApiResponse(commitHash, snippet.getRepoUrl());

            if (response != null) {
                CommitResponse responseCommit = response.getCommit();
                AuthorResponse responseAuthor = response.getAuthor();
                AuthorResponse commitAuthor = responseCommit != null ? responseCommit.getAuthor() : new AuthorResponse();
                AuthorResponse commitCommitter = responseCommit != null ? responseCommit.getCommitter() : new AuthorResponse();
                githubCommit.setCommitDate(commitAuthor.getDate() != null ? commitAuthor.getDate() : commitCommitter.getDate());
                githubCommit.setAuthorEmail(commitAuthor.getEmail() != null ? commitAuthor.getEmail() : commitCommitter.getEmail());
                githubCommit.setAuthorName(commitAuthor.getName() != null ? commitAuthor.getName() : commitCommitter.getName());
                githubCommit.setAuthorAvatarUrl(responseAuthor != null ? responseAuthor.getAvatarUrl() : "");
                githubCommit.setAuthorFollowersUrl(responseAuthor != null ? responseAuthor.getFollowersUrl() : "");
            }
        } else {
            githubCommit = commit.get();
            if (CollectionUtils.isEmpty(githubCommit.getOtherLanguages()))
                githubCommit.setOtherLanguages(new HashSet<>());
            githubCommit.getOtherLanguages().add(snippet.getLanguage());
        }

        return githubCommit;
    }

    private <T extends Snippets> T initCommitFile (Map.Entry<String, List<T>> entry, Set<String> files) {
        String fileName = entry.getKey();
        String language = entry.getValue().stream().findFirst().get().getLanguage();
        List<String> orderLine;
        final GithubCommitFile file;
        Optional<String> optional = files.stream()
                .filter(e -> e.equals(fileName))
                .findFirst();

        if (optional.isEmpty()) {
            file = createEmptyFile(fileName, language);
        } else {
            Optional<GithubCommitFile> opt = fileRepository.findByFileName(fileName);
            if (opt.isPresent())
                file = opt.get();
            else
                file = createEmptyFile(fileName, language);
        }
        orderLine = (file.getOrderLine() != null)
                ? Arrays.stream(file.getOrderLine().split(",")).collect(Collectors.toList())
                : new ArrayList<>();

        entry.getValue().stream().forEachOrdered(e -> {
            file.setContent(file.getContent().concat(e.getSnippet()));
            orderLine.add(String.valueOf(e.getLineNumber()));
        });

        file.setOrderLine(StringUtils.join(orderLine, ","));
        fileRepository.save(file);
        files.add(file.getFileName());

        return null;
    }

    private MongoRepository getRepository(String language) {
        return switch (language.toLowerCase().trim()) {
            case "python" -> pythonSnippetsRepository;
            case "c" -> cSnippetsRepository;
            case "c++" -> cppSnippetsRepository;
            case "go" -> goSnippetsRepository;
            case "java" -> javaSnippetsRepository;
            case "javascript" -> javascriptSnippetsRepository;
            case "json" -> jsonSnippetsRepository;
            case "jupyter" -> jupyterSnippetsRepository;
            case "markdown" -> markdownSnippetsRepository;
            case "ruby" -> rubySnippetsRepository;
            case "rust" -> rustSnippetsRepository;
            case "shell" -> shellSnippetsRepository;
            case "yaml" -> yamlSnippetsRepository;
            default -> throw new RuntimeException("Undefined Language!");
        };
    }

    public void cleanCommitFileLists() {
        List<GenericGithubCommits> commits = commitRepository.findAll();
        for (GenericGithubCommits commit : commits) {
            Set<String> files = commit.getFiles().stream().filter(e -> !e.contains(".yml") && !e.contains(".yaml")).collect(Collectors.toSet());
            if (commit.getFiles().size() != files.size()) {
                commit.setFiles(files);
                commitRepository.save(commit);
            }
        }
    }

    private GithubCommitFile createEmptyFile (String fileName, String language) {
        GithubCommitFile file = new GithubCommitFile();
        file.setFileName(fileName);
        file.setLanguage(language);

        return file;
    }
}
