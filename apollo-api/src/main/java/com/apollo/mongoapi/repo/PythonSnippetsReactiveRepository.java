package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.PythonSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface PythonSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<PythonSnippets> {


}