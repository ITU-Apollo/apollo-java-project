package com.apollo.merger.model.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GithubApiRepositoryResponse implements Serializable {
    @JsonProperty("full_name")
    private String fullName;
    private RepositoryOwner owner;
    private String description;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("pushed_at")
    private String pushedAt;
    private License license;
    private String language;
    @JsonProperty("visibility")
    private String visibilityStatus;
    private long size;
    @JsonProperty("stargazers_count")
    private long stargazersCount;
    @JsonProperty("watchers_count")
    private long watchersCount;
    @JsonProperty("forks_count")
    private long forksCount;
    @JsonProperty("open_issues_count")
    private long openIssuesCount;
    @JsonProperty("network_count")
    private long networkCount;
    @JsonProperty("subscribers_count")
    private long subscribersCount;
    @JsonProperty("fork")
    private boolean forkStatus;
    @JsonProperty("allow_forking")
    private boolean allowForkingStatus;
    @JsonProperty("is_template")
    private boolean templateStatus;
    @JsonProperty("private")
    private boolean privateStatus;
    private List<String> topics;

}
