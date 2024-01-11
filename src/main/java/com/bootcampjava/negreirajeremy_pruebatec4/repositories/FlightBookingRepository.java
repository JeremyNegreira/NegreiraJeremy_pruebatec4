package com.bootcampjava.negreirajeremy_pruebatec4.repositories;

import com.bootcampjava.negreirajeremy_pruebatec4.models.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {

}
