package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.RubySnippets;
import com.apollo.mongoapi.repo.RubySnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RubySnippetsService extends BaseOperationsService<RubySnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        RubySnippets rubySnippets = convertFromApolloRequest(request, new RubySnippets());
        return repository.save(rubySnippets);
    }
}
