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
public interface GeneratedListener extends EventListener {
    
    /** Был создан новый агар
     *
     * @param p сгенерированная частица
     */
    public void generatedAgar(Particle p);
    
    /** Был создан новый бот
     *
     * @param p сгенерированная частица
     */
    public void generatedBot(Particle p);
    
}
