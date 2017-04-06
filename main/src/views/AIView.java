/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import models.Particle;

/**
 *
 * @author 999
 */
public class AIView extends SpriteView {

    /**
     * Создание отображени для ИИ
     * @param _particle -  частица
     */
    public AIView(Particle _particle) {
        particle = _particle;
        setSpeed(0);
        setColor(Color.RED);
        setPosition(particle.getPosition());
        particle.addPlayerActionListener(new ParticleObserver());
    }
    
}
