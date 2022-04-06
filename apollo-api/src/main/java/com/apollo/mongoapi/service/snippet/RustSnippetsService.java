package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.RustSnippets;
import com.apollo.mongoapi.repo.RustSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RustSnippetsService extends BaseOperationsService<RustSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        RustSnippets rustSnippets = convertFromApolloRequest(request, new RustSnippets());
        return repository.save(rustSnippets);
    }
}
