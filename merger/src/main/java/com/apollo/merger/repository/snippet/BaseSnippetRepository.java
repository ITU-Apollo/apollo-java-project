package com.apollo.merger.repository.snippet;

import com.apollo.merger.model.snippets.Snippets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseSnippetRepository<T extends Snippets> extends MongoRepository<T, String> {

    List<T> findAllByFileName(String fileName);
}
