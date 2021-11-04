package com.encora.seleccion.tipocambio.controller;

import com.encora.seleccion.tipocambio.request.CambiarTipoCambioRequest;
import com.encora.seleccion.tipocambio.request.ObtenerMontoTipoCambioRequest;
import com.encora.seleccion.tipocambio.response.GenericoResponse;
import com.encora.seleccion.tipocambio.service.TipoCambioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Tipo de Cambio", description = "Tipo de Cambio de Divisas.")
@Slf4j
@RestController
@RequestMapping("/v1/tipos-cambio")
@Validated
public class TipoCambioController {

    private final TipoCambioService tipoCambioService;

    @Autowired
    public TipoCambioController(TipoCambioService tipoCambioService) {
        this.tipoCambioService = tipoCambioService;
    }

    @ApiOperation(
            value = "Obtener monto de tipo de cambio",
            notes = "Este método se encarga de obtener el monto según la moneda Origen y Destino.")
    @PostMapping(value = "/obtener-monto")
    public ResponseEntity<GenericoResponse> obtenerMontoTipoCambio(
            @Valid @RequestBody ObtenerMontoTipoCambioRequest request) throws Exception {
        log.info("Inicio de obtenerMontoTipoCambio: {}", request);
        GenericoResponse response = tipoCambioService.obtenerMontoTipoCambio(request);
        log.info("Fin de obtenerMontoTipoCambio {}", response.getMensaje());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Actualizar valor del tipo de cambio.",
            notes = "Este método se encarga de actualizar el valor del tipo de cambio.")
    @PutMapping(value = "/tipo-cambio/{id}")
    public ResponseEntity<GenericoResponse> actualizarValorTipoCambio(
            @ApiParam(value = "Id de tipo de cambio", required = true)
            @PathVariable("id") Integer id,
            @Valid @RequestBody CambiarTipoCambioRequest request) throws Exception {
        log.info("Inicio de actualizarValorTipoCambio: id: {} , request: {}", id, request);
        GenericoResponse response = tipoCambioService.actualizarValorTipoCambio(id, request);
        log.info("Fin de actualizarValorTipoCambio {}", response.getMensaje());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
