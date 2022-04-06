package com.apollo.merger.model.github;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.TreeSet;

@Document("Repositories")
@Getter
@Setter
public class GithubRepository {

    @Id
    private String id;

    private String repoUrl;
    private String fullName;
    private String ownerLogin;
    private String ownerType;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String pushedAt;
    private String licenseName;
    private String licenseUrl;
    private String language;
    private String visibilityStatus;
    private Long size;
    private Long stargazersCount;
    private Long watchersCount;
    private Long forksCount;
    private Long openIssuesCount;
    private Long networkCount;
    private Long subscribersCount;
    private Boolean forkStatus;
    private Boolean allowForkingStatus;
    private Boolean templateStatus;
    private Boolean privateStatus;
    private List<String> topics;
    private TreeSet<String> commits;



}
