package com.bootcampjava.negreirajeremy_pruebatec4.models;

import com.bootcampjava.negreirajeremy_pruebatec4.models.common.ModelEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelBooking extends ModelEntity {

    @Temporal(TemporalType.DATE)
    private LocalDate dateFrom;

    @Temporal(TemporalType.DATE)
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "hotel_code")
    private Hotel hotel;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hotel_booking_person",
            joinColumns = @JoinColumn(name = "hotel_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<PersonData> hosts;
}
