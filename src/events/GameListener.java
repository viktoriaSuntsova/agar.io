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
public interface GameListener extends EventListener {
    
    public void AteParticle();
    
    public void ParticleDied(Particle p);
    
    public void generatedAgar(Particle p);
    
    public void generatedBot(Particle p);
    
    public void createNewPlayer(String name, String ava);
    
}
