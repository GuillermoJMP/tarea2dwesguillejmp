package com.guillejmp.dao;

import com.guillejmp.modelo.Planta;
import java.util.List;

public interface PlantaDAO {
    List<Planta> findAll() throws Exception;
    boolean insert(Planta p) throws Exception;
    boolean update(Planta p) throws Exception;
}