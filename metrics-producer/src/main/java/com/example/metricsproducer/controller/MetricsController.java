package com.example.metricsproducer.controller;

import com.example.metricsproducer.dto.MetricMeasurementDto;
import com.example.metricsproducer.service.MetricsSenderService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
@Validated
public class MetricsController {

    private final MetricsSenderService metricsSenderService;

    public MetricsController(@Nonnull MetricsSenderService metricsSenderService) {
        this.metricsSenderService = metricsSenderService;
    }

    @PostMapping
    public ResponseEntity<?> sendMetricMeasurement(@Valid @RequestBody MetricMeasurementDto metricDto) {
        metricsSenderService.sendMetricMeasurement(metricDto);
        return ResponseEntity.ok().build();
    }
}
