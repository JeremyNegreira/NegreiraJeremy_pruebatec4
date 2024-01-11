package com.bootcampjava.negreirajeremy_pruebatec4.repositories;

import com.bootcampjava.negreirajeremy_pruebatec4.models.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {

    public boolean existsByHotelHotelCode(String hotelCode);
}
