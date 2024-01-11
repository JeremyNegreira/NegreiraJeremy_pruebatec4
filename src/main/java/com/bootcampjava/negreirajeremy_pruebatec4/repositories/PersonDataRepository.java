package com.bootcampjava.negreirajeremy_pruebatec4.repositories;

import com.bootcampjava.negreirajeremy_pruebatec4.models.PersonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDataRepository extends JpaRepository<PersonData, Long> {

}
