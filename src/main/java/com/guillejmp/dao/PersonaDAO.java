package com.guillejmp.dao;

import com.guillejmp.modelo.Persona;

public interface PersonaDAO {
    Persona findById(Long id) throws Exception;
    boolean insert(Persona p) throws Exception;
}