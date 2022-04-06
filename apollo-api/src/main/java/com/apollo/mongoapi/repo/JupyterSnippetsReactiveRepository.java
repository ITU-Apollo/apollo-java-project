package com.apollo.mongoapi.repo;

import com.apollo.mongoapi.model.snippets.JupyterSnippets;
import org.springframework.stereotype.Repository;

@Repository
public interface JupyterSnippetsReactiveRepository extends SnippetsReactiveMongoRepository<JupyterSnippets> {

}