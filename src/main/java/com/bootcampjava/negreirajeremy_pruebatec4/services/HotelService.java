package com.bootcampjava.negreirajeremy_pruebatec4.services;

import com.bootcampjava.negreirajeremy_pruebatec4.dto.HotelBookingRequestDTO;
import com.bootcampjava.negreirajeremy_pruebatec4.dto.HotelDTO;
import com.bootcampjava.negreirajeremy_pruebatec4.models.Hotel;
import com.bootcampjava.negreirajeremy_pruebatec4.models.HotelBooking;
import com.bootcampjava.negreirajeremy_pruebatec4.repositories.HotelBookingRepository;
import com.bootcampjava.negreirajeremy_pruebatec4.repositories.HotelRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelBookingRepository hotelBookingRepository;

    /**
     * Obtiene la lista de todos los hoteles.
     *
     * @return Lista de DTOs de hoteles.
     */
    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findByDeletedAtIsNull()
                .stream()
                .map(this::mapHotelToHotelDTO)
                .toList();
    }

    /**
     * Obtiene la lista de hoteles disponibles según las fechas y destino
     * proporcionados.
     *
     * @param dateFrom Fecha de inicio para la búsqueda.
     * @param dateTo Fecha de fin para la búsqueda.
     * @param destination Destino de los hoteles buscados.
     * @return Lista de DTOs de hoteles disponibles.
     */
    @Override
    public List<HotelDTO> getAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String destination) {
        return hotelRepository.findAvailableHotels(dateFrom, dateTo, destination)
                .stream()
                .map(this::mapHotelToHotelDTO)
                .toList();
    }

    /**
     * Realiza la reserva de un hotel y devuelve el monto total de la reserva.
     *
     * @param bookingRequest DTO con la información de la reserva.
     * @return Monto total de la reserva.
     */
    @Override
    public Double bookHotel(HotelBookingRequestDTO bookingRequest) {
        Optional<Hotel> hotelRequested = hotelRepository.findByHotelCodeAndDeletedAtIsNull(bookingRequest.getHotelCode());

        if (hotelRequested.isEmpty()) {
            return 0.0;
        }
        Hotel hotel = hotelRequested.get();
        HotelBooking booking = new HotelBooking(bookingRequest.getDateFrom(), bookingRequest.getDateTo(), hotel, bookingRequest.getHosts());
        hotelBookingRepository.save(booking);

        return hotel.getPrice() * bookingRequest.getPeopleQ();
    }

    /**
     * Crea un nuevo hotel.
     *
     * @param hotelDTO DTO del hotel a crear.
     * @return DTO del hotel creado.
     */
    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel(hotelDTO.getHotelCode(),
                hotelDTO.getName(), hotelDTO.getPlace(),
                hotelDTO.getRoomType(), hotelDTO.getRoomPrice(),
                hotelDTO.getDisponibilityDateFrom(),
                hotelDTO.getDisponibilityDateTo());

        hotelRepository.save(hotel);
        return mapHotelToHotelDTO(hotel);
    }

    /**
     * Edita la información de un hotel existente.
     *
     * @param id Identificador del hotel a editar.
     * @param hotelDTO DTO del hotel con la información actualizada.
     * @return DTO del hotel actualizado.
     */
    @Override
    public HotelDTO editHotel(Long id, HotelDTO hotelDTO) {
        Optional<Hotel> optionalExistingHotel = hotelRepository.findByIdAndDeletedAtIsNull(id);

        if (optionalExistingHotel.isPresent()) {
            Hotel existingHotel = optionalExistingHotel.get();

            // Actualizar valores
            existingHotel.setName(hotelDTO.getName());
            existingHotel.setPlace(hotelDTO.getPlace());
            existingHotel.setType(hotelDTO.getRoomType());
            existingHotel.setPrice(hotelDTO.getRoomPrice());
            existingHotel.setDisponibilityDateFrom(hotelDTO.getDisponibilityDateFrom());
            existingHotel.setDisponibilityDateTo(hotelDTO.getDisponibilityDateTo());

            // Guardar
            hotelRepository.save(existingHotel);

            return mapHotelToHotelDTO(existingHotel);
        } else {
            // No se ha encontrado nada
            return null;
        }
    }

    /**
     * Elimina un hotel existente.
     *
     * @param id Identificador del hotel a eliminar.
     * @return DTO del hotel eliminado.
     */
    @Override
    public HotelDTO deleteHotel(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        optionalHotel.ifPresent(hotel -> {
            hotel.setDeletedAt(LocalDateTime.now());
            hotelRepository.save(hotel);
        });

        return optionalHotel.isPresent() ? mapHotelToHotelDTO(optionalHotel.get()) : null;
    }

    /**
     * Obtiene la información de un hotel por su identificador.
     *
     * @param id Identificador del hotel.
     * @return DTO del hotel.
     */
    @Override
    public HotelDTO getHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findByIdAndDeletedAtIsNull(id);
        return hotel.isEmpty() ? null : mapHotelToHotelDTO(hotel.get());
    }

    /**
     * Mapea los Hotel a HotelDTO para el endpoint.
     *
     * @param hotel Hotel a mapear.
     * @return DTO del hotel mapeado.
     */
    private HotelDTO mapHotelToHotelDTO(Hotel hotel) {
        return new HotelDTO(
                hotel.getHotelCode(),
                hotel.getName(),
                hotel.getName(),
                hotel.getType(),
                hotel.getPrice(),
                hotel.getDisponibilityDateFrom(),
                hotel.getDisponibilityDateTo(),
                hotelBookingRepository.existsByHotelHotelCode(hotel.getHotelCode()));
    }

}
