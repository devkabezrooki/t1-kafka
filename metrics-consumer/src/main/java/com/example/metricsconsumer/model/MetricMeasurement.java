package com.example.metricsconsumer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "metrics")
public class MetricMeasurement {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="IdGenerator")
    @GenericGenerator(name="IdGenerator", strategy="increment", parameters = {
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "1")
    })
    private Long id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "measure_date", nullable = false)
    private Date measureDate;

    @Column(name = "receive_date", nullable = false)
    private Date receiveDate;
}
