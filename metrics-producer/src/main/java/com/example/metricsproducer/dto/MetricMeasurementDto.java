package com.example.metricsproducer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class MetricMeasurementDto {

    @Nonnull
    @JsonProperty
    String metricName;
    @Nonnull
    @JsonProperty
    Double metricValue;
    @Nonnull
    @JsonProperty
    Date measureDate;

}
