package com.encora.seleccion.tipocambio.exception;

import com.encora.seleccion.tipocambio.message.GlobalMessages;
import com.encora.seleccion.tipocambio.response.GenericoResponse;
import com.encora.seleccion.tipocambio.util.ServiceUtil;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final GenericoResponse errorGenericResponse;
    private final JsonNode emptyObject;
    private final GlobalMessages globalMessages;

    @Autowired
    public ResponseExceptionHandler(GenericoResponse errorGenericResponse, JsonNode emptyObject, GlobalMessages globalMessages) {
        this.errorGenericResponse = errorGenericResponse;
        this.emptyObject = emptyObject;
        this.globalMessages = globalMessages;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<Object> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        GenericoResponse response = procesarError(
                globalMessages.msgValidacionRequestNoExitosa(
                        ServiceUtil.CADENA_VACIA,
                        fieldErrors.toString()
                ),
                status.value(),
                emptyObject
        );
        log.warn(globalMessages.msgValidacionRequestNoExitosa(methodArgumentNotValidException.getBindingResult().getObjectName(), fieldErrors.toString()));
        log.warn(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(GenericException.class)
    public final ResponseEntity<GenericoResponse> manejarGenericException(GenericException genericException) {
        log.warn(genericException.getMessage(), genericException);
        GenericoResponse response = procesarError(genericException.getMessage(),
                genericException.getCodigoError(), genericException.getTipoReturn());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<GenericoResponse> manejarException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorGenericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GenericoResponse procesarError(String mensaje, int codigoOperacion, Object object) {
        GenericoResponse response = new GenericoResponse();
        response.setRespuesta(object != null ? object : emptyObject);
        response.setMensaje(mensaje);
        response.setCodigoOperacion(codigoOperacion);
        return response;
    }
}
