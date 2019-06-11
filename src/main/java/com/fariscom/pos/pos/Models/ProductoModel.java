/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fariscom.pos.pos.Models;

import com.fariscom.pos.pos.Db.db;
import com.fariscom.pos.pos.Entity.Producto;
 import com.fariscom.pos.pos.Service.Entity;
import com.fariscom.pos.pos.Service.JSON;
import com.fariscom.pos.pos.Service.List;
import com.fariscom.pos.pos.Service.crud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Juan Arenas
 */
public class ProductoModel implements crud, JSON,List {
    String Query = "";
    PreparedStatement pst = null;
    JSONObject response =null;
            
            
    @Override
    public JSONObject Save(Entity bean) {
        Producto producto = (Producto) bean;
        response = new JSONObject();

        try {
            if (db.getConection() != null) {
                Query = "insert into productos(codigo,nombre,descripcion,precio,stock) values(?,?,?,?,?)";//call pr_clientes_add (?,?);";
                pst = db.getConection().prepareStatement(Query);

                pst.setString(1, producto.getCodigo());
                pst.setString(2, producto.getNombre());
                pst.setString(3, producto.getDescripcion());
                pst.setFloat(4, producto.getPrecio());
                pst.setInt(5, producto.getStock());
                
                pst.executeUpdate();

                try (ResultSet rs = pst.getResultSet()) {
                    if (rs != null) {
                        while (rs.next()) {
                            producto.setId(rs.getLong("id"));
                            response.put("Estado", true);
                            response.put("Mensaje", "Producto registrado con exito");
                            response.put("Producto", this.Get(bean));
                            return response;
                        }
                    }
                }
                response.put("Mensaje", "imposible registrar el cliente");
            }

        } catch (SQLException ex) {
            response.put("Mensaje", ex.getMessage());
            System.out.println("ProductoModel.Add => Error => " + ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.out.println("ProductoModel.Add => Error => " + ex.getMessage());
            }
        }
        response.put("Estado", false);
        return response;
    }

    @Override
    public JSONObject Get(Entity bean) {
         Producto producto = (Producto) bean;
        Query = "select * from productos where id = ?;";

        try {
            pst = db.getConection().prepareStatement(Query);
            pst.setLong(1, producto.getId());
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    return this.parseJson(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("ProductoModel => Error => " + ex.getMessage());
            return null;
        } finally {
            try { 
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.out.println("ProductoModel => Error => " + ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public JSONObject Update(Entity bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject Delete(Entity bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject parseJson(ResultSet rs) {
        JSONObject job = new JSONObject();
        try {
            job.put("id", rs.getLong("id"));
            job.put("codigo", rs.getString("codigo"));
            job.put("nombre", rs.getString("nombre"));
            job.put("descripcion", rs.getString("descripcion"));
            job.put("precio", rs.getFloat("precio"));
            job.put("stock", rs.getInt("stock"));
             
        } catch (SQLException ex) {
            Logger.getLogger(ClienteModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return job;
    }

    @Override
    public JSONArray ListByActivos(boolean estatus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray ListByCriteria(Entity bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
