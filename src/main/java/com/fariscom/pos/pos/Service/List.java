/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fariscom.pos.pos.Service;

import org.json.simple.JSONArray;

/**
 *
 * @author Juan Arenas
 */
public interface List {
    JSONArray ListByActivos(boolean estatus);
    JSONArray ListByCriteria(Entity bean);
}
