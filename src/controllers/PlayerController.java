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
        int radiusParticle = particle.getSize()/2;
        
        double dx = Math.abs(particle.getPosition().getX() - mousePosition.getX()),
                dy = Math.abs(particle.getPosition().getY() - mousePosition.getY());
        
        double r = Math.sqrt( Math.pow(dx, 2) + Math.pow(dy, 2));
        int angle = GameMath.angle(particle.getPosition(), mousePosition);
        particle.setAngle(angle);
        if( r < radiusParticle*0.1 ) {
            particle.setSpeed(0);
        } else if( r > radiusParticle*0.1 && r < radiusParticle ) {
            particle.setSpeed(0.05);
        } else {
            particle.setSpeed(0.1);
        }
        particle.fireCharacteristicsIsChanged();
    }
    
}
