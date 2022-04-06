package com.apollo.merger.service;

import com.apollo.merger.model.AnomalyModel;
import com.apollo.merger.repository.snippet.AnomalyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnomalyService {

    private final AnomalyRepository anomalyRepository;
    private final CommitFileService commitFileService;
    private final CommitService commitService;

    public List<String> getFileNamesByAnomalyType(String anomalyType) {
        List<AnomalyModel> anomalyIds = anomalyRepository.findAllByAnomalyType(anomalyType);

        if (CollectionUtils.isEmpty(anomalyIds))
            return new ArrayList<>();

        return commitFileService.getFileNamesByIdList(anomalyIds.stream()
                .map(AnomalyModel::getAnomalyId)
                .collect(Collectors.toList())
        );
    }

    public List<String> repairAnomalies() {
        List<String> repaired = new ArrayList<>();

        List<AnomalyModel> anomalyModels = anomalyRepository.findAll();

        for (AnomalyModel anomalyModel : anomalyModels) {
            String anomalyId = anomalyModel.getAnomalyId();
            String anomalyType = anomalyModel.getAnomalyType();
            boolean result = false;

            if (!anomalyType.equals("Missing File"))
                result = commitFileService.recreateFileWithId(anomalyId);
            else if (anomalyType.equals("Missing File"))
                result = commitService.recreateFileFromSnippets(anomalyId);

            if (result)
                repaired.add(anomalyId);

            anomalyRepository.deleteById(anomalyModel.getId());
        }

        return repaired;
    }
}
