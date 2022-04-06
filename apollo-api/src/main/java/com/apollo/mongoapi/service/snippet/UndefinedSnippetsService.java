package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.UndefinedSnippets;
import com.apollo.mongoapi.repo.UndefinedSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UndefinedSnippetsService extends BaseOperationsService<UndefinedSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        UndefinedSnippets undefinedSnippets = convertFromApolloRequest(request, new UndefinedSnippets());
        return repository.save(undefinedSnippets);
    }
}
