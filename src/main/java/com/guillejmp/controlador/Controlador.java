package com.guillejmp.controlador;

import com.guillejmp.dao.PlantaDAO;
import com.guillejmp.dao.PersonaDAO;
import com.guillejmp.dao.CredencialesDAO;
import com.guillejmp.dao.EjemplarDAO;
import com.guillejmp.dao.MensajeDAO;
import com.guillejmp.daoimpl.PlantaDAOImpl;
import com.guillejmp.daoimpl.PersonaDAOImpl;
import com.guillejmp.daoimpl.CredencialesDAOImpl;
import com.guillejmp.daoimpl.EjemplarDAOImpl;
import com.guillejmp.daoimpl.MensajeDAOImpl;
import com.guillejmp.modelo.Credenciales;
import com.guillejmp.modelo.Persona;
import com.guillejmp.modelo.Planta;
import com.guillejmp.modelo.Ejemplar;
import com.guillejmp.modelo.Mensaje;

import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class Controlador {
    private static Controlador INST;
    private PlantaDAO pd = new PlantaDAOImpl();
    private PersonaDAO od = new PersonaDAOImpl();
    private CredencialesDAO cd = new CredencialesDAOImpl();
    private EjemplarDAO ed = new EjemplarDAOImpl();
    private MensajeDAO md = new MensajeDAOImpl();
    private Persona user;

    private Controlador() { }

    public static Controlador getInstancia() {
        if (INST == null) {
            INST = new Controlador();
        }
        return INST;
    }

    /** CU2: Login */
    public boolean login(String usuario, String pass) throws Exception {
        Credenciales cr = cd.findByUsuario(usuario);
        if (cr != null && cr.getPassword().equals(pass)) {
            user = od.findById(cr.getId_persona());
            return true;
        }
        return false;
    }

     /** Cierra la sesión actual */
     public void logout() {
         user = null;
     }

     /** Permite recuperar quién está logueado */
     public Persona getUsuarioLogueado() {
         return user;
     }

 // … resto de métodos …

    /** CU1: Listar todas las plantas */
    public List<Planta> listarPlantas() throws Exception {
        List<Planta> lista = pd.findAll();
        lista.sort(Comparator.comparing(Planta::getCodigo));
        return lista;
    }

    /** CU3: Registrar nueva persona + credenciales */
    public boolean registrar(String nombre, String email,
                             String usuario, String pass) throws Exception {
        Persona p = new Persona();
        p.setNombre(nombre);
        p.setEmail(email);
        if (!od.insert(p)) return false;

        long idPersona = p.getId();

        Credenciales c = new Credenciales();
        c.setUsuario(usuario);
        c.setPassword(pass);
        c.setId_persona(idPersona);
        return cd.insert(c);
    }

    /** CU4: Crear nueva planta */
    public boolean nuevaPlanta(String codigo, String comun, String cientifico) throws Exception {
        Planta pl = new Planta(codigo, comun, cientifico);
        return pd.insert(pl);
    }

    /** CU5: Crear nuevo ejemplar */
    public long nuevoEjemplar(String nombre, String codigoPlanta) throws Exception {
        Ejemplar e = new Ejemplar();
        e.setNombre(nombre);
        e.setCodigo(codigoPlanta);
        return ed.insert(e);
    }

    /** CU6: Anotar mensaje en un ejemplar */
    public boolean anotarMensaje(long idEjemplar, long idPersona, String texto) throws Exception {
        Mensaje m = new Mensaje();
        m.setFechahora(new Date());
        m.setMensaje(texto);
        m.setId_persona(idPersona);
        m.setId_ejemplar(idEjemplar);
        return md.insert(m);
    }
}