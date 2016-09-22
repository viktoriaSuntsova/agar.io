/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 *
 * @author 999
 */
public class Collision extends BasicCollisionGroup  {

    @Override
    public void collided(Sprite s1, Sprite s2) {
        ((SpriteView)s1).particle.setSpeed(0);
        ((SpriteView)s1).particle.fireCharacteristicsIsChanged();
        ((SpriteView)s2).particle.setSpeed(0);
        ((SpriteView)s1).particle.fireCharacteristicsIsChanged();
    }
    
}
