package com.bootcampjava.negreirajeremy_pruebatec4.models;

import com.bootcampjava.negreirajeremy_pruebatec4.models.common.ModelEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonData extends ModelEntity {

    // Datos de ejemplo para una primera versión
    private String documentID;
    private String firstName;
    private String lastName;
    private String nationality;
    private String email;
    private String telephone;
}
