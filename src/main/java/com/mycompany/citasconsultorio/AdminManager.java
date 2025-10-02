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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AdminManager {
    private final String archivo = "db/admins.csv";
    private List<Admin> admins;

    public AdminManager() {
        admins = new ArrayList<>();
        File file = new File(archivo);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println("id,username,password");
                pw.println("1,admin,admin"); // admin por defecto
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error creando archivo admins.csv: " + e.getMessage());
            }
        }
        cargarAdmins();
    }

    private void cargarAdmins() {
        admins.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line = br.readLine(); // saltar encabezado
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    admins.add(new Admin(parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error cargando administradores: " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        for (Admin a : admins) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private class Admin {
        private String username;
        private String password;

        public Admin(String username, String password) {
            this.username = username;
            this.password = password;
        }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }
}
