/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import events.ParticleListener;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import models.GameMath;
import models.Particle;

/**
 *
 * @author 999
 */
public class SpriteView extends engines.views.Sprite {
    
    
    protected Particle particle = null;
    
    protected Color color = null;
    
    protected BufferedImage icon = null;
    
    public Graphics2D g2d;
    
    protected String image = "";
    
    /**
     *Отрисовка
     */
    protected void repaint() {
        if(image.isEmpty()) {
            image =  "/img/" + particle.getType() + ".png";
        }
        BufferedImage bi = new BufferedImage(particle.getSize(), particle.getSize(), 
                BufferedImage.TYPE_INT_ARGB);
        if (color != null) {
            // Зарисовать площадь нужным цветом
            g2d = bi.createGraphics();
            g2d.setColor(color);
            g2d.drawOval(0, 0, bi.getWidth(), bi.getHeight());
            BufferedImage avatar = getImage();
            if(avatar != null) {
                g2d.drawImage(avatar, 0, 0, null);
            }
            this.setImage(bi);
        }
    }
    
    public BufferedImage getImage() {
        BufferedImage avatar = null;
        BufferedImage scaled = null;
        String absolutePath = (new File(".")).getAbsolutePath();
        File newImage = new File(absolutePath + image);
        try {
            if(newImage.isFile()) {
                //Взять картинку и задать ей нужный размер
                avatar = ImageIO.read(newImage);
                //Создать  BufferedImage
                scaled = new BufferedImage(particle.getSize(), particle.getSize(), 
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = scaled.createGraphics();
                g.drawImage(avatar, 0, 0, particle.getSize(), particle.getSize(), null);
                g.dispose();
            }
        } catch(IOException ex) {
            System.err.println(ex.getMessage());
        }
        return scaled;
    }
    
    public void clearImage() {
        BufferedImage bi = new BufferedImage(particle.getSize(), particle.getSize(),
                BufferedImage.TYPE_INT_ARGB);
        
        // Зарисовать площадь нужным цветом
        g2d = bi.createGraphics();
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect((int)getX(), (int)getY(), particle.getSize(), particle.getSize());
        
        g2d.clearRect((int)getX(), (int)getY(), particle.getSize(), particle.getSize());
    }
    
    /**
     * Задать скорость
     * @param angle угол
     */
    protected void setSpeed(int angle) {
        setHorizontalSpeed(particle.speed() * Math.cos(GameMath.degreesToRadians(particle.getAngle())));
        setVerticalSpeed(particle.speed() * Math.sin(GameMath.degreesToRadians(particle.getAngle())));
    }
    
    /**
     * Получить частицу
     * @return частица
     */
    public Particle getParticle() {
        return particle;
    }
    
    /**
     * Установить цвет
     * @param color цвет
     */
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    
    /**
     * Установить позицию.
     * @param position позиция
     */
    public void setPosition(Point position) {
        this.setX(position.getX());
        this.setY(position.getY());
    }

    /**
     * Полусить позицию
     * @return позиция
     */
    public Point getPosition() {
        Point position = new Point((int) (getX()),(int) (getY()));
        return position;
    }
    
    protected class ParticleObserver implements ParticleListener{

        @Override
        public void CharacteristicsIsChanged(Particle p) {
            setSpeed( p.getAngle());
        }

        @Override
        public void ParticleIncreased(Particle p) {
            repaint();
        }
    }
    
    /**
     * Обновить игру
     * @param l
     */
    @Override
    public void update(long l) {
        super.update(l);
        particle.setPosition((int)getX(), (int)getY(), getWidth(), getHeight());
    }
    
}
