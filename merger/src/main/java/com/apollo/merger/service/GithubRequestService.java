package com.apollo.merger.service;

import com.apollo.merger.model.github.GithubApiCommitResponse;
import com.apollo.merger.model.github.GithubApiRepositoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubRequestService {

    @Value("${github.authentication}")
    private final String AUTH_TOKEN;

    private RestTemplate restTemplate = new RestTemplate();

    public GithubApiCommitResponse getCommitApiResponse (String commitHash, String repoUrl) {
        String requestUrl = createCommitRequestUrl(commitHash, repoUrl);
        HttpEntity entity = getHttpEntity();
        try {
            ResponseEntity<GithubApiCommitResponse> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, GithubApiCommitResponse.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public GithubApiRepositoryResponse getRepositoryApiResponse (String repoUrl) {
        String requestUrl = createRepositoryRequestUrl(repoUrl);
        HttpEntity entity = getHttpEntity();
        try {
            ResponseEntity<GithubApiRepositoryResponse> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, GithubApiRepositoryResponse.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private String createCommitRequestUrl(String commitHash, String repoUrl) {
        String requestUrl = createRepositoryRequestUrl(repoUrl);
        return requestUrl.concat("/commits/").concat(commitHash.replace("\n", ""));
    }

    private String createRepositoryRequestUrl(String repoUrl) {
        return repoUrl.replace("github.com", "api.github.com/repos");
    }

    private HttpEntity getHttpEntity () {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", AUTH_TOKEN);

        return new HttpEntity(null, httpHeaders);
    }
}
