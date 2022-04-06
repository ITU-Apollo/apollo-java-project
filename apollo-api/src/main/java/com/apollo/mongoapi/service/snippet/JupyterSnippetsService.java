package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.JupyterSnippets;
import com.apollo.mongoapi.repo.JupyterSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JupyterSnippetsService extends BaseOperationsService<JupyterSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        JupyterSnippets jupyterSnippets = convertFromApolloRequest(request, new JupyterSnippets());
        return repository.save(jupyterSnippets);
    }
}
