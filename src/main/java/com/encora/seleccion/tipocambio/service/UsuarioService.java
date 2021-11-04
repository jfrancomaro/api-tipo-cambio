package com.encora.seleccion.tipocambio.service;

import com.encora.seleccion.tipocambio.response.GenericoResponse;

public interface UsuarioService {

    GenericoResponse login(String usuario, String clave) throws Exception;
}
