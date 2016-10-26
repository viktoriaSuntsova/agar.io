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
public class PlayerBotCollision extends BasicCollisionGroup {
        
    // Этот вид коллизия для игрока с ботом и для бота с игроком (((
    // первый всегда игрок, второй всегда бот
    @Override
    public void collided(Sprite s1, Sprite s2) {
        boolean firstBigest = ((SpriteView)s1).getParticle().getSize() > ((SpriteView)s2).getParticle().getSize();
        Particle p1 = firstBigest ? ((SpriteView)s1).getParticle() : ((SpriteView)s2).getParticle();
        Particle p2 = firstBigest ? ((SpriteView)s2).getParticle() : ((SpriteView)s1).getParticle();
        int radiusParticle = p1.getSize()/2;
        int dSize = p1.getSize() - p2.getSize();
        double distanceBetweenSprites = GameMath.distance(p1.getPosition(), p2.getPosition());
        // если этот спрайт больше и он достиг центра другой частицы
        if( distanceBetweenSprites - radiusParticle < 5 && dSize > 5 ) {
            p1.swallow(p2);
        }
    }
}
