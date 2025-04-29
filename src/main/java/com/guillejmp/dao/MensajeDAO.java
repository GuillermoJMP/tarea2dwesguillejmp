package com.guillejmp.dao;

import com.guillejmp.modelo.Mensaje;
import java.util.List;

public interface MensajeDAO {
    List<Mensaje> findByEjemplarId(Long id) throws Exception;
    boolean insert(Mensaje m) throws Exception;
}