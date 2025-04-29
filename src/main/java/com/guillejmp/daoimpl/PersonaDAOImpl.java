package com.guillejmp.daoimpl;

import com.guillejmp.dao.PersonaDAO;
import com.guillejmp.modelo.Persona;
import com.guillejmp.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PersonaDAOImpl implements PersonaDAO {
    private static final String SEL = "SELECT * FROM Persona WHERE id = ?";
    private static final String INS =
        "INSERT INTO Persona(nombre, correo) VALUES(?, ?)";

    @Override
    public Persona findById(Long id) throws Exception {
        Persona p = null;
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(SEL);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = new Persona();
            p.setId(rs.getLong("id"));
            p.setNombre(rs.getString("nombre"));
            p.setEmail(rs.getString("correo"));
        }
        rs.close();
        ps.close();
        return p;
    }

    @Override
    public boolean insert(Persona p) throws Exception {
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(INS, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getEmail());
        int affected = ps.executeUpdate();

        if (affected == 1) {
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                p.setId(keys.getLong(1));
            }
            keys.close();
        }

        ps.close();
        return affected == 1;
    }
}