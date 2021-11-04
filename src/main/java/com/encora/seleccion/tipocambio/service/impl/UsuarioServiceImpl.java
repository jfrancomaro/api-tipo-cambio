package com.encora.seleccion.tipocambio.service.impl;

import com.encora.seleccion.tipocambio.dto.UsuarioDTO;
import com.encora.seleccion.tipocambio.response.GenericoResponse;
import com.encora.seleccion.tipocambio.service.UsuarioService;
import com.encora.seleccion.tipocambio.service.reusable.ReusableResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl extends ReusableResponse implements UsuarioService {

    @Override
    public GenericoResponse login(String usuario, String clave) {
        String token = getJWTToken(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuario(usuario);
        usuarioDTO.setClave(clave);
        usuarioDTO.setToken(token);
        return procesarRespuesta(usuarioDTO, globalMessages.msgProcesoExitoso());
    }

    private String getJWTToken(String usuario) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("encora")
                .setSubject(usuario)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
