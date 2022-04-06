package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.Snippets;
import com.apollo.mongoapi.repo.SnippetsReactiveMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public abstract class BaseOperationsService<U extends SnippetsReactiveMongoRepository> {

    U repository;

    @Autowired
    public void setRepository(U repository) {
        this.repository = repository;
    }

    public <T extends Snippets> T convertFromApolloRequest (ApolloRequest request, T child) {
        child.setChunk(request.getChunk());
        child.setCommitHash(request.getCommitHash());
        child.setFileName(request.getFileName());
        child.setLanguage(request.getLanguage());
        child.setLicense(request.getLicense());
        child.setSnippet(request.getSnippet());
        child.setLineNumber(request.getLineNumber());
        child.setRepoUrl(request.getRepoUrl());

        return child;
    }

    public boolean existsByRequest (ApolloRequest request) {
        return repository.existsByFileNameAndLineNumberAndCommitHash(request.getFileName(), request.getLineNumber(), request.getCommitHash());
    }
}
