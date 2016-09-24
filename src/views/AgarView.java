/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.object.SpriteGroup;
import java.awt.Color;
import java.util.Random;
import models.Particle;

/**
 *
 * @author 999
 */
public class AgarView extends SpriteView {
    
    public AgarView(Particle _particle) {
        Random r = new Random();
        particle = _particle;
        particle.setSize(r.nextInt(10) + 10);
        setSpeed(0);
        setColor(Color.BLUE);
        setPosition(particle.getPosition());
        group = new SpriteGroup(particle.getName());
        group.add(this);
        particle.addPlayerActionListener(new SpriteView.ParticleObserver());
    }
    
}
