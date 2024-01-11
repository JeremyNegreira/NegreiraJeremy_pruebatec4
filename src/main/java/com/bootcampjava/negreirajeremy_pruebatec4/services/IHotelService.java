package com.bootcampjava.negreirajeremy_pruebatec4.services;

import com.bootcampjava.negreirajeremy_pruebatec4.dto.HotelBookingRequestDTO;
import com.bootcampjava.negreirajeremy_pruebatec4.dto.HotelDTO;
import java.time.LocalDate;
import java.util.List;

public interface IHotelService {

    public List<HotelDTO> getAllHotels();

    public List<HotelDTO> getAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String destination);

    public Double bookHotel(HotelBookingRequestDTO bookingRequest);

    public HotelDTO createHotel(HotelDTO hotel);

    public HotelDTO editHotel(Long id, HotelDTO hotel);

    public HotelDTO deleteHotel(Long id);

    public HotelDTO getHotelById(Long id);
}
