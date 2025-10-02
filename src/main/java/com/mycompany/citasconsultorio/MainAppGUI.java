/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author flule
 */
package com.mycompany.citasconsultorio;

import javax.swing.JOptionPane;

public class MainAppGUI {
    public static void main(String[] args) {
        AdminManager adminManager = new AdminManager();
        DoctorManager doctorManager = new DoctorManager();
        PacienteManager pacienteManager = new PacienteManager();
        CitaManager citaManager = new CitaManager(doctorManager, pacienteManager);

        boolean acceso = false;

        // Login
        while (!acceso) {
            String user = JOptionPane.showInputDialog(null, "Usuario:");
            String pass = JOptionPane.showInputDialog(null, "Password:");

            if (user == null || pass == null || user.trim().isEmpty() || pass.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña no puede estar vacío.");
                continue;
            }

            acceso = adminManager.login(user.trim(), pass.trim());
            if (!acceso) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Intenta de nuevo.");
            }
        }

        JOptionPane.showMessageDialog(null, "✅ Acceso concedido.");

        // Menú principal
        int opcion = -1;
        String menu = "===== Menú Principal =====\n"
                + "1. Dar de alta doctor\n"
                + "2. Dar de alta paciente\n"
                + "3. Crear cita\n"
                + "4. Listar doctores\n"
                + "5. Listar pacientes\n"
                + "6. Listar citas\n"
                + "7. Salir";

        while (opcion != 7) {
            try {
                String input = JOptionPane.showInputDialog(null, menu + "\n\nElige una opción:");
                if (input == null) break; // usuario canceló
                opcion = Integer.parseInt(input.trim());

                switch (opcion) {
                    case 1:
                        doctorManager.altaDoctorGUI();
                        break;
                    case 2:
                        pacienteManager.altaPacienteGUI();
                        break;
                    case 3:
                        citaManager.crearCitaGUI();
                        break;
                    case 4:
                        doctorManager.listarDoctoresGUI();
                        break;
                    case 5:
                        pacienteManager.listarPacientesGUI();
                        break;
                    case 6:
                        citaManager.listarCitasGUI();
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "Saliendo del sistema... ✅");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un número válido.");
            }
        }
    }
}
