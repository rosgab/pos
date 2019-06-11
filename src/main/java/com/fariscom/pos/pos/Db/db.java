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
public class db {
    private static Connection conection; // conexionLocal
    private String ClassConection = "org.postgresql.Driver";
    private String UserConection = "postgres";
    private String PasswordConection = "juan.vatoms1996";
    private String dbConection = "pos";
    private String portConetion = "5432";
    
    public static Connection getConection()
    {
        if(conection==null)
        {
            conection = Conectar(conection);
        }
        else
        {
            System.out.println("Conexion existente");
        }
        
        return conection;
    }
    
    private db()
    { 
    }
    
    public static Connection Conectar(Connection conection) {
        try {
            //if (conection == null) {
                Class.forName("org.postgresql.Driver");
                
                System.out.println("Se conecto con el servidor de base de datos");
                return conection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pos", "postgres", "juan.vatoms1996");
            //}
            //return conection;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("database.Conectar : error => " + ex.getMessage());
        }
    }
}
