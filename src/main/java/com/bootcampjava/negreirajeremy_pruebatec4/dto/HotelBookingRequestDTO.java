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
public class HotelBookingRequestDTO {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer nights;
    private String place;
    private String hotelCode;
    private Integer peopleQ;
    private String roomType;
    private List<PersonData> hosts;
}
