package com.apollo.merger.controller;

import com.apollo.merger.service.MergeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/apollo/github/commits")
public class GithubCommitController {

    private final MergeService mergeService;

    @PostMapping("/merge/{language}")
    @ResponseStatus(HttpStatus.OK)
    public void createApollo (@PathVariable("language") String language) {
         mergeService.mergeCommitsByLanguage(language);
    }
}
