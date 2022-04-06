package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.RustSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface RustSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<RustSnippets> {

}