package com.guillejmp.dao;

import com.guillejmp.modelo.Ejemplar;
import java.util.List;

public interface EjemplarDAO {
    List<Ejemplar> findByPlantaCodigo(String codigo) throws Exception;
    long insert(Ejemplar e) throws Exception;
}