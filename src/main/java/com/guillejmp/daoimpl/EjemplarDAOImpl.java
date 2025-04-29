package com.guillejmp.daoimpl;

import com.guillejmp.dao.EjemplarDAO;
import com.guillejmp.modelo.Ejemplar;
import com.guillejmp.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EjemplarDAOImpl implements EjemplarDAO {
    private static final String SEL = "SELECT * FROM Ejemplar WHERE codigo=?";
    private static final String INS = "INSERT INTO Ejemplar(nombre,codigo) VALUES(?,?)";

    @Override
    public List<Ejemplar> findByPlantaCodigo(String cod) throws Exception {
        List<Ejemplar> list = new ArrayList<>();
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(SEL);
        ps.setString(1, cod);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Ejemplar(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("codigo")
            ));
        }
        rs.close();
        ps.close();
        return list;
    }

    @Override
    public long insert(Ejemplar e) throws Exception {
        long id = 0;
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(INS, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, e.getNombre());
        ps.setString(2, e.getCodigo());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getLong(1);
        }
        rs.close();
        ps.close();
        return id;
    }
}