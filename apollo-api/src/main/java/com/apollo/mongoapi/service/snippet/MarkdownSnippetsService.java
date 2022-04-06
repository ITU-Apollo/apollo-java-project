package com.apollo.mongoapi.service.snippet;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.MarkdownSnippets;
import com.apollo.mongoapi.repo.MarkdownSnippetsReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MarkdownSnippetsService extends BaseOperationsService<MarkdownSnippetsReactiveRepository> {

    public Mono create(ApolloRequest request) {
        boolean exists = existsByRequest(request);

        if (exists)
            return Mono.just("null");

        MarkdownSnippets markdownSnippets = convertFromApolloRequest(request, new MarkdownSnippets());
        return repository.save(markdownSnippets);
    }
}
