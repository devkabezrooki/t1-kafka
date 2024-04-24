package com.example.metricsconsumer.service;

import com.example.metricsconsumer.model.MetricMeasurement;
import com.example.metricsconsumer.model.dto.MetricMeasurementInputDto;
import com.example.metricsconsumer.model.dto.MetricResponseDto;
import com.example.metricsconsumer.repository.MetricMeasurementRepository;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetricMeasurementService {

    private final MetricMeasurementRepository metricMeasurementRepository;

    @Autowired
    public MetricMeasurementService(@Nonnull MetricMeasurementRepository metricMeasurementRepository) {
        this.metricMeasurementRepository = metricMeasurementRepository;
    }

    @Transactional
    public List<MetricResponseDto> getAllMetricMeasurements() {
        return metricMeasurementRepository.findAll().stream()
                .map(m -> MetricMapper.createMetricResponseDtoFromMetricMeasurement(m))
                .collect(Collectors.toList());
    }

    @Transactional
    public MetricResponseDto getMetricMeasurement(Long id) {
        MetricMeasurement metricMeasurement = metricMeasurementRepository.findById(id).orElseThrow();
        return MetricMapper.createMetricResponseDtoFromMetricMeasurement(metricMeasurement);
    }

    @Transactional
    public void saveMetricMeasurement(MetricMeasurementInputDto metricDto) {
        metricMeasurementRepository.save(MetricMapper.createMetricMeasurementFromInput(metricDto));
    }
}
