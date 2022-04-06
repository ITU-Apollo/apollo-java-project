package com.apollo.merger.model.github;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommitResponse {
    private AuthorResponse author;
    private AuthorResponse committer;
    private String message;

}