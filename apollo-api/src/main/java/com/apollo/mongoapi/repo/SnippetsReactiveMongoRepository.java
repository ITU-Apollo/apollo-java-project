package com.apollo.mongoapi.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SnippetsReactiveMongoRepository<T> extends ReactiveMongoRepository<T, String> {

    boolean existsByFileNameAndLineNumberAndCommitHash(String fileName, String lineNumber, String commitHash);
}
