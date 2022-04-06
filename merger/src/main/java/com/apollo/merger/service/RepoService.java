package com.apollo.merger.service;

import com.apollo.merger.model.github.*;
import com.apollo.merger.repository.CommitRepository;
import com.apollo.merger.repository.GithubRepoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepoService {

    private final GithubRepoRepository repoRepository;
    private final CommitRepository commitRepository;
    private final GithubRequestService requestService;

    public String createRepos() {
        GithubRepository exampleRepository = new GithubRepository();
        Pageable pageable = PageRequest.of(0, 10);
        boolean flag = true;

        while (flag) {
            Page<GenericGithubCommits> page = commitRepository.findAll(pageable);
            for (GenericGithubCommits commit : page.getContent()) {
                exampleRepository.setRepoUrl(commit.getRepoUrl());
                List<GithubRepository> repositories = repoRepository.findAll(Example.of(exampleRepository));

                if (CollectionUtils.isNotEmpty(repositories))
                    continue;

                GithubApiRepositoryResponse response = requestService.getRepositoryApiResponse(commit.getRepoUrl());

                GithubRepository githubRepository = new GithubRepository();
                githubRepository.setRepoUrl(commit.getRepoUrl());
                githubRepository.setFullName(response != null ? response.getFullName() : "");
                githubRepository.setDescription(response != null ? response.getDescription() : "");
                githubRepository.setCreatedAt(response != null ? response.getCreatedAt() : "");
                githubRepository.setUpdatedAt(response != null ? response.getUpdatedAt() : "");
                githubRepository.setPushedAt(response != null ? response.getPushedAt() : "");
                githubRepository.setLanguage(response != null ? response.getLanguage() : "");
                githubRepository.setVisibilityStatus(response != null ? response.getVisibilityStatus() : "");

                githubRepository.setSize(response != null ? response.getSize() : 0);
                githubRepository.setStargazersCount(response != null ? response.getStargazersCount() : 0);
                githubRepository.setWatchersCount(response != null ? response.getWatchersCount() : 0);
                githubRepository.setForksCount(response != null ? response.getForksCount() : 0);
                githubRepository.setOpenIssuesCount(response != null ? response.getOpenIssuesCount() : 0);
                githubRepository.setNetworkCount(response != null ? response.getNetworkCount() : 0);
                githubRepository.setSubscribersCount(response != null ? response.getSubscribersCount() : 0);

                githubRepository.setForkStatus(response != null && response.isForkStatus());
                githubRepository.setAllowForkingStatus(response != null && response.isAllowForkingStatus());
                githubRepository.setTemplateStatus(response != null && response.isTemplateStatus());
                githubRepository.setPrivateStatus(response != null && response.isPrivateStatus());

                githubRepository.setTopics(response != null ? response.getTopics() : new ArrayList<>());
                githubRepository.setCommits(new TreeSet<>(Collections.singleton(commit.getCommitHash())));

                if (response != null) {
                    RepositoryOwner owner = response.getOwner();
                    githubRepository.setOwnerLogin(owner != null ? owner.getLogin() : "");
                    githubRepository.setOwnerType(owner != null ? owner.getType() : "");

                    License license = response.getLicense();
                    githubRepository.setLicenseName(license != null ? license.getName() : commit.getLicense());
                    githubRepository.setLicenseUrl(license != null ? license.getUrl() : "");
                } else {
                    githubRepository.setOwnerLogin("");
                    githubRepository.setOwnerType("");
                    githubRepository.setLicenseName(commit.getLicense());
                    githubRepository.setLicenseUrl("");
                }

                repoRepository.save(githubRepository);
            }

            if (page.hasNext())
                pageable = page.nextPageable();
            else
                flag = false;
        }

        return "created";
    }
}
