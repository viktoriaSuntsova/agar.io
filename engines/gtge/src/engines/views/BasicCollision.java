/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.Sprite;

/**
 *
 * @author homepc
 */
public class BasicCollision extends BasicCollisionGroup {

    @Override
    public void checkCollision() {
        super.checkCollision();
    }
    
    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setCollisionGroup(SpriteGroup s1, SpriteGroup s2) {
        super.setCollisionGroup(s1, s2);
    }
    
    /**
     * Обрабатывает коллизии.
     * @param first
     * @param second 
     */
    public void collided(engines.views.Sprite first, engines.views.Sprite second) {
        
    }
}