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

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findByDeletedAtIsNull()
                .stream()
                .map(this::mapHotelToHotelDTO)
                .toList();
    }

    @Override
    public List<HotelDTO> getAvailableHotels(LocalDate dateFrom, LocalDate dateTo, String destination) {
        return hotelRepository.findAvailableHotels(dateFrom, dateTo, destination)
                .stream()
                .map(this::mapHotelToHotelDTO)
                .toList();
    }

    @Override
    public Double bookHotel(HotelBookingRequestDTO bookingRequest) {
        Optional<Hotel> hotelRequested = hotelRepository.findByHotelCodeAndDeletedAtIsNull(bookingRequest.getHotelCode());

        if (hotelRequested.isEmpty()) {
            return 0.0;
        }
        Hotel hotel = hotelRequested.get();
        HotelBooking booking = new HotelBooking(bookingRequest.getDateFrom(), bookingRequest.getDateTo(), hotel, bookingRequest.getHosts());
        hotelBookingRepository.save(booking);

        Double totalAmount = hotel.getPrice() * bookingRequest.getPeopleQ();
        return totalAmount;
    }

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

    @Override
    public HotelDTO deleteHotel(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        optionalHotel.ifPresent(hotel -> {
            hotel.setDeletedAt(LocalDateTime.now());
            hotelRepository.save(hotel);
        });

        return optionalHotel.isPresent() ? mapHotelToHotelDTO(optionalHotel.get()) : null;
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findByIdAndDeletedAtIsNull(id);
        return hotel.isEmpty() ? null : mapHotelToHotelDTO(hotel.get());
    }

    /**
     * Mapea los Hotel a HotelDTO para el endpoint.
     *
     * @param hotel
     * @return hotelDTO
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
