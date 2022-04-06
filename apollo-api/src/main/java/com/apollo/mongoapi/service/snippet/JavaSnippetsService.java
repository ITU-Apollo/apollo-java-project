package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.JavaSnippets;
import com.apollo.mongoapi.repo.JavaSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JavaSnippetsService extends BaseOperationsService<JavaSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        JavaSnippets javaSnippets = convertFromApolloRequest(request, new JavaSnippets());
        return repository.save(javaSnippets);
    }
}
