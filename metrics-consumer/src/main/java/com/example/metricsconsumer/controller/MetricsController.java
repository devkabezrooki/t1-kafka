package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.model.dto.MetricResponseDto;
import com.example.metricsconsumer.service.MetricMeasurementService;
import jakarta.annotation.Nonnull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final MetricMeasurementService metricMeasurementService;

    public MetricsController(@Nonnull MetricMeasurementService metricMeasurementService) {
        this.metricMeasurementService = metricMeasurementService;
    }

    @GetMapping()
    public ResponseEntity<List<MetricResponseDto>> getAllTasks() {
        return ResponseEntity.ok(metricMeasurementService.getAllMetricMeasurements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricResponseDto> getTask(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(metricMeasurementService.getMetricMeasurement(id));
    }
}
