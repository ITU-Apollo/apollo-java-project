package com.apollo.mongoapi.model.snippets;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document("Go")
public class GoSnippets extends Snippets implements Serializable {

    @Id
    private String id;


}