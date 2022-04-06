package com.apollo.merger.repository.snippet;

import com.apollo.merger.model.snippets.YamlSnippets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YamlSnippetsRepository extends BaseSnippetRepository<YamlSnippets> {

}
