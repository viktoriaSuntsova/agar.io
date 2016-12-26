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
 * @author 999
 */
public interface ParticleListener extends EventListener {
    
    public void CharacteristicsIsChanged(Particle p);
    
    public void ParticleIncreased(Particle p);
    
}
