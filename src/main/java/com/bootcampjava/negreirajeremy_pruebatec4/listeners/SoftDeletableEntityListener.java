package com.bootcampjava.negreirajeremy_pruebatec4.listeners;

import com.bootcampjava.negreirajeremy_pruebatec4.models.common.SoftDeletableEntity;
import jakarta.persistence.PreRemove;
import java.time.LocalDateTime;

public class SoftDeletableEntityListener {

    /**
     * Método llamado antes de eliminar (soft-delete) una entidad de la base de
     * datos. Establece la marca de tiempo de eliminación (deletedAt) en el
     * momento actual, solo si la entidad no ha sido previamente marcada como
     * eliminada.
     *
     * @param entity La entidad que se va a eliminar (soft-delete).
     */
    @PreRemove
    public void preRemove(SoftDeletableEntity entity) {
        if (!entity.isDeleted()) {
            entity.setDeletedAt(LocalDateTime.now());
        }
    }
}
