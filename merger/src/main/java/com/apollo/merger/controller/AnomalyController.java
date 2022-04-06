package com.apollo.merger.controller;

import com.apollo.merger.service.AnomalyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apollo/anomalies")
@RequiredArgsConstructor
public class AnomalyController {

    private final AnomalyService anomalyService;

    @GetMapping("/file-names/{anomaly-type}")
    public ResponseEntity<List<String>> getFileNamesByAnomalyType(@PathVariable("anomaly-type") String anomalyType) {
        return ResponseEntity.ok(anomalyService.getFileNamesByAnomalyType(anomalyType));
    }

    @PostMapping("/repair")
    public ResponseEntity<List<String>> repairAnomalies() {
        return ResponseEntity.ok(anomalyService.repairAnomalies());
    }
}
