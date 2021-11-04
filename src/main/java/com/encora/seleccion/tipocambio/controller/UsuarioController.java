package com.encora.seleccion.tipocambio.controller;

import com.encora.seleccion.tipocambio.response.GenericoResponse;
import com.encora.seleccion.tipocambio.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Usuario", description = "Login de Usuarios.")
@Slf4j
@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ApiOperation(
            value = "Iniciar Sesión",
            notes = "Este método se encarga de obtener el token mediante usuario y clave.")
    @PostMapping("/login")
    public ResponseEntity<GenericoResponse> login(
            @ApiParam(value = "Usuario", required = true)
            @RequestParam("usuario") String usuario,
            @ApiParam(value = "Clave", required = true)
            @RequestParam("clave") String clave) throws Exception {

        log.info("Inicio de login: usuario {}, clave {}", usuario, clave);
        GenericoResponse response = usuarioService.login(usuario, clave);
        log.info("Fin de login {}", response.getMensaje());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
