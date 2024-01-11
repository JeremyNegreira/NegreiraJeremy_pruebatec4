package com.bootcampjava.negreirajeremy_pruebatec4.controllers;

import com.bootcampjava.negreirajeremy_pruebatec4.dto.FlightBookingRequestDTO;
import com.bootcampjava.negreirajeremy_pruebatec4.dto.HotelBookingRequestDTO;
import com.bootcampjava.negreirajeremy_pruebatec4.services.IFlightService;
import com.bootcampjava.negreirajeremy_pruebatec4.services.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agency")
public class BookingController {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IFlightService flightService;

    // User Story Nº 3
    /**
     * Maneja la creación de una nueva reserva de hotel.
     *
     * @param bookingRequest DTO que contiene información para la reserva de
     * hotel.
     * @return ResponseEntity<Double> El monto total de la reserva o un estado
     * HTTP BAD_REQUEST si algo salió mal.
     *
     * @PostMapping("/hotel-booking/new")
     */
    @PostMapping("/hotel-booking/new")
    public ResponseEntity<Double> bookHotel(@RequestBody HotelBookingRequestDTO bookingRequest) {
        return handleBooking(hotelService.bookHotel(bookingRequest));
    }

    // User Story Nº 6
    /**
     * Maneja la creación de una nueva reserva de vuelo.
     *
     * @param bookingRequest DTO que contiene información para la reserva de
     * vuelo.
     * @return ResponseEntity<Double> El monto total de la reserva o un estado
     * HTTP BAD_REQUEST si algo salió mal.
     *
     * @PostMapping("/flight-booking/new")
     */
    @PostMapping("/flight-booking/new")
    public ResponseEntity<Double> bookFlight(@RequestBody FlightBookingRequestDTO bookingRequest) {
        return handleBooking(flightService.bookFlight(bookingRequest));
    }

    /**
     * Método común para manejar las respuestas de las reservas.
     *
     * @param totalAmount El monto total de la reserva.
     * @return ResponseEntity<Double> El monto total de la reserva o un estado
     * HTTP BAD_REQUEST si el monto total es 0 o menos.
     */
    private ResponseEntity<Double> handleBooking(Double totalAmount) {
        if (totalAmount <= 0) {
            return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(totalAmount, HttpStatus.CREATED);
    }
}
