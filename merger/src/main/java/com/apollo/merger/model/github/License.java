package com.apollo.merger.model.github;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class License implements Serializable {
    private String name;
    private String url;
}
