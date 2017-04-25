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
    
    
    ArrayList<SpriteGroup> spritegroups = new ArrayList<>();
    
    ArrayList<BasicCollision> collisions = new ArrayList<>();
    
    public void addGroup(SpriteGroup group) {
        spritegroups.add(group);
    }
    
    public void removeGroup(SpriteGroup group) {
        spritegroups.remove(group);
    }
    
    public SpriteGroup getGroup(String name) {
        SpriteGroup group = null;
        for(SpriteGroup item: spritegroups) {
            if(name.equals(item.getName())) {
                group = item;
            }
        }
        return group;
    }
    
    public void addCollisionGroup(SpriteGroup sprite1,
            SpriteGroup sprite2, BasicCollision collision) {
    }
    
    public void setBackground(Background bg) {
        
    }
    
    /**
     * Обновляет состояние группы спрайтов
     * @param elapsed прошедшее время в мс
     */
    public void update(long elapsed) {
        spritegroups.stream().forEach((s) -> {
            s.update(elapsed);
        });
    }
    
    /**
     * Рендерит группу в контексте
     * @param g графический контекст
     */
    public void render(Graphics2D g) {
        spritegroups.stream().forEach((s) -> {
            s.render(g);
        });
    }
}
