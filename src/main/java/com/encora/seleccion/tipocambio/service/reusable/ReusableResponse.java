package com.encora.seleccion.tipocambio.service.reusable;

import com.encora.seleccion.tipocambio.exception.GenericException;
import com.encora.seleccion.tipocambio.response.GenericoResponse;
import com.encora.seleccion.tipocambio.util.CodigoHttpEnum;
import com.encora.seleccion.tipocambio.message.GlobalMessages;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class ReusableResponse {

    protected GlobalMessages globalMessages;
    protected JsonNode emptyObject;

    protected void excepcionGenerica(String mensaje, int codigoError) throws Exception {
        throw new GenericException(mensaje, codigoError);
    }

    protected void procesarExito(final GenericoResponse response) {
        response.setRespuesta(response.getRespuesta() != null ? response.getRespuesta() : emptyObject);
        response.setMensaje(response.getMensaje() != null && !response.getMensaje().isEmpty() ?
                response.getMensaje() : globalMessages.msgProcesoExitoso());
        response.setCodigoOperacion(CodigoHttpEnum.EXITO.getCodigo());
    }

    protected GenericoResponse procesarRespuesta(final Object respuesta, final String mensaje) {
        GenericoResponse response = new GenericoResponse();
        response.setRespuesta(respuesta);
        response.setMensaje(mensaje);
        procesarExito(response);
        return response;
    }

    protected GenericoResponse procesarRespuesta(final String mensaje) {
        GenericoResponse response = new GenericoResponse();
        response.setMensaje(mensaje);
        procesarExito(response);
        return response;
    }

    @Autowired
    public void setGlobalMessages(GlobalMessages globalMessages) {
        this.globalMessages = globalMessages;
    }

    @Autowired
    public void setEmptyObject(JsonNode emptyObject) {
        this.emptyObject = emptyObject;
    }
}
