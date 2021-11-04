package com.encora.seleccion.tipocambio.message;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class GlobalMessages {

    private final MessageSource messageSource;

    private MessageSourceAccessor accessor;

    public GlobalMessages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public String msgProcesoExitoso() {
        return accessor.getMessage("msg.proceso.exitoso");
    }

    public String msgRegistroNoEncontrado(String valor) {
        return accessor.getMessage("msg.regitro.no.encontrado", new Object[]{valor});
    }

    public String msgErrorGeneral() {
        return accessor.getMessage("msg.error.general");
    }

    public String msgValidacionRequestNoExitosa(String valorUno, String valorDos) {
        return accessor.getMessage("msg.validacion.error", new Object[]{valorUno, valorDos});
    }

    public String msgActualizacionExitosa(String valor) {
        return accessor.getMessage("msg.actualizacion.exitosa", new Object[]{valor});
    }
}
