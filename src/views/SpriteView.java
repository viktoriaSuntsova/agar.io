/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.object.*;
import events.ParticleListener;
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
import models.Particle;

/**
 *
 * @author 999
 */
public class SpriteView extends Sprite {
    
    
    protected Particle particle = null;
    
    protected Color color = null;
    
    protected BufferedImage icon = null;
    
    public Graphics2D g2d;
    
    protected String image = "";
    
    /**
     *Отрисовка
     */
    protected void repaint() {
        image = image.isEmpty() ? "img/" + particle.getType() + ".png" : image;
        BufferedImage bi = new BufferedImage(particle.getSize(), particle.getSize(), BufferedImage.TYPE_INT_ARGB);
        if (color != null) {
            // Зарисовать площадь нужным цветом
            g2d = bi.createGraphics();
            g2d.setColor(color);
            g2d.drawOval(0, 0, bi.getWidth(), bi.getHeight());
            if( !particle.getType().isEmpty() ) {
                try {
                    File newImage = new File(image);
                    if(!newImage.isFile()) {
                        System.err.println("The file: " + image + " - not found!");
                        return;
                    }
                    //Взять картинку и задать ей нужный размер
                    Image originalImage = ImageIO.read(newImage);
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
                }
            }
            this.setImage(bi);
        }
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
