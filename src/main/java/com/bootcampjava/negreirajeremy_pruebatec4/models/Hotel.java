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
public class Hotel extends SoftDeletableEntity {
    // Se refiere a la habitación

    private String hotelCode;
    private String name;
    private String place;
    private String type;
    private Double price;

    @Temporal(TemporalType.DATE)
    private LocalDate disponibilityDateFrom;

    @Temporal(TemporalType.DATE)
    private LocalDate disponibilityDateTo;
}
