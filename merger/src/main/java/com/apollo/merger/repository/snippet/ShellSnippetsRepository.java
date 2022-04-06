package com.apollo.merger.repository.snippet;

import com.apollo.merger.model.snippets.ShellSnippets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShellSnippetsRepository extends BaseSnippetRepository<ShellSnippets> {

}
