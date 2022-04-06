package com.apollo.merger.repository.snippet;

import com.apollo.merger.model.snippets.UndefinedSnippets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UndefinedSnippetsRepository extends BaseSnippetRepository<UndefinedSnippets> {

}
