package com.apollo.merger.repository;

import com.apollo.merger.model.github.GithubCommitFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FileRepository extends MongoRepository<GithubCommitFile, String> {

    Optional<GithubCommitFile> findByFileName(String fileName);

    Page<GithubCommitFile> findAllByValidNot (Pageable pageable, boolean isValid);

    Page<GithubCommitFile> findAllByValidIsFalseOrValidIsNull (Pageable pageable);

    List<GithubCommitFile> findAllByFileNameIn(Set<String> fileNames);

    boolean existsByFileName(String fileName);
}
