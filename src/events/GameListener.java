/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.util.EventListener;

/**
 *
 * @author 999
 */
public interface GameListener extends EventListener {
    
    public void AteParticle();
    
    public void ParticleDied(GameEvent e);
    
    public void generatedAgar(GameEvent e);
    
    public void generatedBot(GameEvent e);
    
    public void generatedPlayer(GameEvent e);
    
    public void createNewPlayer(String name, String ava);
    
}
