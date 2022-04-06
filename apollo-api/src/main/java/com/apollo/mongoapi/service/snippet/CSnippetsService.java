package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.CSnippets;
import com.apollo.mongoapi.repo.CSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CSnippetsService extends BaseOperationsService<CSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        CSnippets cSnippets = convertFromApolloRequest(request, new CSnippets());
        return repository.save(cSnippets);
    }


}
