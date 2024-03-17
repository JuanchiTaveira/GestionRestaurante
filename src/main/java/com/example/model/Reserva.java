package com.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@Entity
@Table(name = "Reservas")
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_reserva_id") // nombre de la columna en la tabla Reservas que contendrá el ID del usuario de la reserva
    private UsuarioReserva usuarioReserva;

    @Column(name = "numero_mesa")
    private Integer numeroMesa;

    private LocalDate dia;

    @Enumerated(EnumType.STRING)
    private Horario horario;

    @Column(name = "numero_personas")
    private Integer numeroPersonas;

    public enum Horario {
        ALMUERZO, CENA
    }

    public Reserva(UsuarioReserva usuarioReserva, Integer numeroMesa, LocalDate dia, Horario horario, Integer numeroPersonas) {
        this.usuarioReserva = usuarioReserva;
        this.numeroMesa = numeroMesa;
        this.dia = dia;
        this.horario = horario;
        this.numeroPersonas = numeroPersonas;
    }
}
