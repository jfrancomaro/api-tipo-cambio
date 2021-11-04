package com.encora.seleccion.tipocambio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tipo_cambio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false, name = "nombre")
    private String nombre;

    @Column(length = 3, nullable = false, name = "abreviatura")
    private String abreviatura;

    @Column(scale = 6, precision = 4 ,nullable = false, name = "valor")
    private BigDecimal valor;
}
