package com.bootcampjava.negreirajeremy_pruebatec4.controllers;

import com.bootcampjava.negreirajeremy_pruebatec4.models.Flight;
import com.bootcampjava.negreirajeremy_pruebatec4.services.IFlightService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agency/flights")
public class FlightController {

    @Autowired
    private IFlightService flightService;

    // User Story Nº 4
    /**
     * Obtiene la lista de todos los vuelos.
     *
     * @return ResponseEntity con la lista de vuelos y el código de estado
     * correspondiente.
     */
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return flights.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // User Story Nº 5
    /**
     * Obtiene los vuelos disponibles entre dos fechas con un destino y origen
     * específicos.
     *
     * @param dateFrom
     * @param dateTo
     * @param origin
     * @param destination
     * @return Lista de vuelos encontrados con estado OK o estado no encontrado.
     */
    @GetMapping(params = {"dateFrom", "dateTo", "origin", "destination"})
    public ResponseEntity<List<Flight>> getAvailableFlights(@RequestParam LocalDate dateFrom,
            @RequestParam LocalDate dateTo,
            @RequestParam String origin,
            @RequestParam String destination) {
        List<Flight> availableFlights = flightService.getAvailableFlights(dateFrom, dateTo, origin, destination);
        if (availableFlights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(availableFlights, HttpStatus.OK);
    }

    // User Story Nº 7 - Requieres autenticación
    /**
     * Crea un vuelo a partir de un objeto Flight.
     *
     * @param flight
     * @return Vuelo creado y estado creado o mensaje de error interno.
     */
    @PostMapping("/new")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight flightCreated = flightService.createFlight(flight);
        return flightCreated != null
                ? new ResponseEntity<>(flightCreated, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Realiza una edición de un vuelo encontrado por su id por los datos.
     *
     * @param id
     * @return Vuelo editado y estado OK o estado no encontrado si no hay
     * resultados
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Flight> editFlight(@PathVariable Long id, @RequestBody Flight flight) {
        return flightService.editFlight(id, flight)
                .map(updatedFlight -> new ResponseEntity<>(flight, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Realiza un borrado lógico de un vuelo encontrado por su id.
     *
     * @param id
     * @return Vuelo borrado y estado OK o estado no encontrado si no hay
     * resultados
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable Long id) {
        return flightService.deleteFlight(id)
                .map(deletedFlight -> new ResponseEntity<>(deletedFlight, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Obtiene el vuelo por el campo Id.
     *
     * @param id
     * @return Vuelo encontrado y estado OK o estado no encontrado si no hay
     * resultados
     */
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id)
                .map(foundFlight -> new ResponseEntity<>(foundFlight, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
