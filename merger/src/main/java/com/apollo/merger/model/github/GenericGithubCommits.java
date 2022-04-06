package com.apollo.merger.model.github;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document("Commits")
public class GenericGithubCommits {
    @Id
    private String id;
    private String language;
    private String repoUrl;
    private String license;
    private String commitHash;
    private Set<String> files;
    private String authorName;
    private String authorEmail;
    private String commitDate;
    private String authorAvatarUrl;
    private String authorFollowersUrl;
    private Set<String> otherLanguages;
    private boolean hasRepo = false;
}
