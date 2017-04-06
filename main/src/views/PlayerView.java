/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import models.Particle;

/** Здесь будет
 *
 * @author 999
 */
public class PlayerView extends SpriteView {
    
    /**
     * Создать отображение для игрока
     * @param _particle- чатсица
     */
    public PlayerView(Particle _particle) {
        particle = _particle;
        setSpeed(particle.getAngle());
        setColor(Color.GREEN);
        setPosition(particle.getPosition());
        particle.addPlayerActionListener(new ParticleObserver());
    }
    
    public void setPicture(String picture) {
        image = picture;
        repaint();
    }
}
