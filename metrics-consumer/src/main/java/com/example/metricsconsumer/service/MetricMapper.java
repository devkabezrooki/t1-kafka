package com.example.metricsconsumer.service;

import com.example.metricsconsumer.model.MetricMeasurement;
import com.example.metricsconsumer.model.dto.MetricMeasurementInputDto;
import com.example.metricsconsumer.model.dto.MetricResponseDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MetricMapper {

    public static MetricMeasurement createMetricMeasurementFromInput(MetricMeasurementInputDto metricDto) {
        MetricMeasurement metricMeasurement = new MetricMeasurement();
        metricMeasurement.setName(metricDto.getMetricName());
        metricMeasurement.setValue(metricDto.getMetricValue());
        metricMeasurement.setMeasureDate(metricDto.getMeasureDate());
        metricMeasurement.setReceiveDate(metricDto.getReceiveDate());
        return metricMeasurement;
    }

    public static MetricResponseDto createMetricResponseDtoFromMetricMeasurement(MetricMeasurement metricMeasurement) {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return new MetricResponseDto()
                .setMetricName(metricMeasurement.getName())
                .setMetricValue(metricMeasurement.getValue())
                .setMeasureDate(formatter.format(metricMeasurement.getMeasureDate()))
                .setReceiveDate(formatter.format(metricMeasurement.getReceiveDate()));
    }
}
