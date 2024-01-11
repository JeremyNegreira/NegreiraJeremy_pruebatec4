package com.bootcampjava.negreirajeremy_pruebatec4.models;

import com.bootcampjava.negreirajeremy_pruebatec4.models.common.SoftDeletableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends SoftDeletableEntity {

    private String flightCode;
    private String name;
    private String origin;
    private String destination;
    private String seatType;
    private Double flightPrice;

    @Temporal(TemporalType.DATE)
    private LocalDate departureDate;

    @Temporal(TemporalType.DATE)
    private LocalDate returnDate;
}
