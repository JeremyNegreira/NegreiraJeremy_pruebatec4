package com.bootcampjava.negreirajeremy_pruebatec4.models;

import com.bootcampjava.negreirajeremy_pruebatec4.models.common.ModelEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class FlightBooking extends ModelEntity {

    @ManyToOne
    @JoinColumn(name = "flight_code")
    private Flight flight;

    private Boolean isReturn;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "flight_booking_person",
            joinColumns = @JoinColumn(name = "flight_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<PersonData> passengers;
}
