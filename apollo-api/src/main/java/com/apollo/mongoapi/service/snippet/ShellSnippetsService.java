package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.ShellSnippets;
import com.apollo.mongoapi.repo.ShellSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ShellSnippetsService extends BaseOperationsService<ShellSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        ShellSnippets shellSnippets = convertFromApolloRequest(request, new ShellSnippets());
        return repository.save(shellSnippets);
    }
}
