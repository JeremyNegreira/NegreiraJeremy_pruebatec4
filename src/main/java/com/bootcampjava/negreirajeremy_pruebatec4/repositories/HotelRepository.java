package com.bootcampjava.negreirajeremy_pruebatec4.repositories;

import com.bootcampjava.negreirajeremy_pruebatec4.models.Hotel;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByDeletedAtIsNull();

    Optional<Hotel> findByIdAndDeletedAtIsNull(Long id);

    Optional<Hotel> findByHotelCodeAndDeletedAtIsNull(String hotelCode);

    @Query("SELECT h FROM Hotel h "
            + "WHERE h.disponibilityDateFrom <= :dateTo "
            + "AND h.disponibilityDateTo >= :dateFrom "
            + "AND h.place = :destination "
            + "AND h.deletedAt IS NULL")
    List<Hotel> findAvailableHotels(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("destination") String destination
    );
}
