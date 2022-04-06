package com.apollo.merger.model.github;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("CommitFile")
public class GithubCommitFile {
    @Id
    private String id;
    private String language;
    private String fileName;
    private String content = Strings.EMPTY;
    private String orderLine;
    private Boolean valid = false;
}
