package com.example.metricsconsumer.service;

import com.example.metricsconsumer.model.dto.MetricMeasurementInputDto;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class KafkaListenerService {

    private final MetricMeasurementService metricMeasurementService;

    @Autowired
    public KafkaListenerService(@Nonnull MetricMeasurementService metricMeasurementService) {
        this.metricMeasurementService = metricMeasurementService;
    }

    @KafkaListener(topics = "metrics-topic", groupId = "metrics-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenMetrics(MetricMeasurementInputDto message) {
        message.setReceiveDate(new Date());
        metricMeasurementService.saveMetricMeasurement(message);
        log.info("Successfully save metric {} measurement", message.getMetricName());
    }

}
