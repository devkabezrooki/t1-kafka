package com.example.metricsconsumer.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class MetricResponseDto {
    @JsonProperty("Наименование метрики")
    String metricName;
    @JsonProperty("Значение метрики")
    Double metricValue;
    @JsonProperty("Дата и время замера")
    String measureDate;
    @JsonProperty("Дата и время обработки")
    String receiveDate;
}
