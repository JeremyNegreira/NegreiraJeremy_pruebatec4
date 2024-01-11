package com.bootcampjava.negreirajeremy_pruebatec4.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HotelDTO {

    private String hotelCode;
    private String name;
    private String place;
    private String roomType;
    private Double roomPrice;
    private LocalDate disponibilityDateFrom;
    private LocalDate disponibilityDateTo;
    private boolean isBooked;
}
