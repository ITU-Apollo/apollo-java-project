package com.apollo.merger.service;

import com.apollo.merger.model.github.GenericGithubCommits;
import com.apollo.merger.repository.CommitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommitService {

    private final CommitRepository commitRepository;
    private final CommitFileService commitFileService;


    public boolean recreateFileFromSnippets(String anomalyId) {
        Optional<GenericGithubCommits> commit = commitRepository.findById(anomalyId);

        if (commit.isEmpty())
            return false;

        Set<String> files = commit.get().getFiles();

        if (CollectionUtils.isEmpty(files))
            return false;

        for (String fileName : files)
            commitFileService.recreateFileFromFileName(fileName);

        return true;

    }
}
