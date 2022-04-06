package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.CppSnippets;
import com.apollo.mongoapi.repo.CppSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CppSnippetsService extends BaseOperationsService<CppSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        CppSnippets cppSnippets = convertFromApolloRequest(request, new CppSnippets());
        return repository.save(cppSnippets);
    }
}
