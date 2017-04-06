package engines.views;

import com.golden.gamedev.Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author homepc
 */
public class GameView extends Game {

    @Override
    public void initResources() {

    }

    @Override
    public void update(long l) {

    }

    @Override
    public void render(java.awt.Graphics2D g) {
        Graphics2D ctx = new Graphics2D(g);
        renderInContext(ctx);
    }
    
    /**
     * Отрисовывет состояние игры
     * @param g 
     */    
    public void renderInContext(Graphics2D g) {
        
    }
    
    /**
     * Получение координаты X курсора мыши в окне
     * @return 
     */
    @Override
    public int getMouseX() {
        return super.getMouseX();
    }

    /**
     * Получение координаты Y курсора мыши в окне
     * @return 
     */    
    @Override
    public int getMouseY() {
        return super.getMouseY();
    }
    
}
