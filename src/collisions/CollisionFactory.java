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
    
    public static BasicCollisionGroup createCollision (String type) {
        BasicCollisionGroup collision = null;
        switch(type) {
            case "bot":
                collision = new PlayerBotCollision();
            break;
            case "agar":
                collision = new AgarCollision();
            break;
            case "obctacle":
                collision = new ObstacleCollision();
            break;
        }
        return collision;
    }
}
