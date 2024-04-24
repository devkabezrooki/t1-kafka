package com.example.metricsconsumer.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MetricMeasurementInputDto {
    String metricName;
    Double metricValue;
    Date measureDate;
    Date receiveDate;
}
