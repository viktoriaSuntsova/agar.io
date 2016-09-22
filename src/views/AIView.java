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

/**
 *
 * @author 999
 */
public class AIView extends SpriteView {

    public AIView(Particle _particle, BufferedImage _icon) {
        particle = _particle;
        icon = _icon;
        setSpeed(particle.getAngle());
        setColor(Color.RED);
        setPosition(particle.getPosition());
        group = new SpriteGroup(particle.getName());
        group.add(this);
        particle.addPlayerActionListener(new ParticleObserver());
    }
    
}
