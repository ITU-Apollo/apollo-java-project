package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.MarkdownSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkdownSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<MarkdownSnippets> {

}