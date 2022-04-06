package com.apollo.merger.repository;

import com.apollo.merger.model.github.GenericGithubCommits;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommitRepository extends MongoRepository<GenericGithubCommits, String> {

    Optional<GenericGithubCommits> findByCommitHash(String commitHash);

}
