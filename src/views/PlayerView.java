/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.object.SpriteGroup;
import java.awt.Color;
import java.awt.image.BufferedImage;
import models.Particle;

/** Здесь будет
 *
 * @author 999
 */
public class PlayerView extends SpriteView {
    
    /**
     *
     * @param _particle
     */
    public PlayerView(Particle _particle) {
        particle = _particle;
        setSpeed(particle.getAngle());
        setColor(Color.GREEN);
        setPosition(particle.getPosition());
        particle.addPlayerActionListener(new ParticleObserver());
    }
    
}
