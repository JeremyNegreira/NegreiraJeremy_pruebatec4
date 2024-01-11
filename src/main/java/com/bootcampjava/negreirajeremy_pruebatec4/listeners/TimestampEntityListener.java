package com.bootcampjava.negreirajeremy_pruebatec4.listeners;

import com.bootcampjava.negreirajeremy_pruebatec4.models.common.ModelEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

public class TimestampEntityListener {

    /**
     * Método llamado antes de persistir una entidad en la base de datos.
     * Establece la marca de tiempo de creación (createdAt) en el momento
     * actual.
     *
     * @param entity La entidad que se va a persistir.
     */
    @PrePersist
    public void prePersist(ModelEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
    }

    /**
     * Método llamado antes de actualizar una entidad en la base de datos.
     * Establece la marca de tiempo de actualización (updatedAt) en el momento
     * actual.
     *
     * @param entity La entidad que se va a actualizar.
     */
    @PreUpdate
    public void preUpdate(ModelEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
    }

}
