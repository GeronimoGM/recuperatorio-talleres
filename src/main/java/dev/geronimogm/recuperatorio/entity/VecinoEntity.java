package dev.geronimogm.recuperatorio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vecinos")
@NoArgsConstructor
@Getter
@Setter
public class VecinoEntity {

    @Id
    private Long dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "vecinos", fetch = FetchType.LAZY)
    private Set<TallerEntity> talleres = new HashSet<>();

    public VecinoEntity(Long dni, String nombre, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
    }
}

