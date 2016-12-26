/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Point;
import models.*;

/**
 *
 * @author 999
 */
public class Controller {
    
    protected GameModel game = null;
    
    protected Particle particle = null;
    
    protected final int MIN_DITANCE = 500;
    
    /**
     * Создание контроллера
     * @param _game
     * @param _particle
     */
    public Controller(GameModel _game, Particle _particle) {
        game = _game;
        particle = _particle;
    }
    
    /**
     * 
     * @param mousePosition - позиция мыши
     */
    public void update(Point mousePosition) {
        
    }
    
    /**
     * Получить частицу
     * @return частица этого контрллера
     */
    public Particle getParticle() {
        return particle;
    }
    
    /**
     * Установить коллизию
     * @param angle угол
     * @param mousePosition позиция мыши
     */
    public void setCollision(int angle, Point mousePosition) {
        /*double angleCollision = GameMath.radiansToDegrees( GameMath.getAngleAtThreePoints(
                    particle.getPosition(), 
                    particle.getCollision().getPosition(), 
                    mousePosition.getLocation()
            ) );
        if( angleCollision >= 0 && angleCollision < 90 ) {
            int diffAngle = (int) (90 - angleCollision);
            int pointToLine = GameMath.pointToLine(particle.getPosition(), particle.getCollision().getPosition(), mousePosition);
            particle.setAngle(pointToLine > 0 ? angle - diffAngle : angle + diffAngle);
            particle.setSpeed(particle.speed() * (angleCollision / 90 ));
        }*/
    }
    
    /**
     *
     * @param mousePosition позиция мыши
     * @return признак мы у границы
     */
    public boolean checkGoOutBorder(Point mousePosition) {
        double x = particle.getPosition().getX(),
                y = particle.getPosition().getY();
        if(x <= 0 || x > game.getSize().getWidth() ||
                y <= 0 || y > game.getSize().getHeight()) {
            particle.setSpeed(0);
            return false;
        }
        return true;
    }
}
