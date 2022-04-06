package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.GoSnippets;
import com.apollo.mongoapi.repo.GoSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GoSnippetsService extends BaseOperationsService<GoSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        GoSnippets goSnippets = convertFromApolloRequest(request, new GoSnippets());
        return repository.save(goSnippets);
    }
}
