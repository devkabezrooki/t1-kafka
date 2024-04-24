package com.example.metricsproducer.service;

import com.example.metricsproducer.dto.MetricMeasurementDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MetricsSenderService {

    private final KafkaTemplate<String, MetricMeasurementDto> kafkaTemplate;
    private final MetricsCollectorService metricsCollectorService;

    @Autowired
    public MetricsSenderService(KafkaTemplate<String, MetricMeasurementDto> kafkaTemplate,
                                MetricsCollectorService metricsCollectorService) {
        this.kafkaTemplate = kafkaTemplate;
        this.metricsCollectorService = metricsCollectorService;
    }

    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.MINUTES)
    public void sendAllMetricMeasurements() {
        Map<String, Double> metrics = metricsCollectorService.collectMetricValues();

        Date measureDate = new Date();
        metrics.keySet().forEach(metricKey -> {
            MetricMeasurementDto metricDto = new MetricMeasurementDto(metricKey, metrics.get(metricKey), measureDate);
            sendMetricMeasurement(metricDto);
        });
    }

    public void sendMetricMeasurement(MetricMeasurementDto metricDto) {
        kafkaTemplate.sendDefault(metricDto);
        log.info("Successfully send metric {} measurement", metricDto.getMetricName());
    }
}
