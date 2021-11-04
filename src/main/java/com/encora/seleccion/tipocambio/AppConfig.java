package com.encora.seleccion.tipocambio;

import com.encora.seleccion.tipocambio.message.GlobalMessages;
import com.encora.seleccion.tipocambio.response.GenericoResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

import static com.encora.seleccion.tipocambio.util.CodigoHttpEnum.ERROR_SERVIDOR_INTERNO;

@Configuration
public class AppConfig {

    //@Value("${token.acceso.ose}")
    @Value("2")
    private String tokenAccesoOse;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("classpath:message");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }

    @Bean
    public GlobalMessages globalMessages() {
        return new GlobalMessages(messageSource());
    }

    @Bean
    public JsonNode emptyObject() throws Exception {
        return new ObjectMapper().readTree("{}");
    }

    @Bean
    public List emptyList() {
        return new ArrayList();
    }

    @Bean
    public GenericoResponse errorGenericResponse() throws Exception {
        GenericoResponse response = new GenericoResponse();
        response.setRespuesta(emptyObject());
        response.setMensaje(globalMessages().msgErrorGeneral());
        response.setCodigoOperacion(ERROR_SERVIDOR_INTERNO.getCodigo());
        return response;
    }

    @Bean
    public HttpEntity<HttpHeaders> requestEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("origen", "1");
        httpHeaders.set("key", "123");
        httpHeaders.set("ruc", "123");
        return new HttpEntity<>(httpHeaders);
    }

    @Lazy
    @Bean
    public HttpEntity<HttpHeaders> requestEntityOse() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + tokenAccesoOse);
        return new HttpEntity<>(httpHeaders);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Lazy
    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=test.xlsx");
        return headers;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
