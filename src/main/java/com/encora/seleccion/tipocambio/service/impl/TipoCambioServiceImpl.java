package com.encora.seleccion.tipocambio.service.impl;

import com.encora.seleccion.tipocambio.dto.TipoCambioDTO;
import com.encora.seleccion.tipocambio.exception.GenericException;
import com.encora.seleccion.tipocambio.model.TipoCambio;
import com.encora.seleccion.tipocambio.repository.TipoCambioRepository;
import com.encora.seleccion.tipocambio.request.CambiarTipoCambioRequest;
import com.encora.seleccion.tipocambio.request.ObtenerMontoTipoCambioRequest;
import com.encora.seleccion.tipocambio.response.GenericoResponse;
import com.encora.seleccion.tipocambio.service.TipoCambioService;
import com.encora.seleccion.tipocambio.service.reusable.ReusableValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.encora.seleccion.tipocambio.util.CodigoHttpEnum.NO_ENCONTRADO;
import static com.encora.seleccion.tipocambio.util.ServiceUtil.TIPO_CAMBIO;

@Service
public class TipoCambioServiceImpl extends ReusableValidacion implements TipoCambioService {

    private final TipoCambioRepository tipoCambioRepository;

    @Autowired
    public TipoCambioServiceImpl(TipoCambioRepository tipoCambioRepository) {
        this.tipoCambioRepository = tipoCambioRepository;
    }

    @Override
    public GenericoResponse obtenerMontoTipoCambio(ObtenerMontoTipoCambioRequest request) throws Exception {

        TipoCambio tipoCambioOrigen = tipoCambioRepository.findByAbreviatura(request.getMonedaOrigen());
        validarResultadoBusqueda(tipoCambioOrigen);

        TipoCambio tipoCambioDestino = tipoCambioRepository.findByAbreviatura(request.getMonedaDestino());
        validarResultadoBusqueda(tipoCambioDestino);

        TipoCambioDTO tipoCambioDTO = new TipoCambioDTO();

        tipoCambioDTO.setMonto(request.getMonto());
        tipoCambioDTO.setMonedaOrigen(request.getMonedaOrigen());
        tipoCambioDTO.setMonedaDestino(request.getMonedaDestino());

        BigDecimal tipoCambio = tipoCambioDestino.getValor().divide(tipoCambioOrigen.getValor(), 4, RoundingMode.HALF_UP);

        tipoCambioDTO.setTipoCambio(tipoCambio.setScale(4, RoundingMode.HALF_UP));

        BigDecimal montoTipoCambio = tipoCambio.multiply(tipoCambioDTO.getMonto());

        tipoCambioDTO.setMontoTipoCambio(montoTipoCambio.setScale(2, RoundingMode.HALF_UP));

        return procesarRespuesta(tipoCambioDTO, globalMessages.msgProcesoExitoso());
    }

    @Override
    public GenericoResponse actualizarValorTipoCambio(Integer idTipoCambio, CambiarTipoCambioRequest request) throws Exception {

        TipoCambio tipoCambio = tipoCambioRepository.findById(idTipoCambio)
                .orElseThrow(() ->
                        new GenericException(
                                globalMessages.msgRegistroNoEncontrado(TIPO_CAMBIO),
                                NO_ENCONTRADO.getCodigo())
                );

        tipoCambio.setValor(request.getValor());

        tipoCambioRepository.save(tipoCambio);

        return procesarRespuesta(globalMessages.msgActualizacionExitosa(TIPO_CAMBIO));
    }
}
