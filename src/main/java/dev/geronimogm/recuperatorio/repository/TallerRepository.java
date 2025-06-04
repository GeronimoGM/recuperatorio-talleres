package dev.geronimogm.recuperatorio.repository;

import dev.geronimogm.recuperatorio.entity.TallerEntity;
import dev.geronimogm.recuperatorio.entity.enums.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TallerRepository extends JpaRepository<TallerEntity, Long> {
    List<TallerEntity> findByZonaAndNombreLike(Zona zona, String nombre);
}
