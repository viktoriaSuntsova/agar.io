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
import views.Collision;

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
        //радиус частицы
        int radiusParticle = particle.getSize()/2;
        // растояние между центром частицы и мышкой
        double distance = GameMath.distance(particle.getPosition(), mousePosition);
        // угол относительно горизонта
        int angle = GameMath.angle(particle.getPosition(), mousePosition);
        particle.setAngle(angle);
        setSpeed(distance, radiusParticle);
        if( particle.getCollision() != null ) {
            int dSize = particle.getSize() - particle.getCollision().getSize();
            double distanceBetweenSprites = GameMath.distance(particle.getPosition(), particle.getCollision().getPosition());
            // если этот спрайт больше и он достиг центра другой частицы
            if( dSize > 10 ) {
                if( distanceBetweenSprites - radiusParticle < 5 )
                    particle.swallow();
            } else if( dSize < 10 || dSize > -10 ) {
                setCollision(angle, mousePosition);
            }
            particle.setCollision(null);
        }
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
    
    public void setCollision(int angle, Point mousePosition) {
        double angleCollision = GameMath.radiansToDegrees( GameMath.getAngleAtThreePoints(
                    particle.getPosition(), 
                    particle.getCollision().getPosition(), 
                    mousePosition.getLocation()
            ) );
        if( angleCollision >= 0 && angleCollision < 90 ) {
            int diffAngle = (int) (90 - angleCollision);
            int pointToLine = GameMath.pointToLine(particle.getPosition(), particle.getCollision().getPosition(), mousePosition);
            particle.setAngle(pointToLine > 0 ? angle - diffAngle : angle + diffAngle);
            particle.setSpeed(particle.speed() * (angleCollision / 90 ));
        }
    }
    
}
