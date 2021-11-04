package com.encora.seleccion.tipocambio.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class CambiarTipoCambioRequest {

//    @ApiModelProperty(required = true)
//    @NotNull(message = "Moneda requerida.")
//    @NotBlank(message = "Moneda no debe estar vacía.")
//    @Size(min = 3, max = 3, message = "La longitud de la moneda debe ser de 3 caracteres.")
//    private String moneda;

    @ApiModelProperty(required = true)
    @NotNull(message = "Valor del tipo de cambio requerido.")
    @Positive(message = "Valor del tipo de cambio ser positivo.")
    private BigDecimal valor;
}
