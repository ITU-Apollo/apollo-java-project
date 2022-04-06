package com.apollo.mongoapi.controller;

import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.service.ApolloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/apollo")
public class ApolloController {

    private final ApolloService apolloService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Mono createApollo (@RequestBody ApolloRequest request) {
    //    return Mono.just("This endpoint finished its job and now enjoys his vacation in void :)");
        return apolloService.sendCreateRequestToKafka(request);
    //    return ServerResponse.ok().build();
    }

    @GetMapping("/get/{language}/{commit_hash}")
    @ResponseStatus(HttpStatus.OK)
    public Flux getSnippetsByLanguageAndCommitHash(@PathVariable String language, @PathVariable("commit_hash") String commitHash) {
        return Flux.just("Currently Under Maintenance");
    }
}
