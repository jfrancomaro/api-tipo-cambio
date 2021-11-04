package com.encora.seleccion.tipocambio.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ObtenerMontoTipoCambioRequest {

    @ApiModelProperty(required = true)
    @NotNull(message = "Monto requerido.")
    @Positive(message = "El Monto debe ser positivo.")
    private BigDecimal monto;

    @ApiModelProperty(required = true)
    @NotNull(message = "Moneda origen requerida.")
    @NotBlank(message = "Moneda origen no debe estar vacía.")
    @Size(min = 3, max = 3, message = "La longitud de la moneda origen debe ser de 3 caracteres.")
    private String monedaOrigen;

    @ApiModelProperty(required = true)
    @NotNull(message = "Moneda destino requerida.")
    @NotBlank(message = "Moneda destino no debe estar vacía.")
    @Size(min = 3, max = 3, message = "La longitud de la moneda destino debe ser de 3 caracteres.")
    private String monedaDestino;
}
