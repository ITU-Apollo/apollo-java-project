package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.JavaSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface JavaSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<JavaSnippets> {

}