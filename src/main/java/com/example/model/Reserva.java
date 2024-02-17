package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_reserva")
    private String nombreReserva;

    private Integer mesa;

    @Enumerated(EnumType.STRING)
    private Horario horario;

    public enum Horario {
        ALMUERZO, CENA
    }

    public Reserva(String nombreReserva, Integer mesa, Horario horario) {
        this.nombreReserva = nombreReserva;
        this.mesa = mesa;
        this.horario = horario;
    }
}
