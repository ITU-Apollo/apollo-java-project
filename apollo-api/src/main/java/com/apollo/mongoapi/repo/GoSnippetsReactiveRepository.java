package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.GoSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface GoSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<GoSnippets> {

}