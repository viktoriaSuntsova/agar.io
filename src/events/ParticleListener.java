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
public interface ParticleListener extends EventListener {
    
    /** Характеристики частицы увеличились
     *
     * @param p текущая частица
     */
    public void CharacteristicsIsChanged(Particle p);
    
    /** Частица увеличилась
     *
     * @param p текущая частица
     */
    public void ParticleIncreased(Particle p);
    
}
