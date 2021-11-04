package com.encora.seleccion.tipocambio.service.reusable;

import static com.encora.seleccion.tipocambio.util.CodigoHttpEnum.NO_ENCONTRADO;
import static com.encora.seleccion.tipocambio.util.ServiceUtil.TIPO_CAMBIO;

public abstract class ReusableValidacion extends ReusableResponse {

    protected void validarResultadoBusqueda(Object object) throws Exception {
        if (object == null) {
            excepcionGenerica(
                    globalMessages.msgRegistroNoEncontrado(TIPO_CAMBIO),
                    NO_ENCONTRADO.getCodigo());
        }
    }
}