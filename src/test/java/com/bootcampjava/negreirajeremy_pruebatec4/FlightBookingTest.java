package com.bootcampjava.negreirajeremy_pruebatec4;

import com.bootcampjava.negreirajeremy_pruebatec4.dto.FlightBookingRequestDTO;
import com.bootcampjava.negreirajeremy_pruebatec4.models.PersonData;
import com.bootcampjava.negreirajeremy_pruebatec4.services.FlightService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FlightBookingTest {

    @Autowired
    private FlightService flightService;

    @Test
    public void testBookingAFlight() {
        // Datos de entrada
        LocalDate date = LocalDate.of(2024, 4, 12);
        String origin = "Buenos Aires";
        String destination = "Barcelona";
        String flightCode = "BUBA-3369";
        int peopleQ = 2;
        String seatType = "Economy";
        List<PersonData> passengers = Arrays.asList(new PersonData(), new PersonData());

        Double adequateFlightBookingRequest = flightService.bookFlight(new FlightBookingRequestDTO(date, origin, destination, flightCode, peopleQ, seatType, passengers));
        Double emptyRequest = flightService.bookFlight(null);

        // Al llegar al controlador devolveran una respuesta según está cantidad
        Assertions.assertEquals(0, emptyRequest);
        Assertions.assertTrue(adequateFlightBookingRequest > 0);
    }
}
