package com.guillejmp.daoimpl;

import com.guillejmp.dao.PlantaDAO;
import com.guillejmp.modelo.Planta;
import com.guillejmp.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlantaDAOImpl implements PlantaDAO {
    private static final String SEL = "SELECT * FROM Planta";
    private static final String INS = "INSERT INTO Planta(codigo,nombre_comun,nombre_cientifico) VALUES(?,?,?)";
    private static final String UPD = "UPDATE Planta SET nombre_comun=?,nombre_cientifico=? WHERE codigo=?";

    @Override
    public List<Planta> findAll() throws Exception {
        List<Planta> list = new ArrayList<>();
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(SEL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Planta(
                rs.getString("codigo"),
                rs.getString("nombre_comun"),
                rs.getString("nombre_cientifico")
            ));
        }
        rs.close();
        ps.close();
        return list;
    }

    @Override
    public boolean insert(Planta p) throws Exception {
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(INS);
        ps.setString(1, p.getCodigo());
        ps.setString(2, p.getNombrecomun());
        ps.setString(3, p.getNombrecientifico());
        boolean ok = ps.executeUpdate() == 1;
        ps.close();
        return ok;
    }

    @Override
    public boolean update(Planta p) throws Exception {
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(UPD);
        ps.setString(1, p.getNombrecomun());
        ps.setString(2, p.getNombrecientifico());
        ps.setString(3, p.getCodigo());
        boolean ok = ps.executeUpdate() == 1;
        ps.close();
        return ok;
    }
}