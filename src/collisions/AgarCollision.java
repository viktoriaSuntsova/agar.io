/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import models.GameMath;
import models.Particle;
import views.SpriteView;

/**
 *
 * @author 999
 */
public class AgarCollision extends BasicCollisionGroup {
    
    @Override
    public void collided(Sprite s1, Sprite s2) {
        Particle p1 = ((SpriteView)s1).getParticle();
        Particle p2 = ((SpriteView)s2).getParticle();
        double distance = GameMath.distance(p1.getPosition(), p2.getPosition());
        if( distance - p1.getSize()/2 < 5 ) {
            p1.swallow(p2);
            s2.setActive(false);
        }
    }
}
