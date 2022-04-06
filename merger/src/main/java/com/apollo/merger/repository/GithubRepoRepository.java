package com.apollo.merger.repository;

import com.apollo.merger.model.github.GithubRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubRepoRepository extends MongoRepository<GithubRepository, String> {


}
