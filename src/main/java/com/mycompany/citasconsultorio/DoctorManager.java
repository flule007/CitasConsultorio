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

public class DoctorManager {
    private final String archivo = "db/doctores.csv";

    public DoctorManager() {
        File file = new File(archivo);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println("id,nombre,especialidad");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error creando archivo doctores.csv: " + e.getMessage());
            }
        }
    }

    public void altaDoctorGUI() {
        String nombre = JOptionPane.showInputDialog(null, "Nombre del doctor:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String especialidad = JOptionPane.showInputDialog(null, "Especialidad:");
        if (especialidad == null || especialidad.trim().isEmpty()) return;

        int id = generarId();
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(id + "," + nombre + "," + especialidad);
            JOptionPane.showMessageDialog(null, "✅ Doctor agregado con ID: " + id);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar doctor: " + e.getMessage());
        }
    }

    private int generarId() {
        int id = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            br.readLine(); // saltar encabezado
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int current = Integer.parseInt(parts[0]);
                if (current >= id) id = current + 1;
            }
        } catch (IOException e) {}
        return id;
    }

    public void listarDoctoresGUI() {
        StringBuilder sb = new StringBuilder("---- Lista de Doctores ----\n");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); // saltar encabezado
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                sb.append("ID: ").append(parts[0]).append(", Nombre: ").append(parts[1])
                  .append(", Especialidad: ").append(parts[2]).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al listar doctores: " + e.getMessage());
        }
    }
}
