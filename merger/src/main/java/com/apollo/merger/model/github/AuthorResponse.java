package com.apollo.merger.model.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorResponse {
    private String name;
    private String email;
    private String date;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("followers_url")
    private String followersUrl;
}