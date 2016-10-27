/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collisions;

import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 *
 * @author 999
 */
public class CollisionFactory {
    
    /**
     */
    public static BasicCollisionGroup createCollision (String first, String second) {
        BasicCollisionGroup collision = null;
        switch(second) {
            case "bot":
                collision = first.equals("bot") ? new BotBotCollision(): new PlayerBotCollision();
            break;
            case "agar":
                collision = new AgarCollision();
            break;
            case "obstacle":
                collision = first.equals("bot") ? new ObstacleAICollision(): new ObstaclePlayerCollision();
            break;
        }
        return collision;
    }
}
