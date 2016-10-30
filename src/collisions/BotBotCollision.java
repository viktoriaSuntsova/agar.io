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
public class BotBotCollision extends BasicCollisionGroup {

    @Override
    public void collided(Sprite s1, Sprite s2) {
        boolean firstBigest = ((SpriteView)s1).getParticle().getSize() > ((SpriteView)s2).getParticle().getSize();
        Particle p1 = firstBigest ? ((SpriteView)s1).getParticle() : ((SpriteView)s2).getParticle();
        Particle p2 = firstBigest ? ((SpriteView)s2).getParticle() : ((SpriteView)s1).getParticle();
        double size_1 = p1.getSize();
        double size_2 = p2.getSize();
        // если первый бот больше второго больше чем на 10
        // необходимо съесть второго
        if( size_1 - size_2 > 10) {
            double distance = GameMath.distance(p1.getPosition(), p2.getPosition());
            if(size_1/2 - distance < size_1/4) {
               p1.swallow(p2);
               if(firstBigest) s2.setActive(false);
               else s1.setActive(false);
            }
        }
    }
}
