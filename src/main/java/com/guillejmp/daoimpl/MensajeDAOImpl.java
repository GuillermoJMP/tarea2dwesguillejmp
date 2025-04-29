package com.guillejmp.daoimpl;

import com.guillejmp.dao.MensajeDAO;
import com.guillejmp.modelo.Mensaje;
import com.guillejmp.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MensajeDAOImpl implements MensajeDAO {
    private static final String SEL = "SELECT * FROM Mensaje WHERE id_ejemplar=?";
    private static final String INS = "INSERT INTO Mensaje(fecha_hora,mensaje,id_persona,id_ejemplar) VALUES(?,?,?,?)";

    @Override
    public List<Mensaje> findByEjemplarId(Long id) throws Exception {
        List<Mensaje> list = new ArrayList<>();
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(SEL);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Mensaje m = new Mensaje();
            m.setId(rs.getLong("id"));
            m.setFechahora(rs.getDate("fechahora"));
            m.setMensaje(rs.getString("mensaje"));
            m.setId_persona(rs.getLong("id_persona"));
            m.setId_ejemplar(rs.getLong("id_ejemplar"));
            list.add(m);
        }
        rs.close();
        ps.close();
        return list;
    }

    @Override
    public boolean insert(Mensaje m) throws Exception {
        Connection c = ConexionDB.getConnection();
        PreparedStatement ps = c.prepareStatement(INS);
        ps.setTimestamp(1, new Timestamp(m.getFechahora().getTime()));
        ps.setString(2, m.getMensaje());
        ps.setLong(3, m.getId_persona());
        ps.setLong(4, m.getId_ejemplar());
        boolean ok = ps.executeUpdate() == 1;
        ps.close();
        return ok;
    }
}