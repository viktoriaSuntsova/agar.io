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
public class AIController extends Controller {
    
    public AIController(GameModel game, Particle particle) {
        super(game, particle);
    }
    
    /**
     * Базовая реализация лишь проверяет, что спрайт не вышел за поля
     * @param mousePosition 
     */
    @Override
    public void update(Point mousePosition) {
        int angle = GameMath.angle(particle.getPosition(), mousePosition);
        particle.setAngle(angle);
        particle.setSpeed(0);
        particle.fireCharacteristicsIsChanged();
    }
    
}
