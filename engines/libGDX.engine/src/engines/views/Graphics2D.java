/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author homepc
 */
public class Graphics2D {
    
    /**
     * Создает новый контекст с заданным батчем
     * @param batch 
     */
    public Graphics2D(SpriteBatch batch) {
        m_batch = batch;
    }
    
    /**
     * Начинает отрисовку
     */
    public void begin() {
        m_batch.begin();
    }
    
    /**
     * Заканчивает отрисовку
     */
    public void end() {
        m_batch.end();
    }
    
    
    /**
     * Возвращает новый батч кода
     * @return
     */
    public SpriteBatch getBatch() {
        return m_batch;
    }
    
    /**
     * Батч для рисования
     */
    SpriteBatch m_batch;
}
