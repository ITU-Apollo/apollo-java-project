package com.apollo.merger.repository.snippet;

import com.apollo.merger.model.snippets.MarkdownSnippets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkdownSnippetsRepository extends BaseSnippetRepository<MarkdownSnippets> {

}
