package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.RubySnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface RubySnippetsReactiveRepository extends SnippetsReactiveMongoRepository<RubySnippets> {

}