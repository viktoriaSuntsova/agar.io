/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.object.SpriteGroup;
import java.awt.Color;
import models.Particle;

/**
 *
 * @author 999
 */
public class ObstacleView extends SpriteView {
    
    public ObstacleView(Particle _particle) {
        particle = _particle;
        particle.setSize(100);
        setColor(Color.GRAY);
        setSpeed(0);
        setPosition(particle.getPosition());
    }
}
