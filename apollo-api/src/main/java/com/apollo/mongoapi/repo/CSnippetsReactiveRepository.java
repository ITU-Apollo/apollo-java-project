package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.CSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface CSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<CSnippets> {

}