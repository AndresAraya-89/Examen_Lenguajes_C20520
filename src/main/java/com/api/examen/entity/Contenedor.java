package com.api.examen.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contenedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contenedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_contenedor;

    @Column(name = "vin", nullable = false)
    private String vin;

    @Column(name = "fecha", nullable = false)
    private String fecha;

    @ManyToOne()
    @JoinColumn(name = "id_barco")
    private Barco barco;

}
