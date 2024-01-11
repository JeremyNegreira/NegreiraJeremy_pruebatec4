package com.bootcampjava.negreirajeremy_pruebatec4.services;

import com.bootcampjava.negreirajeremy_pruebatec4.dto.FlightBookingRequestDTO;
import com.bootcampjava.negreirajeremy_pruebatec4.models.Flight;
import com.bootcampjava.negreirajeremy_pruebatec4.models.FlightBooking;
import com.bootcampjava.negreirajeremy_pruebatec4.repositories.FlightBookingRepository;
import com.bootcampjava.negreirajeremy_pruebatec4.repositories.FlightRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService implements IFlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    /**
     *
     * Obtiene todos los vuelos
     *
     * @return Lista de vuelos
     */
    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findByDeletedAtIsNull();
    }

    /**
     *
     * Obtiene los vuelos disponibles en un rango de fechas y desde/hacia un
     * origen y destino especificados
     *
     * @param dateFrom Fecha de inicio del rango
     * @param dateTo Fecha de fin del rango
     * @param origin Origen del vuelo
     * @param destination Destino del vuelo
     * @return Lista de vuelos disponibles
     */
    @Override
    public List<Flight> getAvailableFlights(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {
        return flightRepository.getAvailableFlights(dateFrom, dateTo, origin, destination);
    }

    /**
     *
     * Reserva un vuelo
     *
     * @param bookingRequest Información del vuelo a reservar
     *
     * @return Coste total de la reserva
     */
    @Override
    public Double bookFlight(FlightBookingRequestDTO bookingRequest) {
        Optional<Flight> flightRequested = flightRepository.findByFlightCodeAndDeletedAtIsNull(bookingRequest.getFlightCode());

        if (flightRequested.isEmpty()) {
            return 0.0;
        }

        Flight flight = flightRequested.get();

        // Comprobar sentido del vuelo
        boolean isReturn;
        if (bookingRequest.getDestination().equals(flight.getDestination())
                && bookingRequest.getOrigin().equals(flight.getOrigin())) {
            isReturn = false;
        } else if (bookingRequest.getDestination().equals(flight.getOrigin())
                && bookingRequest.getOrigin().equals(flight.getDestination())) {
            isReturn = true;
        } else {
            return 0.0;
        }

        // Guardar la reserva
        FlightBooking booking = new FlightBooking(flight, isReturn, bookingRequest.getPassengers());
        flightBookingRepository.save(booking);

        return flight.getFlightPrice() * bookingRequest.getPeopleQ();
    }

    /**
     *
     * Crea un nuevo vuelo
     *
     * @param flight Vuelo a crear
     * @return Vuelo creado
     */
    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    /**
     *
     * Edita un vuelo existente
     *
     * @param id ID del vuelo a editar
     *
     * @param flight Vuelo con los nuevos datos
     *
     * @return Vuelo editado o null si no se encuentra el vuelo con el ID
     * especificado
     */
    @Override
    public Optional<Flight> editFlight(Long id, Flight flight) {
        Optional<Flight> flightToEdit = getFlightById(flight.getId());

        if (flightToEdit.isPresent()) {
            Flight existingFlight = flightToEdit.get();

            existingFlight.setName(flight.getName());
            existingFlight.setDestination(flight.getDestination());
            existingFlight.setOrigin(flight.getOrigin());
            existingFlight.setDepartureDate(flight.getDepartureDate());
            existingFlight.setReturnDate(flight.getReturnDate());
            existingFlight.setFlightPrice(flight.getFlightPrice());

            flightRepository.save(existingFlight);

            return Optional.of(existingFlight);
        } else {
            // No se ha encontrado un resultado
            return Optional.empty();
        }
    }

    /**
     *
     * Elimina un vuelo existente
     *
     * @param id ID del vuelo a eliminar
     *
     * @return Vuelo eliminado
     */
    @Override
    public Optional<Flight> deleteFlight(Long id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        flightOptional.ifPresent(flight -> {
            flight.setDeletedAt(LocalDateTime.now());
            flightRepository.save(flight);
        });
        return flightOptional;
    }

    /**
     *
     * Obtiene un vuelo a partir de su ID
     *
     * @param id ID del vuelo a obtener
     * @return Vuelo con el ID especificado o null si no se encuentra el vuelo
     */
    @Override
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findByIdAndDeletedAtIsNull(id);
    }
}
