/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fariscom.pos.pos.Models;

import com.fariscom.pos.pos.Db.db;
import com.fariscom.pos.pos.Entity.Cliente;
import com.fariscom.pos.pos.Service.Entity;
import com.fariscom.pos.pos.Service.List;
import com.fariscom.pos.pos.Service.crud;
import com.fariscom.pos.pos.Service.JSON;
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
public class ClienteModel implements crud, JSON,List {

    String Query = "";
    PreparedStatement pst = null;
    JSONObject response = null;

    @Override
    public JSONObject Save(Entity bean) {
        Cliente cliente = (Cliente) bean;
        response = new JSONObject();

        try {
            if (db.getConection() != null) {
                Query = "insert into clientes(razon_social,rfc) values(?,?)";//call pr_clientes_add (?,?);";
                pst = db.getConection().prepareStatement(Query);

                pst.setString(1, cliente.getRazonSocial());
                pst.setString(2, cliente.getRfc());
                pst.executeUpdate();

                try (ResultSet rs = pst.getResultSet()) {
                    if (rs != null) {
                        while (rs.next()) {
                            cliente.setId(rs.getInt("id"));
                            response.put("Estado", true);
                            response.put("Mensaje", "Cliente registrado con exito");
                            response.put("Cliente", this.Get(bean));
                            return response;
                        }
                    }
                }
                response.put("Mensaje", "imposible registrar el cliente");
            }

        } catch (SQLException ex) {
            response.put("Mensaje", ex.getMessage());
            System.out.println("ClienteModel.Add => Error => " + ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.out.println("ClienteModel.Add => Error => " + ex.getMessage());
            }
        }
        response.put("Estado", false);
        return response;
    }

    @Override
    public JSONObject Get(Entity bean) {
        Cliente cliente = (Cliente) bean;
        Query = "select id,nombre,rfc from clientes where id = ?;";

        try {
            pst = db.getConection().prepareStatement(Query);
            pst.setLong(1, cliente.getId());
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    return this.parseJson(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("ClienteModel => Error => " + ex.getMessage());
            return null;
        } finally {
            try { 
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.out.println("ClienteModel => Error => " + ex.getMessage());
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
            job.put("id", rs.getInt("id"));
            job.put("razon_social", rs.getString("razon_social"));
            job.put("rfc", rs.getString("rfc"));
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
         
        Query = "select id,razon_social,rfc from clientes;";
        JSONArray lista = new JSONArray();
        try {
            pst = db.getConection().prepareStatement(Query);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    lista.add(this.parseJson(rs));
                 }
            }
        } catch (SQLException ex) {
            System.out.println("ClienteModel => Error => " + ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.out.println("ClienteModel => Error => " + ex.getMessage());
            }
        }
        return lista;
    }

}
