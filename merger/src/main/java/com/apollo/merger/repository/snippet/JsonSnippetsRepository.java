package com.apollo.merger.repository.snippet;

import com.apollo.merger.model.snippets.JsonSnippets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonSnippetsRepository extends BaseSnippetRepository<JsonSnippets> {

}
