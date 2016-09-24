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
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import models.GameMath;
import models.ImageWork;
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
        BufferedImage bi = new BufferedImage(particle.getSize(), particle.getSize(), BufferedImage.TYPE_INT_ARGB);
        if (color != null) {
            // Зарисовать площадь нужным цветом
            g2d = bi.createGraphics();
            g2d.setColor(color);
            g2d.fillOval(0, 0, bi.getWidth(), bi.getHeight());
            
            // Обозначить периметр
            g2d.setColor(color.darker().darker());
            g2d.setStroke(new BasicStroke((float) (particle.getSize()*0.03)));
            g2d.drawOval(0, 0, particle.getSize(), particle.getSize());
            g2d.drawImage(null,0,0, null);
            if( !particle.getType().isEmpty() ) {
                /*try {
                    //Взять картинку и задать ей нужный размер
                    Image originalImage = ImageIO.read(new File("img/" + particle.getType() + ".png"));
                    Image scaled = originalImage.getScaledInstance(particle.getSize(), particle.getSize(), Image.SCALE_SMOOTH);
                    //Создать  BufferedImage
                    BufferedImage avatar = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    //Нарисовать BufferedImage на кружочке
                    Graphics2D bGr = avatar.createGraphics();
                    bGr.drawImage(scaled, 0, 0, null);
                    bGr.dispose();
                    g2d.drawImage(avatar, (particle.getSize() - avatar.getWidth()) / 2,
                         (particle.getSize() - avatar.getHeight()) / 2, null);
                } catch (IOException ex) {
                    Logger.getLogger("No such file in the directory");
                }*/
            }
            
            this.setImage(bi);
        }
    }
    
    protected void setSpeed(int angle) {
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
