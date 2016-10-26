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
        Particle p1 = ((SpriteView)s1).getParticle();
        Particle p2 = ((SpriteView)s2).getParticle();
        // будем ссылаться в p1 что он всегда больше
        Particle tmp = p1.getSize() > p1.getSize() ? p1 : p2;
        p1 = p1 == tmp ? p1 : p2;
        p2 = p1 == tmp ? p2 : p1;
        double size_1 = p1.getSize();
        double size_2 = p2.getSize();
        // если первый бот больше второго больше чем на 10
        // необходимо съесть второго
        if( size_1 - size_2 > 10) {
            double distance = GameMath.distance(p1.getPosition(), p2.getPosition());
            if(size_1/2 - distance < size_1/4) {
               p1.swallow(p2);
            }
        }
    }
}
