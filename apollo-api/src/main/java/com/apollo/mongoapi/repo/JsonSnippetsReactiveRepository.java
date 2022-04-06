package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.JsonSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<JsonSnippets> {

}