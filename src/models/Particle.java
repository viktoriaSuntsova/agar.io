/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.Controller;
import events.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    private int stepsUpdate = 0;
    Controller cont;
    /**
     * Позиция объекта
     */
    private Point position = null;
    
    /**
     * Шаги для задержки update
     * @param _stepsUpdate
     */
    public void stepsUpdate(int _stepsUpdate){
        stepsUpdate =_stepsUpdate;
    }

    /**
     * Получить Шаги для задержки update
     * @return шаги
     */
    public int getStepsUpdate(){
        return stepsUpdate;
    }

    /**
     * Добавить контроллер
     * @param _cont - контроллер
     */
    public void addController(Controller _cont){
        cont = _cont;
    }

    /**
     * Получить контроллер
     * @return контрллер
     */
    public Controller getController(){
        return cont;
    }

    /**
     * Создание частицы
     * @param p - точка
     */
    public Particle(Point p) {
        position = p;
    }
    
    /**
     * Создание частицы
     * @param p - точка
     * @param _type - тип
     * @param _name - имя
     */
    public Particle(Point p, String _type, String _name) {
        type = _type;
        position = p;
        if( _name != null && !_name.isEmpty())
            name = _name;
    }

    /**
     * Полчить позицию на поле
     * @return позиция
     */
    public Point getPosition() {
        return position;
    }
    
    /**
     * Утановить имя
     * @param n - имя
     */
    public void setName(String n) {
        name = n;
    }
    
    /**
     * Получить имя
     * @return имя
     */
    public String getName() {
        return name;
    }
    
    /**
     * Получить размер
     * @return размер
     */
    public int getSize() {
        return size;
    }

    /**
     * Установить размер
     * @param _size - размер
     */
    public void setSize(int _size) {
        size = _size;
    }
    
    /**
     * Установить угол
     * @param _angle угол
     */
    public void setAngle(int _angle) {
        angle = _angle;
    }

    /**
     * Получить угол
     * @return угол
     */
    public int getAngle() {
        return angle;
    }
    
    /**
     * Получить тип
     * @return тип
     */
    public String getType() {
        return type;
    }
    
    /**
     *
     * @param x - координата Х
     * @param y - координата У
     * @param width - ширина
     * @param height -длина
     */
    public void setPosition(int x, int y, int width, int height) {
        position = new Point( x + width/2, y + height/2 );
    }

    /**
     * Полуить скорость
     * @return скорость
     */
    public double speed() {
        return speed;
    }
    
    /**
     * Установить скорость
     * @param _speed скорость
     */
    public void setSpeed(double _speed) {
        speed = _speed;
    }
    
    /**
     * Поглотить другую частицу
     * @param p- другая частица
     */
    public void swallow(Particle p) {
        int otherSize = p.getSize();
        double allSquare = 3.14*Math.pow(size, 2) + 3.14*Math.pow(otherSize, 2);
        int newSize = (int)(Math.sqrt(allSquare/3.14)+0.99);
        setSize(newSize);
        fireParticleIsIncrease();
        if(getType().equals("player"))
            fireAteParticle();
        p.fireParticleDied();
    }
    
    private ArrayList particleListenerList = new ArrayList();
    private GameListener gameListener = null;

    /**
     * Добавить слушателч игрока
     * @param p - слушатель
     */
    public void addPlayerActionListener(ParticleListener p) {
        particleListenerList.add(p);
    }
    
    /**
     * Добавить слушателей игры
     * @param g - слушатель
     */
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
    
    public void fireAteParticle() {
        if( gameListener != null )
            gameListener.AteParticle();
    }
}
