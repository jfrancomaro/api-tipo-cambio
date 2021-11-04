package com.encora.seleccion.tipocambio.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TipoCambioDTO {

    private BigDecimal monto;
    private BigDecimal montoTipoCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
}
