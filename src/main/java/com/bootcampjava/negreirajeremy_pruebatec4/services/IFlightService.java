package com.bootcampjava.negreirajeremy_pruebatec4.services;

import com.bootcampjava.negreirajeremy_pruebatec4.dto.FlightBookingRequestDTO;
import com.bootcampjava.negreirajeremy_pruebatec4.models.Flight;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IFlightService {

    public List<Flight> getAllFlights();

    public List<Flight> getAvailableFlights(LocalDate dateFrom, LocalDate dateTo, String origin, String destination);

    public Double bookFlight(FlightBookingRequestDTO bookingRequest);

    public Flight createFlight(Flight flight);

    public Optional<Flight> editFlight(Long id, Flight flight);

    public Optional<Flight> deleteFlight(Long id);

    public Optional<Flight> getFlightById(Long id);
}
