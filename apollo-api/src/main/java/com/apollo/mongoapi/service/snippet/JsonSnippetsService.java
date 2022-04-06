package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.JsonSnippets;
import com.apollo.mongoapi.repo.JsonSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JsonSnippetsService extends BaseOperationsService<JsonSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        JsonSnippets jsonSnippets = convertFromApolloRequest(request, new JsonSnippets());
        return repository.save(jsonSnippets);
    }
}
