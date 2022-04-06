package com.apollo.merger.model.github;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RepositoryOwner implements Serializable {
    private String login;
    private String type;

}
