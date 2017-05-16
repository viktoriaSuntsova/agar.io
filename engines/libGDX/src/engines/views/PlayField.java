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
    
    private class CollisionGroups {
        
        public SpriteGroup spriteGroup1 = null;
        
        public SpriteGroup spriteGroup2 = null;
        
        public BasicCollision collision = null;
        
        public CollisionGroups(SpriteGroup g1,
            SpriteGroup g2, BasicCollision coll) {
            spriteGroup1 = g1;
            spriteGroup2 = g2;
            collision = coll;
        }
    }
    
    ArrayList<CollisionGroups> collisions = new ArrayList<>();
    
    public void addGroup(SpriteGroup group) {
        spritegroups.add(group);
    }
    
    public void removeGroup(SpriteGroup group) {
        for(Sprite sprite : group.getSprites()) {
            sprite.clearImage();
        }
        group.sprites.clear();
        collisions.removeIf(gr -> gr.spriteGroup1 == group || gr.spriteGroup2 == group);
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
        collisions.add(new CollisionGroups(sprite1, sprite2, collision));
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
        checkCollisions();
    }
    
    int updateIteration = 0;
    
    public void checkCollisions() {
        if(updateIteration % 1 == 0) {
            for(int col = 0; col < collisions.size(); col++) {
                CollisionGroups collisionGroup = collisions.get(col);
                ArrayList<Sprite> sprites1 = collisionGroup.spriteGroup1.getSprites();
                ArrayList<Sprite> sprites2 = collisionGroup.spriteGroup2.getSprites();
                for(int i = 0; i < sprites1.size(); i++){
                    for(int j = 0; j < sprites2.size(); j++){
                        Sprite sp1 = sprites1.get(i);
                        Sprite sp2 = sprites2.get(j);
                        if(sp1 != null && sp2 != null) {
                            if(checkCollistion((int)sp1.getX(), (int)sp1.getY(), (int)sp1.getWidth(),
                                    (int)sp2.getX(), (int)sp2.getY(), (int)sp2.getWidth())) {
                                collisionGroup.collision.collided(sp1, sp2);
                            }
                        }
                    };
                };
            };
        }
        updateIteration += updateIteration > 1000 ? 1 : (updateIteration + 1);
    }
    
    public boolean checkCollistion(int x1, int y1, int size1, int x2, int y2, int size2) {
        boolean result = false;
        if(x2 > x1 && x2 < x1 + size1) {
            if(y2 > y1 && y2 < y1 + size1)
                result = true;
            if(y1 > y2 && y1 < y2 + size2)
                result = true;
        } else if(x1 > x2 && x1 < x2 + size2) {
            if(y2 > y1 && y2 < y1 + size1)
                result = true;
            if(y1 > y2 && y1 < y2 + size2)
                result = true;
        }
        return result;
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
