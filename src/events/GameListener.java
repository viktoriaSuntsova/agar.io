/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.util.EventListener;
import models.Particle;

/**
 *
 * @author Ivan
 */
public interface GameListener extends EventListener {
    
    /** Съел частицу
     *
     */
    public void AteParticle();
    
    /** Частица была съедена
     *
     * @param p съеденная частица
     */
    public void ParticleDied(Particle p);
    
}
