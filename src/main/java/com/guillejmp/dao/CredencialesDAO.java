package com.guillejmp.dao;

import com.guillejmp.modelo.Credenciales;

public interface CredencialesDAO {
    Credenciales findByUsuario(String usu) throws Exception;
    boolean insert(Credenciales c) throws Exception;
}