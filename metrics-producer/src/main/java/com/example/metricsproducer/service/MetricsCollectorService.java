package com.example.metricsproducer.service;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.MetricDescriptor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MetricsCollectorService {

    private final Set<String> metricNames = Set.of(
            "jvm.compilation.time",
            "disk.total",
            "disk.free",
            "jvm.memory.used",
            "jvm.gc.memory.allocated",
            "jvm.classes.loaded",
            "jvm.classes.unloaded",
            "jvm.buffer.memory.used",
            "application.started.time",
            "tasks.scheduled.execution.active"
    );

    private final MetricsEndpoint metricsEndpoint;

    @Autowired
    public MetricsCollectorService(@Nonnull MetricsEndpoint metricsEndpoint) {
        this.metricsEndpoint = metricsEndpoint;
    }

    public Map<String, Double> collectMetricValues() {
        return metricNames.stream()
                .collect(Collectors.toMap(mn -> mn, mn -> {
                    MetricDescriptor response = metricsEndpoint.metric(mn, null);
                    Optional<Double> metricValue = response.getMeasurements().stream()
                            .map(m -> m.getValue())
                            .findFirst();
                    return metricValue.orElse((double) 0);
                }));
    }
}
