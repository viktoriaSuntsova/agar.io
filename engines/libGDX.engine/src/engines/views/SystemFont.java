/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;

/**
 *
 * @author homepc
 */
public class SystemFont {
    
    /**
     * Создает новый системный шрифт
     * @param fontName имя
     * @param attrs атрибуты
     * @param size размер шрифта
     * @param clr  цвет
     */
    public SystemFont(String fontName, int attrs, int size, java.awt.Color clr) {
        m_font = new BitmapFont();
        m_font.setColor(new Color(
                clr.getRed() / 255.0f, 
                clr.getGreen() / 255.0f,
                clr.getBlue() / 255.0f,
                clr.getAlpha() / 255.0f
        ));
    }
    
    /**
     * Рисует строку с заданным значением строки
     * @param g контекст
     * @param data данные
     * @param x координата X
     * @param y координата Y
     */
    public void drawString(Graphics2D g, String data, int x, int y) {
        float px = x + (GameView.m_current_camera.position.x - Gdx.graphics.getWidth() / 2);
        float py =  (Gdx.graphics.getHeight() - y) 
                 + (GameView.m_current_camera.position.y - Gdx.graphics.getHeight() / 2);
        m_font.draw(
            g.getBatch(), 
            data, 
            px, 
            py
        );
    }
    
    /**
     * Шрифт по умолчанию из GDX
     */
    BitmapFont m_font;
}
