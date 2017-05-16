/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author homepc
 */
public class Background {
    
    /**
     * Создает новый фон с заданным изображением
     * @param bi  изображение
     */
    public Background(BufferedImage bi) {
        m_texture  = TextureManager.getTexture(bi);
        m_texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }
    
    /**
     * Создает новый фон с заданным изображением
     * @param bi  изображение
     */
    public Background(BufferedImage bi, int[][] tile) {
        m_texture  = TextureManager.getTexture(bi);
        m_texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }
    
    /**
     * Устанавливает обрезку фона - здесь не делает ничего, только устанавливает
     * внутренние параметры
     * @param x координата X начала
     * @param y координата Y начала
     * @param width ширина порта
     * @param height высорта порта
     */
    public  void setClip(int x, int y, int width, int height) {
        viewportWidth = width;
        viewportHeight = height;
    }
    
    /**
     * Устанавливает глобальные пределы смещения камеры
     * @param totalWidth ширина поля
     * @param totalHeight высота поля
     */
    public void setTotalClip(int totalWidth, int totalHeight) {
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
        m_region = new TextureRegion(m_texture);
    }
    
    /**
     * Обновление состояния фона
     * @param elapsed прошедшее время в мс
     */
    public void update(long elapsed) {

    }
    
    /**
     * Отрисовка фона
     * @param g графический контекст
     */
    public void render(Graphics2D g) {
       if (m_region != null) {
           g.getBatch().draw(m_region, 0, 0, this.totalWidth, this.totalHeight);
       }
    }
    
    /**
     * Устанавливает центра фона в определенную позицию
     * @param s спрайт, в центр которого устанавливается позиция камеры
     */
    public void setToCenter(Sprite s) {
        double x = s.getX();
        double y = s.getY();
        GameView.m_current_camera.position.set((float)x, (float)y, 0);
    }
    
    /**
     * Возвращает позицию X смещения фона
     * @return X-координата
     */
    public double getX() {
        return GameView.m_current_camera.position.x;
    }

    /**
     * Возвращает позицию Y смещения фона
     * @return Y-координата
     */    
    public double getY() {
        return GameView.m_current_camera.position.y;
    }    
    
    /**
     * Ширина всего поля игры
     */
    int totalWidth = 0;
    /**
     * Высота всего поля игры
     */
    int totalHeight = 0;
    
    /**
     * Ширина вьюпорта
     */
    int viewportWidth = 0;
    /**
     * Высота вьюпорта
     */
    int viewportHeight = 0;
    
    /**
     * Регион текстуры для отрисовки
     */
    TextureRegion m_region;    
    /**
     * Текстура для отрисовки
     */
    Texture m_texture;
}
