package com.apollo.mongoapi.model.snippets;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Snippets implements Serializable {

    String snippet;
    String language;
    String fileName;
    String repoUrl;
    String license;
    String commitHash;
    String lineNumber;
    String chunk;


}
