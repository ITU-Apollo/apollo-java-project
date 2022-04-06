package com.apollo.merger.model.snippets;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document("Rust")
public class RustSnippets extends Snippets implements Serializable {

    @Id
    private String id;


}
