/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.util.EventObject;
import models.Particle;

/**
 *
 * @author 999
 */
public class ParticleEvent extends EventObject {
    //частица
    Particle particle = null;
    
    /**
     * Установить событие
     * @param o - объект 
     */
    public ParticleEvent(Object o) {
        super(o);
        particle = (Particle)o;
    }
    
    /**
     * Получить событие
     * @return частица
     */
    public Particle getParticle() {
        return particle;
    }
    
}
