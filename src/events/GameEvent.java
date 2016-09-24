/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import models.Particle;

/**
 *
 * @author 999
 */
public class GameEvent {
    
    Particle particle = null;
    
    public void setParticle(Particle p) {
        particle = p;
    }
    
    public Particle getParticle() {
        return particle;
    }
}
