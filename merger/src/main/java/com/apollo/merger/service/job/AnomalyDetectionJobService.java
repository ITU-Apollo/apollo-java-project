package com.apollo.merger.service.job;

import com.apollo.merger.model.AnomalyModel;
import com.apollo.merger.model.github.GenericGithubCommits;
import com.apollo.merger.model.github.GithubCommitFile;
import com.apollo.merger.repository.CommitRepository;
import com.apollo.merger.repository.FileRepository;
import com.apollo.merger.repository.snippet.AnomalyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnomalyDetectionJobService {

    private final FileRepository fileRepository;
    private final CommitRepository commitRepository;
    private final AnomalyRepository anomalyRepository;

//    @Scheduled(cron = "* * * * * *")
    @Scheduled(cron = "* 15 */6 * * *")
    public void anomalyDetectionOnFileSnippetOrdering() {
        log.info("Anomaly Detection Started.");
        Pageable pageable = PageRequest.of(0, 100);
        boolean flag = true;
        boolean isValid = true;

        while (flag) {
            Page<GithubCommitFile> page = fileRepository.findAllByValidIsFalseOrValidIsNull(pageable);

            for (GithubCommitFile file : page.getContent()) {
                String orderLineString = file.getOrderLine();
                List<String> orderLineList = Arrays.stream(orderLineString.split(",")).toList();
                List<String> sortedLineList = orderLineList.stream()
                        .distinct()
                        .sorted(Comparator.comparing(Integer::valueOf))
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEqualCollection(orderLineList, sortedLineList)) {
                    saveAnomaly(file.getId(), "CommitFile", orderLineString, "Non-Synchronized");
                    isValid = false;
                }

                String lastNumber = sortedLineList.get(sortedLineList.size()-1);

                if ((Integer.valueOf(lastNumber).intValue()/5 + 1) > sortedLineList.size()) {
                    saveAnomaly(file.getId(), "CommitFile", orderLineString, "Missing Snippet");
                    isValid = false;
                }

                file.setValid(isValid);
                fileRepository.save(file);
                isValid = true;
            }

            if (page.hasNext())
                pageable = page.nextPageable();
            else
                flag = false;
        }
        log.info("Anomaly Detection Finished.");
    }

    @Scheduled(cron = "* 0 0 * * *")
    public void anomalyDetectionMissingFiles () {
        Pageable pageable = PageRequest.of(0, 100);
        boolean flag = true;

        while (flag) {
            Page<GenericGithubCommits> page = commitRepository.findAll(pageable);

            for (GenericGithubCommits commit : page.getContent()) {
                Set<String> fileNames = commit.getFiles();
                List<GithubCommitFile> commitFiles = fileRepository.findAllByFileNameIn(fileNames);
                Set<String> foundFileNames = commitFiles.stream().map(GithubCommitFile::getFileName).collect(Collectors.toSet());
                fileNames.removeAll(foundFileNames);

                if (fileNames.size() > 0)
                    fileNames.forEach(e -> saveAnomaly(commit.getId(), "Commits", e, "Missing File"));
            }

            if (page.hasNext())
                pageable = page.nextPageable();
            else
                flag = false;
        }
    }

    private void saveAnomaly (String documentId, String collectionName, String cause, String type) {
        log.info("Anomaly Found At {} with id {}", collectionName, documentId);
        boolean exists = anomalyRepository.existsByAnomalyIdAndAnomalyType(documentId, type);

        if (exists)
            return;

        AnomalyModel anomalyModel = new AnomalyModel();
        anomalyModel.setAnomalyId(documentId);
        anomalyModel.setAnomalyRootCollection(collectionName);
        anomalyModel.setAnomalyCause(cause);
        anomalyModel.setAnomalyType(type);

        anomalyRepository.save(anomalyModel);
    }

}
