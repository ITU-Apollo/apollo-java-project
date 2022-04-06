package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.CppSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface CppSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<CppSnippets> {

}