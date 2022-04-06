package com.apollo.mongoapi.service;

import com.apollo.mongoapi.constant.KafkaConstants;
import com.apollo.mongoapi.constant.LanguagesConstants;
import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.model.snippets.PythonSnippets;
import com.apollo.mongoapi.service.kafka.KafKaProducerService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApolloService {

    private final Gson gson;
    private final KafKaProducerService kafKaProducerService;

    public Mono<PythonSnippets> sendCreateRequestToKafka(ApolloRequest apolloRequest) {
        log.info("current language is : {}", apolloRequest.getLanguage());
        String json = gson.toJson(apolloRequest);
        String topicName = switch (apolloRequest.getLanguage().toLowerCase().trim()) {
            case LanguagesConstants.C -> KafkaConstants.C_TOPIC_NAME;
            case LanguagesConstants.CPP -> KafkaConstants.CPP_TOPIC_NAME;
            case LanguagesConstants.GOLANG -> KafkaConstants.GOLANG_TOPIC_NAME;
            case LanguagesConstants.JSON -> KafkaConstants.JSON_TOPIC_NAME;
            case LanguagesConstants.JAVA -> KafkaConstants.JAVA_TOPIC_NAME;
            case LanguagesConstants.JAVASCRIPT -> KafkaConstants.JAVASCRIPT_TOPIC_NAME;
            case LanguagesConstants.JUPYTER -> KafkaConstants.JUPYTER_TOPIC_NAME;
            case LanguagesConstants.MARKDOWN -> KafkaConstants.MARKDOWN_TOPIC_NAME;
            case LanguagesConstants.PYTHON -> KafkaConstants.PYTHON_TOPIC_NAME;
            case LanguagesConstants.RUBY -> KafkaConstants.RUBY_TOPIC_NAME;
            case LanguagesConstants.RUST -> KafkaConstants.RUST_TOPIC_NAME;
            case LanguagesConstants.SHELL -> KafkaConstants.SHELL_TOPIC_NAME;
            case LanguagesConstants.YAML -> KafkaConstants.YAML_TOPIC_NAME;
            default -> KafkaConstants.UNDEFINED_TOPIC_NAME;
        };
        log.info("going to -> {}", topicName);
        kafKaProducerService.sendMessage(json, topicName);
        return Mono.just(new PythonSnippets());
    }
}
