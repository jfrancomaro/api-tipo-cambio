package com.encora.seleccion.tipocambio.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericoResponse {

    private Object respuesta;
    private String mensaje;
    private int codigoOperacion;
}
