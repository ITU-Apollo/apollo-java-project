package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.YamlSnippets;
import com.apollo.mongoapi.repo.YamlSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class YamlSnippetsService extends BaseOperationsService<YamlSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        YamlSnippets yamlSnippets = convertFromApolloRequest(request, new YamlSnippets());
        return repository.save(yamlSnippets);
    }
}
