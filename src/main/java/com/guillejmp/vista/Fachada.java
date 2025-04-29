package com.guillejmp.vista;

import com.guillejmp.controlador.Controlador;
import com.guillejmp.modelo.Planta;

import java.util.List;
import java.util.Scanner;

public class Fachada {
    private final Controlador ctrl = Controlador.getInstancia();
    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            muestraMenu();
            String op = sc.nextLine().trim();
            try {
                if (ctrl.getUsuarioLogueado() == null) {
                    switch (op) {
                        case "1": verPlantas();      break;
                        case "2": login();           break;
                        case "3": registrarse();     break;
                        case "0": return;
                        default: System.out.println("Opción inválida.");
                    }
                } else {
                    switch (op) {
                        case "1": verPlantas();      break;
                        case "2": logout();          break;
                        case "4": crearPlanta();     break;
                        case "5": crearEjemplar();   break;
                        case "6": anotarMensaje();   break;
                        case "0": return;
                        default: System.out.println("Opción inválida.");
                    }
                }
            } catch (Exception e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        }
    }

    private void muestraMenu() {
        System.out.println();
        if (ctrl.getUsuarioLogueado() == null) {
            System.out.println("1) Ver plantas   2) Login   3) Registrarse   0) Salir");
        } else {
            System.out.println("1) Ver plantas   2) Logout   4) Nueva planta   5) Nuevo ejemplar   6) Anotar mensaje   0) Salir");
        }
        System.out.print("> ");
    }

    private void verPlantas() throws Exception {
        List<Planta> plantas = ctrl.listarPlantas();
        if (plantas.isEmpty()) {
            System.out.println("No hay plantas registradas.");
        } else {
            System.out.println("\nListado de plantas:");
            for (Planta p : plantas) {
                System.out.printf("- [%s] %s (%s)%n",
                    p.getCodigo(), p.getNombrecomun(), p.getNombrecientifico());
            }
        }
    }

    private void login() throws Exception {
        System.out.print("Usuario: ");
        String u = sc.nextLine().trim();
        System.out.print("Password: ");
        String p = sc.nextLine().trim();
        if (ctrl.login(u, p)) {
            System.out.println("Login OK. Bienvenido, " + ctrl.getUsuarioLogueado().getNombre());
        } else {
            System.out.println("Credenciales inválidas.");
        }
    }

    private void logout() {
        ctrl.logout();
        System.out.println("Sesión cerrada.");
    }

    private void registrarse() throws Exception {
        System.out.print("Nombre: ");
        String n = sc.nextLine().trim();
        System.out.print("Correo: ");
        String e = sc.nextLine().trim();
        System.out.print("Usuario: ");
        String u = sc.nextLine().trim();
        System.out.print("Password: ");
        String p = sc.nextLine().trim();
        if (ctrl.registrar(n, e, u, p)) {
            System.out.println("Registro OK.");
        } else {
            System.out.println("Error al registrar.");
        }
    }

    private void crearPlanta() throws Exception {
        System.out.print("Código planta: ");
        String c = sc.nextLine().trim();
        System.out.print("Nombre común: ");
        String nc = sc.nextLine().trim();
        System.out.print("Nombre científico: ");
        String ci = sc.nextLine().trim();
        if (ctrl.nuevaPlanta(c, nc, ci)) {
            System.out.println("Planta creada.");
        } else {
            System.out.println("Error al crear planta.");
        }
    }

    private void crearEjemplar() throws Exception {
        System.out.print("Nombre ejemplar: ");
        String ne = sc.nextLine().trim();
        System.out.print("Código planta: ");
        String cp = sc.nextLine().trim();
        long id = ctrl.nuevoEjemplar(ne, cp);
        if (id > 0) {
            System.out.println("Ejemplar creado con ID=" + id);
        } else {
            System.out.println("Error al crear ejemplar.");
        }
    }

    private void anotarMensaje() throws Exception {
        System.out.print("ID ejemplar: ");
        long ie = Long.parseLong(sc.nextLine().trim());
        System.out.print("ID persona: ");
        long ip = Long.parseLong(sc.nextLine().trim());
        System.out.print("Texto mensaje: ");
        String txt = sc.nextLine().trim();
        if (ctrl.anotarMensaje(ie, ip, txt)) {
            System.out.println("Mensaje guardado.");
        } else {
            System.out.println("Error al guardar mensaje.");
        }
    }
}