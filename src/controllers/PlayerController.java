/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Point;
import models.GameMath;
import models.GameModel;
import models.Particle;
import collisions.*;

/**
 *
 * @author 999
 */
public class PlayerController extends Controller {
    
    /**
     *
     * @param _game
     * @param _particle
     */
    public PlayerController(GameModel _game, Particle _particle) {
        super(_game, _particle);
    }
    
    /**
     * Базовая реализация лишь проверяет, что спрайт не вышел за поля
     * @param mousePosition 
     */
    @Override
    public void update(Point mousePosition) {
        if ( !checkGoOutBorder(mousePosition) ) {
            particle.setSpeed(0);
            particle.fireCharacteristicsIsChanged();
            return;
        }
        //радиус частицы
        int radiusParticle = particle.getSize()/2;
        // растояние между центром частицы и мышкой
        double distance = GameMath.distance(particle.getPosition(), mousePosition);
        // угол относительно горизонта
        int angle = GameMath.angle(particle.getPosition(), mousePosition);
        particle.setAngle(angle);
        setSpeed(distance, radiusParticle);
        particle.fireCharacteristicsIsChanged();
    }
    
    private void setSpeed(double distance, int radiusParticle) {
        if( distance < radiusParticle*0.2 ) {
            particle.setSpeed(0);
        } else if( distance > radiusParticle*0.2 && distance < radiusParticle ) {
            particle.setSpeed(0.05);
        } else {
            particle.setSpeed(0.1);
        }
    }
    
    @Override
    public boolean checkGoOutBorder(Point mousePosition) {
        double x = particle.getPosition().getX(),
                y = particle.getPosition().getY();
        if(x > game.getSize().getWidth() && mousePosition.getX() > x 
                || y > game.getSize().getHeight() && mousePosition.getY() > y) {
            particle.setSpeed(0);
            return false;
        }
        return true;
    }
}
