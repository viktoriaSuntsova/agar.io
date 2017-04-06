/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collisions;

import engines.views.Sprite;
import engines.views.BasicCollision;
import models.Particle;
import models.GameMath;
import views.SpriteView;

/**
 * Коллизия между препятствием и игроком
 */
public class ObstaclePlayerCollision extends BasicCollision {
        
    /**
     * Коллизия
     * @param s1 - первый коллизирующий спрайт
     * @param s2 - второй коллизирующиц спрайт
     */
    @Override
    public void collided(Sprite s1, Sprite s2) {
        // получаем бота или игрока
        Particle p1 = ((SpriteView)s1).getParticle();//игрок
        Particle p2 = ((SpriteView)s2).getParticle();//прептствие

        //Отодвигаем игрока от препятсвия чтобы он не проходил через него
        s1.setX(s1.getOldX());
        s1.setY(s1.getOldY());
        
        //вычисляем куда подвинуть частицу в случае проникновения в препятствие
        double O0_O2 = (double)(p1.getSize()/2+p2.getSize()/2);
        double O0_O1 = (double)(GameMath.distance((int)(s1.getX()),(int)(s1.getY()),(int)(s2.getX()),(int)(s2.getY())));
        if(O0_O1<=O0_O2){
            int newX, X;
            int newY, Y;
            X = (int) (O0_O2*(Math.abs(s2.getX()-s1.getX()))/O0_O1);
            newX = (int) (X - Math.abs((s2.getX()-s1.getX())));
            
            Y = (int) (O0_O2*(Math.abs(s2.getY()-s1.getY()))/O0_O1);
            newY = (int) (Y - Math.abs((s2.getY()-s1.getY())));
            
            if(s2.getX()>s1.getX()){
                s1.setX(s1.getX()-newX);
            }
            else if(s2.getX()<s1.getX()){
                s1.setX(s1.getX()+newX);
            }
            
            
            if(s2.getY()>s1.getY()){
                s1.setY(s1.getY()-newY);
            }
            else if(s2.getX()<s1.getX()){
                s1.setY(s1.getY()+newY);
            }
            
            
            //p1.setSpeed(4);
        } 
        p1.fireCharacteristicsIsChanged();
    }
}
