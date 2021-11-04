CREATE TABLE TIPO_CAMBIO (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(100) NOT NULL,
    ABREVIATURA VARCHAR(3) NOT NULL,
    VALOR DECIMAL(6,4) NOT NULL,
    PRIMARY KEY (ID)
);