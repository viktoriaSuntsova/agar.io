/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

import java.util.ArrayList;

/**
 *
 * @author homepc
 */
public class PlayField {
    
    
    ArrayList<SpriteGroup> sprites = new ArrayList<>();
    
    ArrayList<BasicCollision> collisions = new ArrayList<>();
    
    public void addGroup(SpriteGroup group) {
        sprites.add(group);
    }
    
    public void addCollisionGroup(Sprite sprite1, 
            Sprite sprite2, BasicCollision collision) {
        
    }
}
