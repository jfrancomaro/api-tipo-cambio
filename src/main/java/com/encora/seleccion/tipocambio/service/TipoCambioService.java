package com.encora.seleccion.tipocambio.service;

import com.encora.seleccion.tipocambio.request.CambiarTipoCambioRequest;
import com.encora.seleccion.tipocambio.request.ObtenerMontoTipoCambioRequest;
import com.encora.seleccion.tipocambio.response.GenericoResponse;

public interface TipoCambioService {

    GenericoResponse obtenerMontoTipoCambio(ObtenerMontoTipoCambioRequest request) throws Exception;

    GenericoResponse actualizarValorTipoCambio(Integer idTipoCambio, CambiarTipoCambioRequest request) throws Exception;
}
