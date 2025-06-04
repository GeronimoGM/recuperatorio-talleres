package dev.geronimogm.recuperatorio.entity;

import dev.geronimogm.recuperatorio.entity.enums.Zona;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "talleres")
@NoArgsConstructor
@Getter
@Setter
public class TallerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Zona zona;

    @Column(nullable = false)
    private String tematica;

    @Column(nullable = false)
    private int cupoMaximo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "taller_vecino",
            joinColumns = @JoinColumn(name = "taller_id"),
            inverseJoinColumns = @JoinColumn(name = "vecino_dni")
    )
    private Set<VecinoEntity> vecinos = new HashSet<>();

    public TallerEntity(String nombre, Zona zona, String tematica, int cupoMaximo) {
        this.nombre = nombre;
        this.zona = zona;
        this.tematica = tematica;
        this.cupoMaximo = cupoMaximo;
    }
}
