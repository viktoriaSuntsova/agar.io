/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collisions;

import engines.views.Sprite;
import engines.views.BasicCollision;
import models.GameMath;
import models.Particle;
import views.SpriteView;

/**
 * Коллизия между игроком и ботом
 */
public class PlayerBotCollision extends BasicCollision {
        
    // Этот вид коллизия для игрока с ботом и для бота с игроком (((
    // первый всегда игрок, второй всегда бот

    /**
     * Коллизия
     * @param s1 - первый коллизирующий спрайт
     * @param s2 - второй коллизирующиц спрайт
     */
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
            if(distance - 10 < size_1/2) {
               p1.swallow(p2);
               if(firstBigest) s2.setActive(false);
               else s1.setActive(false);
            }
        }
    }
}
