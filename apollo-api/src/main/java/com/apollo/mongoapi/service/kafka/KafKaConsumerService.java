package com.apollo.mongoapi.service.kafka;

import com.apollo.mongoapi.constant.KafkaConstants;
import com.apollo.mongoapi.model.ApolloRequest;
import com.apollo.mongoapi.service.snippet.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafKaConsumerService {

    private final PythonSnippetsService pythonSnippetsService;
    private final CSnippetsService cSnippetsService;
    private final CppSnippetsService cppSnippetsService;
    private final GoSnippetsService goSnippetsService;
    private final JavaSnippetsService javaSnippetsService;
    private final JavascriptSnippetsService javascriptSnippetsService;
    private final JsonSnippetsService jsonSnippetsService;
    private final JupyterSnippetsService jupyterSnippetsService;
    private final MarkdownSnippetsService markdownSnippetsService;
    private final RubySnippetsService rubySnippetsService;
    private final RustSnippetsService rustSnippetsService;
    private final ShellSnippetsService shellSnippetsService;
    private final YamlSnippetsService yamlSnippetsService;
    private final UndefinedSnippetsService undefinedSnippetsService;
    private final Gson gson;

    @KafkaListener(topics = KafkaConstants.PYTHON_TOPIC_NAME,
            groupId = KafkaConstants.PYTHON_GROUP_NAME)
    public void consumePython(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = pythonSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.CPP_TOPIC_NAME,
            groupId = KafkaConstants.CPP_GROUP_NAME)
    public void consumeCpp(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = cppSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.C_TOPIC_NAME,
            groupId = KafkaConstants.C_GROUP_NAME)
    public void consumeC(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = cSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.GOLANG_TOPIC_NAME,
            groupId = KafkaConstants.GOLANG_GROUP_NAME)
    public void consumeGolang(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = goSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.JSON_TOPIC_NAME,
            groupId = KafkaConstants.JSON_GROUP_NAME)
    public void consumeJson(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = jsonSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.JAVA_TOPIC_NAME,
            groupId = KafkaConstants.JAVA_GROUP_NAME)
    public void consumeJava(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = javaSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.JAVASCRIPT_TOPIC_NAME,
            groupId = KafkaConstants.JAVASCRIPT_GROUP_NAME)
    public void consumeJavascript(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = javascriptSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.JUPYTER_TOPIC_NAME,
            groupId = KafkaConstants.JUPYTER_GROUP_NAME)
    public void consumeJupyter(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = jupyterSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.MARKDOWN_TOPIC_NAME,
            groupId = KafkaConstants.MARKDOWN_GROUP_NAME)
    public void consumeMarkdown(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = markdownSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.RUBY_TOPIC_NAME,
            groupId = KafkaConstants.RUBY_GROUP_NAME)
    public void consumeRuby(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = rubySnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.RUST_TOPIC_NAME,
            groupId = KafkaConstants.RUST_GROUP_NAME)
    public void consumeRust(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = rustSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.SHELL_TOPIC_NAME,
            groupId = KafkaConstants.SHELL_GROUP_NAME)
    public void consumeShell(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = shellSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.YAML_TOPIC_NAME,
            groupId = KafkaConstants.YAML_GROUP_NAME)
    public void consumeYaml(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = yamlSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }

    @KafkaListener(topics = KafkaConstants.UNDEFINED_TOPIC_NAME,
            groupId = KafkaConstants.UNDEFINED_GROUP_NAME)
    public void consumeUndefined(String message) {
        log.info(String.format("Message recieved -> "));
        Mono output = undefinedSnippetsService.create(gson.fromJson(message, ApolloRequest.class));
        output.block();
    }
}