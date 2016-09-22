/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.object.*;
import events.ParticleEvent;
import events.ParticleListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import models.GameMath;
import models.Particle;

/**
 *
 * @author 999
 */
public class SpriteView extends Sprite {
    
    
    protected Particle particle = null;
    
    protected Color color = null;
    
    /**
     * Иконка объекта
     */
    protected BufferedImage icon = null;
    
    /**
     * Графика для отрисовки
     */
    private Graphics2D g2d;
    
    /**
     * Группа объектов, участвующих в коллизиях
     */
    protected SpriteGroup group = null;
    
    private void repaint() {
        if (color != null && icon != null) {
            // Зарисовать площадь нужным цветом
            BufferedImage bi = new BufferedImage(icon.getWidth(), icon.getHeight(), BufferedImage.TYPE_INT_ARGB);

            g2d = bi.createGraphics();
            g2d.setColor(color);
            g2d.fillOval(0, 0, bi.getWidth(), bi.getHeight());
            
            // Обозначить периметр
            g2d.setColor(color.darker().darker());
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(0, 0, icon.getWidth(), icon.getHeight());
           
         
            g2d.drawImage(icon, 0, 0, null);
            
            this.setImage(bi);
        }
    }
    
    protected void setSpeed(int angle) {
        System.out.println(particle.getName() + " :" + particle.getPosition());
        setHorizontalSpeed(particle.speed() * Math.cos(GameMath.degreesToRadians(particle.getAngle())));
        setVerticalSpeed(particle.speed() * Math.sin(GameMath.degreesToRadians(particle.getAngle())));
    }
    
    public SpriteGroup getGroup() {
        return group;
    }
    
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    
    public void setPosition(Point position) {
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public Point getPosition() {
        Point position = new Point((int) (getX()),(int) (getY()));
        return position;
    }
    
    protected class ParticleObserver implements ParticleListener{

        @Override
        public void CharacteristicsIsChanged(ParticleEvent p) {
            setSpeed( p.getParticle().getAngle());
        }
    }
    
    @Override
    public void update(long l) {
        super.update(l);
        particle.setPosition((int)getX(), (int)getY(), getWidth(), getHeight());
    }
    
}
