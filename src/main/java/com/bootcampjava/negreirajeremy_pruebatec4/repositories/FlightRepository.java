package com.bootcampjava.negreirajeremy_pruebatec4.repositories;

import com.bootcampjava.negreirajeremy_pruebatec4.models.Flight;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    /**
     * Recupera todos los vuelos donde el atributo deletedAt es nulo.
     *
     * @return Lista de entidades Flight con deletedAt establecido como nulo.
     */
    List<Flight> findByDeletedAtIsNull();

    Optional<Flight> findByIdAndDeletedAtIsNull(Long id);

    /**
     * Recupera una entidad Flight basada en el código de vuelo.
     *
     * @param flightCode Código único asociado al vuelo.
     * @return Un Optional que puede contener una entidad Flight correspondiente
     * al flightCode proporcionado.
     */
    Optional<Flight> findByFlightCodeAndDeletedAtIsNull(String flightCode);

    /**
     * Recupera todos los vuelos disponibles entre las fechas especificadas con
     * un origen y destino específicos.
     *
     * @param dateFrom La fecha de inicio para la búsqueda de disponibilidad.
     * @param dateTo La fecha de finalización para la búsqueda de
     * disponibilidad.
     * @param origin Lugar de origen.
     * @param destination Lugar de destino.
     * @return Lista de entidades Flight disponibles que cumplen con los
     * criterios especificados.
     */
    @Query("SELECT f FROM Flight f WHERE f.departureDate >= :dateFrom AND f.departureDate <= :dateTo "
            + "AND f.origin = :origin AND f.destination = :destination AND f.deletedAt IS NULL")
    List<Flight> getAvailableFlights(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("origin") String origin,
            @Param("destination") String destination
    );
}
