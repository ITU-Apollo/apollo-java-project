package com.apollo.merger.repository.snippet;

import com.apollo.merger.model.AnomalyModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnomalyRepository extends MongoRepository<AnomalyModel, String> {

    boolean existsByAnomalyId (String id);

    boolean existsByAnomalyIdAndAnomalyType (String anomalyId, String anomalyType);

    List<AnomalyModel> findAllByAnomalyType(String anomalyType);
}
