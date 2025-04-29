package com.guillejmp.daoimpl;

import com.guillejmp.dao.CredencialesDAO;
import com.guillejmp.modelo.Credenciales;
import com.guillejmp.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CredencialesDAOImpl implements CredencialesDAO {
    private static final String SEL =
        "SELECT * FROM Credenciales WHERE usuario = ?";
    private static final String INS =
        "INSERT INTO Credenciales(usuario, password, id_persona) VALUES(?, ?, ?)";

    @Override
    public Credenciales findByUsuario(String u) throws Exception {
        Credenciales cr = null;
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(SEL);
        ps.setString(1, u);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cr = new Credenciales();
            cr.setId(rs.getLong("id"));
            cr.setUsuario(rs.getString("usuario"));
            cr.setPassword(rs.getString("password"));
            cr.setId_persona(rs.getLong("id_persona"));
        }
        rs.close();
        ps.close();
        return cr;
    }

    @Override
    public boolean insert(Credenciales c2) throws Exception {
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(INS);
        ps.setString(1, c2.getUsuario());
        ps.setString(2, c2.getPassword());
        ps.setLong(3, c2.getId_persona());
        boolean ok = ps.executeUpdate() == 1;
        ps.close();
        return ok;
    }
}