package com.apollo.mongoapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ApolloRequest implements Serializable {

    private String id;
    private String snippet;
    private String language;
    @JsonProperty("repo_file_name")
    private String fileName;
    @JsonProperty("github_repo_url")
    private String repoUrl;
    private String license;
    @JsonProperty("commit_hash")
    private String commitHash;
    @JsonProperty("starting_line_number")
    private String lineNumber;
    @JsonProperty("chunk_size")
    private String chunk;


}
