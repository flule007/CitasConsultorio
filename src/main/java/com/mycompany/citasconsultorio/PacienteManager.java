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
import java.io.*;

public class PacienteManager {
    private final String archivo = "db/pacientes.csv";

    public PacienteManager() {
        File file = new File(archivo);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println("id,nombre");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error creando archivo pacientes.csv: " + e.getMessage());
            }
        }
    }

    public void altaPacienteGUI() {
        String nombre = JOptionPane.showInputDialog(null, "Nombre del paciente:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        int id = generarId();
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(id + "," + nombre);
            JOptionPane.showMessageDialog(null, "✅ Paciente agregado con ID: " + id);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar paciente: " + e.getMessage());
        }
    }

    private int generarId() {
        int id = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); // saltar encabezado
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int current = Integer.parseInt(parts[0]);
                if (current >= id) id = current + 1;
            }
        } catch (IOException e) {}
        return id;
    }

    public void listarPacientesGUI() {
        StringBuilder sb = new StringBuilder("---- Lista de Pacientes ----\n");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); // saltar encabezado
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                sb.append("ID: ").append(parts[0]).append(", Nombre: ").append(parts[1]).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al listar pacientes: " + e.getMessage());
        }
    }
}
