/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fariscom.pos.pos.Service;

import org.json.simple.JSONObject;

/**
 *
 * @author Juan Arenas
 */
public interface crud {
    JSONObject Save(Entity bean);
    JSONObject Get(Entity bean);
    JSONObject Update(Entity bean);
    JSONObject Delete(Entity bean);
}
