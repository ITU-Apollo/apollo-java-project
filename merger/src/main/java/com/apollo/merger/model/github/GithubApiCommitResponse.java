package com.apollo.merger.model.github;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class GithubApiCommitResponse implements Serializable {

    private CommitResponse commit;
    private AuthorResponse author;

}
