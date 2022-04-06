package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.YamlSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface YamlSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<YamlSnippets> {

}