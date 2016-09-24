/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import models.Particle;

/**
 *
 * @author 999
 */
public class Collision extends BasicCollisionGroup  {

    @Override
    public void collided(Sprite s1, Sprite s2) {
        Particle p1 = ((SpriteView)s1).particle;
        Particle p2 = ((SpriteView)s2).particle;
        double d = Math.sqrt( 
                Math.pow(p1.getPosition().getX() - p2.getPosition().getX(), 2) +
                        Math.pow(p1.getPosition().getY() - p2.getPosition().getY(), 2) 
        );
        if( d < (p1.getSize()/2 + p2.getSize()/2) ) {
            ((SpriteView)s1).particle.setCollision(((SpriteView)s2).particle);
            ((SpriteView)s2).particle.setCollision(((SpriteView)s1).particle);
        }
    }
    
}
