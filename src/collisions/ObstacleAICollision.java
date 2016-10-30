/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import controllers.AIController;
import models.Particle;
import views.SpriteView;

/**
 *
 * @author Viktoria
 */
public class ObstacleAICollision extends BasicCollisionGroup {
    
    
    @Override
    public void collided(Sprite s1, Sprite s2) {
        Particle p = ((SpriteView)s1).getParticle();
        AIController ai = (AIController)p.getController();
        int angle = p.getAngle() - 180; 
        ai.setAngleForStep(angle);
        ai.setSteps(500);
        p.fireCharacteristicsIsChanged();
    }
}
