/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fariscom.pos.pos.Db;

import java.sql.Connection;
import java.sql.DriverManager;
 import java.sql.SQLException;
 
/**
 *
 * @author Juan Arenas
 */
public class DbConection {
    public Connection conection; // conexionLocal
 
    public Connection Conectar() {
        try {
            if (this.conection == null) {
                Class.forName("org.mariadb.jdbc.Driver");
                return conection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/db_pos", "root", "juan.vatoms1996");
            }
            return conection;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("database.Conectar : error => " + ex.getMessage());
        }
    }

    public boolean Desconectar() {
        try {
            if (this.conection != null) {
                this.conection.close();
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("database.Desconectar : error => " + ex.getMessage());
        }
        return false;
    }
}
