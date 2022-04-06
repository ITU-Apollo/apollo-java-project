package com.apollo.merger.repository.snippet;

import com.apollo.merger.model.snippets.CSnippets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSnippetsRepository extends BaseSnippetRepository<CSnippets> {

}
