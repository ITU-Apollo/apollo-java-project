package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.UndefinedSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface UndefinedSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<UndefinedSnippets> {

}