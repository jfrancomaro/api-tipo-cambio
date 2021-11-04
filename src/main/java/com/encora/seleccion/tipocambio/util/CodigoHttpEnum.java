package com.encora.seleccion.tipocambio.util;

public enum CodigoHttpEnum {

    EXITO(200, "Proceso exitoso"),
    NO_ENCONTRADO(404, "No encontrado"),
    ERROR_SERVIDOR_INTERNO(500, "Error en el servidor"),
    REGISTRO_EXISTENTE(616, "El registro ya ha sido procesado"),
    PROCESO_NO_EXITOSO(617, "El proceso no fue exitoso"),
    REGISTRO_NO_EXITOSO(620, "El proceso de registro no fue exitoso");

    private int codigo;
    private String descripcion;

    CodigoHttpEnum(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
