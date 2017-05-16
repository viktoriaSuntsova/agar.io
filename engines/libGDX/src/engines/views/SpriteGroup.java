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
public class SpriteGroup {
    
    private String name = "";
    
    ArrayList<Sprite> sprites = new ArrayList<>();
    
    public SpriteGroup(String _name) {
        name = _name;
    }
    
    public String getName() {
        return name;
    }
    
    public void add(Sprite sprite) {
        sprites.add(sprite);
    }
    
    public void remove(Sprite sprite) {
        sprites.add(sprite);
    }
    
    public void removeInactiveSprites() {
        ArrayList<Sprite> inActiveSprites = new ArrayList<>();
        for(Sprite sprite : sprites) {
            if(!sprite.isActive()) {
                sprite.clearImage();
                inActiveSprites.add(sprite);
            }
        }
    }
    
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
    
    public Sprite getActiveSprite() {
        Sprite _sprite = null;
        for(Sprite other : sprites) {
            if(other.isActive()) {
                _sprite = other;
            }
        }
        return _sprite;
    }
    
    /**
     * Обновляет состояние группы спрайтов
     * @param elapsed прошедшее время в мс
     */
    public void update(long elapsed) {
        sprites.stream().forEach((s) -> {
            s.update(elapsed);
        });
    }
    
    /**
     * Рендерит группу в контексте
     * @param g графический контекст
     */
    public void render(Graphics2D g) {
        sprites.stream().forEach((s) -> {
            s.render(g);
        });
    }
}
