package com.apollo.merger.controller;

import com.apollo.merger.service.RepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apollo/github/repos")
@RequiredArgsConstructor
public class GithubRepoController {

    private final RepoService repoService;

    @PostMapping("/create")
    public ResponseEntity<String> createRepos() {
        return ResponseEntity.ok(repoService.createRepos());
    }
}
