/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fariscom.pos.pos.Entity;

import javax.ejb.Stateless;
import com.fariscom.pos.pos.Service.Entity;
/**
 *
 * @author Juan Arenas
 */
@Stateless
public class Cliente implements Entity {
    private int Id;
    private String razonSocial;
    private String Rfc;

    public Cliente()
    {
    }
    
    public Cliente(String Nombre,String Rfc)
    {
        this.razonSocial = Nombre;
        this.Rfc = Rfc;
    }
    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the Nombres
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the Nombres to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the Rfc
     */
    public String getRfc() {
        return Rfc;
    }

    /**
     * @param Rfc the Rfc to set
     */
    public void setRfc(String Rfc) {
        this.Rfc = Rfc;
    }
    
    
}
