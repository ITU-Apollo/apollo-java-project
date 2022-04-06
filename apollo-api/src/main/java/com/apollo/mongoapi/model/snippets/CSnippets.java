package com.apollo.mongoapi.model.snippets;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("C")
public class CSnippets extends Snippets {

    @Id
    private String id;


}
