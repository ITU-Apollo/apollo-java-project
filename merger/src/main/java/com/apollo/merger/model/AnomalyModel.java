package com.apollo.merger.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("Anomalies")
public class AnomalyModel {

    @Id
    private String id;
    private String anomalyRootCollection;
    private String anomalyCause;
    private String anomalyType;
    private String anomalyId;
}
