package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.ShellSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface ShellSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<ShellSnippets> {

}