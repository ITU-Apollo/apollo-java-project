package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.JavascriptSnippets;
import com.apollo.mongoapi.repo.JavascriptSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JavascriptSnippetsService extends BaseOperationsService<JavascriptSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        JavascriptSnippets javascriptSnippets = convertFromApolloRequest(request, new JavascriptSnippets());
        return repository.save(javascriptSnippets);
    }
}
