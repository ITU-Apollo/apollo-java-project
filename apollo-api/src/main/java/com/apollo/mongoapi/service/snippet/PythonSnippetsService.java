package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.PythonSnippets;
import com.apollo.mongoapi.repo.PythonSnippetsReactiveRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PythonSnippetsService extends BaseOperationsService<PythonSnippetsReactiveRepository> {

    private final Gson gson;

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        PythonSnippets pythonSnippets = convertFromApolloRequest(request, new PythonSnippets());
        return repository.save(pythonSnippets);
    }
}
