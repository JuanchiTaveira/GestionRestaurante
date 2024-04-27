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
import java.util.Objects;

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
    @JoinColumn(name = "usuario_reserva_id", nullable = false) // nombre de la columna en la tabla Reservas que contendrá el ID del usuario de la reserva
    private Cliente cliente;

    @Column(name = "numero_mesa", nullable = false)
    private Integer numeroMesa;

    @Column(name = "dia")
    private LocalDate dia;

    @Enumerated(EnumType.STRING)
    @Column(name = "horario", nullable = false)
    private Horario horario;

    @Column(name = "numero_personas", nullable = false)
    private Integer numeroPersonas;

    //TODO: agregar empleado que hizo la reserva (QUIZAS)

    public enum Horario {
        ALMUERZO, CENA
    }

    public Reserva(Cliente cliente, Integer numeroMesa, LocalDate dia, Horario horario, Integer numeroPersonas) {
        this.cliente = cliente;
        this.numeroMesa = numeroMesa;
        this.dia = dia;
        this.horario = horario;
        this.numeroPersonas = numeroPersonas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id) && Objects.equals(cliente, reserva.cliente) && Objects.equals(numeroMesa, reserva.numeroMesa) && Objects.equals(dia, reserva.dia) && horario == reserva.horario && Objects.equals(numeroPersonas, reserva.numeroPersonas);
    }
}
