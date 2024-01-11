package com.bootcampjava.negreirajeremy_pruebatec4.controllers;

import com.bootcampjava.negreirajeremy_pruebatec4.dto.HotelDTO;
import com.bootcampjava.negreirajeremy_pruebatec4.models.Hotel;
import com.bootcampjava.negreirajeremy_pruebatec4.services.IHotelService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agency/hotels")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    // User Story Nº 1
    /**
     * Obtiene la lista de todos los hoteles.
     *
     * @return ResponseEntity con la lista de DTOs de hoteles y el código de
     * estado correspondiente.
     */
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAllHotels();

        if (hotels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(hotels, HttpStatus.OK);
        }
    }

    // User Story Nº 2
    /**
     * Obtiene la lista de hoteles disponibles según las fechas y destino
     * proporcionados.
     *
     * @param dateFrom Fecha de inicio para la búsqueda.
     * @param dateTo Fecha de fin para la búsqueda.
     * @param destination Destino de los hoteles buscados.
     * @return ResponseEntity con la lista de DTOs de hoteles disponibles y el
     * código de estado correspondiente.
     */
    @GetMapping(params = {"dateFrom", "dateTo", "destination"})
    public ResponseEntity<List<HotelDTO>> getAvailableHotels(
            @RequestParam LocalDate dateFrom,
            @RequestParam LocalDate dateTo,
            @RequestParam String destination) {

        List<HotelDTO> availableHotels = hotelService.getAvailableHotels(dateFrom, dateTo, destination);

        if (availableHotels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(availableHotels, HttpStatus.OK);
        }
    }

    // User Story Nº 7
    /**
     * Crea un nuevo hotel.
     *
     * @param hotel DTO del hotel a crear.
     * @return ResponseEntity con el código de estado correspondiente.
     */
    @PostMapping("/new")
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelDTO hotel) {
        HotelDTO createdHotelDTO = hotelService.createHotel(hotel);

        if (createdHotelDTO != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Edita la información de un hotel existente.
     *
     * @param id Identificador del hotel a editar.
     * @param hotel DTO del hotel con la información actualizada.
     * @return ResponseEntity con el DTO del hotel actualizado y el código de
     * estado correspondiente.
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<HotelDTO> editHotel(@PathVariable Long id, @RequestBody HotelDTO hotel) {
        HotelDTO hotelDTO = hotelService.editHotel(id, hotel);

        if (hotelDTO != null) {
            return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un hotel existente.
     *
     * @param id Identificador del hotel a eliminar.
     * @return ResponseEntity con el DTO del hotel eliminado y el código de
     * estado correspondiente.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HotelDTO> deleteHotel(@PathVariable Long id) {
        HotelDTO hotelDTO = hotelService.deleteHotel(id);

        if (hotelDTO != null) {
            return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene la información de un hotel por su identificador.
     *
     * @param id Identificador del hotel.
     * @return ResponseEntity con el DTO del hotel y el código de estado
     * correspondiente.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        HotelDTO hotelDTO = hotelService.getHotelById(id);

        if (hotelDTO != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
