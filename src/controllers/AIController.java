/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Point;
import java.util.ArrayList;
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
        //найдем самую близкую к нам бактерию больше нас
        Particle bigOne = findNearestBiggerParticle();
        //найдем самую близкую к нам бактерию меньше нас
        Particle smallOne = findNearestSmallerParticle();
        //найдем самую близкую к нам агарину
        Particle agar = findNearestAgar();
        int angle = 0;
        //выберем что приоритетнее в данной ситуации
        String particleStr = chooseParticle(smallOne,bigOne,agar);
        System.out.print(particleStr+"\n");
        //если оказалось приоритетнее убежать от большой бактерии
        if("bigOne".equals(particleStr)){
           //Расчитываем угол до нее
           angle = GameMath.angle(particle.getPosition(), bigOne.getPosition());
           //Разворачиваемся
           angle = 360 - angle;
        }
        //если оказалось приоритетнее охотится за маленькой бактерией
        if("smallOne".equals(particleStr)){
            //Расчитываем угол до маленькой бактерии
            angle = GameMath.angle(particle.getPosition(), smallOne.getPosition());
        }
        //Если оказалось приоритетнее есть агар
        if("agar".equals(particleStr)){
            //Расчитываем угол до агарины
             angle = GameMath.angle(particle.getPosition(), agar.getPosition());
        }
        //Если все равно куда двигаться 
        if("random".equals(particleStr)){
            //Двигаемся рандомно TODO
             angle = 60;
        }
        //Сообщаем частице выбранный угол
        particle.setAngle(angle);
        //Сообщаем частице выбранную скорость
        particle.setSpeed(0.01);
        
        //А вот тут путаемся и плачем
        if( particle.getCollision() != null ) {
            if( particle.getSize() > particle.getCollision().getSize()) {
                particle.swallow();
                game.removeParticle(particle.getCollision());
                particle.getCollision().fireParticleDied();
            } 
            else{
                setCollision(angle);
                System.out.print("I am dead\n");
                particle.fireParticleDied();
            }
            particle.setCollision(null);
        }
        particle.fireCharacteristicsIsChanged();
    }
    
    public String chooseParticle(Particle smallOne, Particle bigOne, Particle agar){
        if(bigOne == null){ //Если на поле вообще нет больше меня
            if(smallOne == null){ //Если на поле нет больше меня и меньше меня тоже нет
                if(agar == null){ // Если на поле только я
                    //Будем двигаться рандомно
                    return "random";
                }
                else{ //Если на поле кроме меня только агар
                    System.out.print("I have only agar\n");
                    System.out.print("\n");
                    //Едим агар
                    return "agar";
                }
            }
            else{//Если на поле только агар и меньше меня
                System.out.print("I have small and agar, I choose small\n");
                System.out.print("\n");
                //Гонимся за маленькой бактерией
                return "smallOne";
            }
        }
        else{//Если на поле есть бактерии больше меня
            if(smallOne == null){//Если на поле есть бактерии больше меня, а меньше меня нет
                if(agar == null){//Если на поле только большая бактерия и я
                    //Будем убегать от большой бактерии
                    return "bigOne";
                }
                else{//Если на поле есть бактерия больше меня и агар
                    //Если до агара ближе чем до большой бактерии
                    if(Math.abs(GameMath.distance(particle.getPosition(),bigOne.getPosition())-bigOne.getSize()/2)>GameMath.distance(particle.getPosition(),agar.getPosition())){
                        
                        System.out.print("I have big and agar, I choose agar\n");
                        System.out.print(Math.abs(GameMath.distance(particle.getPosition(),bigOne.getPosition())-bigOne.getSize()/2));
                        System.out.print("\n");
                        /*System.out.print(GameMath.distance(particle.getPosition(),agar.getPosition()));
                        System.out.print("\n");*/
                        System.out.print(bigOne.getPosition());
                        System.out.print("\n");
                        System.out.print(particle.getPosition());
                        System.out.print("\n");
                        System.out.print(agar.getPosition());
                        System.out.print("\n");
                        //Будем есть агар
                        return "agar";
                    }
                    else{ //Если до большой бактерии ближе чем до агара
                        
                        System.out.print("I have big and agar, I choose big\n");
                        System.out.print(Math.abs(GameMath.distance(particle.getPosition(),bigOne.getPosition())-bigOne.getSize()/2));
                        System.out.print("\n");
                        System.out.print(GameMath.distance(particle.getPosition(),agar.getPosition()));
                        System.out.print("\n");
                        //Будем убегать от большой бактерии
                        return "bigOne";
                    }
                }
            }
            else{//Если на поле есть бактерии больше и меньше меня
                if(agar == null){//Если есть бактерии больше и меньше меня, но нет агара
                    //Если до маленькойбактерии ближе чем до большой
                    if(GameMath.distance(particle.getPosition(),bigOne.getPosition())>GameMath.distance(particle.getPosition(),smallOne.getPosition())){
                        //Будем гнаться за маленькой бактерией
                        return "smallOne";
                    }
                    else{//Если большая бактерия ближе маленькой
                        //Убегаем от большой бактерии
                        return "bigOne";
                    }
                }
                else{//Если на поле есть и агар,и большие бактерии и маленкие
                    //Если маленькая бактерия ближе чем большая 
                    if((GameMath.distance(particle.getPosition(),bigOne.getPosition()))>GameMath.distance(particle.getPosition(),smallOne.getPosition())){
                        //Гонимся за маленькой бактерией
                        return "smallOne";
                    }
                    else{//Если агар ближе чем большая бактерия
                        if((GameMath.distance(particle.getPosition(),bigOne.getPosition()))>GameMath.distance(particle.getPosition(),agar.getPosition())){
                            //Едим агар
                            return "agar";
                        }
                        else{//Если еда дальше чем большая бактерия
                           //Убегаем от большой бактерии
                           return "bigOne";
                        }
                    }
                }
            }
        }
    }
    public Particle findNearestSmallerParticle(){
        ArrayList<Particle> particlAround = game.getBots();
        ArrayList<Particle> players = game.getPlayers();
        particlAround.addAll(players);
        double minDistance = 1000000000;
        double distToP;
        Particle nearestP = null;
        for(Particle p : particlAround){
           if(p.getSize()<particle.getSize()){
               distToP = GameMath.distance(p.getPosition(),particle.getPosition());
               if(distToP<minDistance){
                   minDistance = distToP;
                   nearestP = p;
               }
           }
        }
        return nearestP;
    }
    
    public Particle findNearestBiggerParticle(){
        ArrayList<Particle> particlAround = game.getBots();
        ArrayList<Particle> players = game.getPlayers();
        particlAround.addAll(players);
        double minDistance = 1000000000;
        double distToP;
        Particle nearestP = null;
        for(Particle p : particlAround){
           if(p.getSize()>particle.getSize()){
               distToP = GameMath.distance(p.getPosition(),particle.getPosition());
               if(distToP<minDistance){
                   minDistance = distToP;
                   nearestP = p;
               }
           }
        }
        return nearestP;
    }
    
    public Particle findNearestAgar(){
        ArrayList<Particle> agars = game.getAgars();
        double minDistance = 1000000000;
        double distToP;
        Particle nearestP = null;
        for(Particle p : agars){
           distToP = GameMath.distance(p.getPosition(),particle.getPosition());
           if(distToP<minDistance && (nearestP == null || nearestP.getSize()<p.getSize())){
             minDistance = distToP;
             nearestP = p;
           }
        }
        return nearestP;
    }
    
    
    public void setCollision(int angle) {
            particle.setAngle(angle);
            particle.setSpeed(0.1);
        
    }
}
