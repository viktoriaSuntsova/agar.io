/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import events.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;
import views.SpriteView;

/**
 *
 * @author 999
 */
public class Particle {

    /**
     * 
     */
    private String name = "";
    
    private String type = "";
    
    /**
     * Цвет объекта
     */
    private Color color = null;
    
    /**
     * Иконка объекта
     */
    private BufferedImage icon = null;
    
    /**
     * Угол перемещения относительно восточного направления
     */
    private int angle = 0;
    
    /**
     * Скорость перемещения объекта
     */
    private double speed = 0;
    
    /**
     * размер объекта
     */
    private int size = 50;
    
    /**
     * Позиция объекта
     */
    private Point position = null;
    
    private Particle collisionParticle = null;
    
    public Particle(int maxWidth, int maxHeight) {
        Random r = new Random();
        position = new Point(r.nextInt(maxWidth), r.nextInt(maxHeight));
    }

    public Point getPosition() {
        return position;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public String getName() {
        return name;
    }
    
    public int getSize() {
        return size;
    }

    
    
    public void setSize(int _size) {
        size = _size;
    }
    
    public void setAngle(int _angle) {
        angle = _angle;
    }

    public int getAngle() {
        return angle;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String str) {
        type = str;
    }
    
    public void setCollision(Particle p) {
        collisionParticle = p;
    }
    
    public Particle getCollision() {
        return collisionParticle;
    }

    public void setPosition(int x, int y, int width, int height) {
        position = new Point( x + width/2, y + height/2 );
    }

    public double speed() {
        return speed;
    }
    
    public void setSpeed(double _speed) {
        speed = _speed;
    }
    
    public void swallow() {
        if( collisionParticle != null ) {
            int otherSize = collisionParticle.getSize();
            double allSquare = 3.14*Math.pow(size, 2) + 3.14*Math.pow(otherSize, 2);
            int newSize = (int)(Math.sqrt(allSquare/3.14)+0.99);
            setSize(newSize);
            fireParticleIsIncrease();
            collisionParticle.fireParticleDied();
        }
    }
    
    private ArrayList particleListenerList = new ArrayList();
    private GameListener gameListener = null;

    public void addPlayerActionListener(ParticleListener p) {
        particleListenerList.add(p);
    }
    
    public void setGameListener( GameListener g ) {
        gameListener = g;
    }
    
    public void fireCharacteristicsIsChanged() {
        ParticleEvent e = new ParticleEvent(this);
        for (Object listener : particleListenerList){
            ((ParticleListener)listener).CharacteristicsIsChanged(e);
        }
    }
    
    public void fireParticleIsIncrease() {
        ParticleEvent e = new ParticleEvent(this);
        for (Object listener : particleListenerList){
            ((ParticleListener)listener).ParticleIncreased(e);
        }
    }
    
    public void fireParticleDied() {
        
        GameEvent e = new GameEvent();
        e.setParticle(this);
        if( gameListener != null )
            gameListener.ParticleDied(e);
            
    }
}
