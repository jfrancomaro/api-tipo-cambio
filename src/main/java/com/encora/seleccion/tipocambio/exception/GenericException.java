package com.encora.seleccion.tipocambio.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericException extends Exception {

    private int codigoError;
    private Object tipoReturn;

    public GenericException(String mensaje, int codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    public GenericException(String mensaje, int codigoError, Object tipoReturn) {
        super(mensaje);
        this.codigoError = codigoError;
        this.tipoReturn = tipoReturn;
    }
}