package com.example.metricsconsumer.repository;

import com.example.metricsconsumer.model.MetricMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricMeasurementRepository extends JpaRepository<MetricMeasurement, Long> {
}
