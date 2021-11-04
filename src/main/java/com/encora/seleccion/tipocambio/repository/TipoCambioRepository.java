package com.encora.seleccion.tipocambio.repository;

import com.encora.seleccion.tipocambio.model.TipoCambio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCambioRepository extends CrudRepository<TipoCambio, Integer> {

    TipoCambio findByAbreviatura(String abreviatura) throws Exception;
}
