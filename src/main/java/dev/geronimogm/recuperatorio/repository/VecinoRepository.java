package dev.geronimogm.recuperatorio.repository;

import dev.geronimogm.recuperatorio.entity.VecinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VecinoRepository extends JpaRepository<VecinoEntity, Long> {
    Optional<VecinoEntity> findByDni(Long dni);

    List<VecinoEntity> findByNombreLike(String nombre);

    boolean existsByDni(Long dni);

    boolean existsByEmail(String email);
}
