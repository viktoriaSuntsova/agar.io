/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author homepc
 */
public class Sprite {
    
    /**
     * Создает спрайт по умолчанию
     */
    public Sprite() {
        
    }
    
    /**
     * Создает спрайт с изображением x и y
     * @param bi изображение
     * @param x X-координата спрайта
     * @param y Y-координата спрайта
     */
    public Sprite(BufferedImage bi, int x, int y) {        
        m_x = x;
        m_old_x = x;
        m_y = y;
        m_old_y = y;
        setImage(bi);
    }
    
    /**
     * Обновление состояния спрайта
     * @param elapsed прошедшее время
     */
    public void update(long elapsed) {
        double nx = m_x + m_horizontal_speed * elapsed;
        double ny = m_y + m_vertical_speed * elapsed;
        
        m_old_x = m_x;
        m_old_y = m_y;
        
        m_x = nx;
        m_y = ny;
        
        m_shape.x0 = m_x +  m_texture.getWidth() / 2;
        m_shape.y0 = m_y -  m_texture.getHeight() / 2;
    }
    
    /**
     * Рисует спрайт 
     * @param g контекст
     */
    public void render(Graphics2D g) {
        if (m_texture != null)  {
            g.getBatch().draw(
                m_texture, 
                (float)m_x, 
                (float)(m_y - m_texture.getHeight())
            );
 
        }
    }
    
    /**
     * Устанавливает изображение для спрайта
     * @param bi изображение
     */
    public final void setImage(BufferedImage bi) {
        m_texture = TextureManager.getTexture(bi);
        m_shape = new engines.views.Cicle(
                m_x +  m_texture.getWidth() / 2, 
                m_y -  m_texture.getHeight() / 2, 
                m_texture.getWidth() / 2
        );
    }
    
    /**
     * Вызывается в наследуемых классах
     */
    public void clearImage() {
        
    }
    
    /**
     * Устанавливает X-координату спрайта
     * @param x координата спрайта
     */
    public void setX(double x) {
        m_x = x;
        m_shape.x0 = x + m_texture.getWidth() / 2;
    }

    /**
     * Устанавливает Y-координату спрайта
     * @param y координата спрайта
     */
    public void setY(double y) {
        m_y = y;
        //m_shape.y0 = y - m_texture.getHeight() / 2;
    }


    /**
     * Возвращает X-координату спрайта
     * @return X-координата спрайта
     */    
    public double getX() {
        return m_x;
    }

    /**
     * Возвращает Y-координату спрайта
     * @return Y-координата спрайта
     */
    public double getY() {
        return m_y;
    }

    /**
     * Устанавливает горизонтальную скорость спрайта
     * @param v скорость
     */    
    public void setHorizontalSpeed(double v) {
        m_horizontal_speed = v;
    }

    /**
     * Устанавливает вертикальную скорость спрайта
     * @param v скорость
     */
    public void setVerticalSpeed(double v) {
        m_vertical_speed = v;
    }

    /**
     * Возвращает старую X-координату
     * @return 
     */    
    public double getOldX() {
        return m_old_x;
    }
    
    /**
     * Возвращает старую Y-координату
     * @return 
     */
    public double getOldY() {
       return m_old_y;
    }  
    
    /**
     * Возвращает центр спрайта
     * @return центр спрайта
     */
    public Point getCenter() {
        return new Point((int)getX(),(int) getY());
    }
    
    public void setActive(boolean isActive) {
        this._isActive = isActive;
    }
    
    public boolean isActive() {
        return _isActive;
    }
    
    public int getWidth() {
        return m_texture.getWidth();
    }
    
    public int getHeight() {
        return m_texture.getHeight();
    }
    
    private boolean _isActive = true;
    
    /**
     * Текстура
     */
    Texture m_texture;    
    
    /**
     * X-координата центра спрайта
     */
    double m_x = 0;
    
    /**
     * Y-координата центра спрайта
     */
    double m_y = 0;
    
    /**
     * "Старая" X-координата спрайта
     */
    double m_old_x = 0;
    /**
     * "Старая" Y-координата спрайта
     */
    double m_old_y = 0;
    
    /**
     * Горизональная скорость спрайта
     */
    double m_horizontal_speed = 0;
    
    /**
     * Вертикальная скорость спрайта
     */
    double m_vertical_speed = 0;
    
    /**
     * Форма для отображения спрайта
     */
    engines.views.Cicle m_shape;
}
