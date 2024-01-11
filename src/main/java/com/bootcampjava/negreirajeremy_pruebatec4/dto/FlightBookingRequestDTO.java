package com.bootcampjava.negreirajeremy_pruebatec4.dto;

import com.bootcampjava.negreirajeremy_pruebatec4.models.PersonData;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FlightBookingRequestDTO {

    private LocalDate date;
    private String origin;
    private String destination;
    private String flightCode;
    private Integer peopleQ;
    private String seatType;
    private List<PersonData> passengers;
}
