package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.JavascriptSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface JavascriptSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<JavascriptSnippets> {

}