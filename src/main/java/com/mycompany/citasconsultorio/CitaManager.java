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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CitaManager {
    private final String archivo = "db/citas.csv";
    private DoctorManager doctorManager;
    private PacienteManager pacienteManager;

    public CitaManager(DoctorManager dm, PacienteManager pm) {
        this.doctorManager = dm;
        this.pacienteManager = pm;

        File file = new File(archivo);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println("id,fechaHora,motivo,doctorId,pacienteId");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error creando archivo citas.csv: " + e.getMessage());
            }
        }
    }

    public void crearCitaGUI() {
        try {
            String fechaStr = JOptionPane.showInputDialog(null, "Fecha y hora (yyyy-MM-dd HH:mm):");
            if (fechaStr == null || fechaStr.trim().isEmpty()) return;
            LocalDateTime fechaHora;
            try {
                fechaHora = LocalDateTime.parse(fechaStr.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha/hora incorrecto.");
                return;
            }

            String motivo = JOptionPane.showInputDialog(null, "Motivo de la cita:");
            if (motivo == null || motivo.trim().isEmpty()) return;

            String doctorIdStr = JOptionPane.showInputDialog(null, "ID del doctor:");
            if (doctorIdStr == null || doctorIdStr.trim().isEmpty()) return;
            int doctorId = Integer.parseInt(doctorIdStr.trim());

            String pacienteIdStr = JOptionPane.showInputDialog(null, "ID del paciente:");
            if (pacienteIdStr == null || pacienteIdStr.trim().isEmpty()) return;
            int pacienteId = Integer.parseInt(pacienteIdStr.trim());

            int id = generarId();
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
                pw.println(id + "," + fechaHora + "," + motivo + "," + doctorId + "," + pacienteId);
                JOptionPane.showMessageDialog(null, "✅ Cita creada con ID: " + id);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al crear cita: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
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

    public void listarCitasGUI() {
        StringBuilder sb = new StringBuilder("---- Lista de Citas ----\n");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); // saltar encabezado
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                sb.append("ID: ").append(parts[0])
                  .append(", FechaHora: ").append(parts[1])
                  .append(", Motivo: ").append(parts[2])
                  .append(", DoctorID: ").append(parts[3])
                  .append(", PacienteID: ").append(parts[4]).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al listar citas: " + e.getMessage());
        }
    }
}
