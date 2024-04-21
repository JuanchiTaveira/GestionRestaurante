package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Mesas")
public class Mesa {

    @Id
    @Column(name = "numero_mesa")
    private Integer numeroMesa;

    @Column(name = "max_personas")
    private Integer maxPersonas;
}
