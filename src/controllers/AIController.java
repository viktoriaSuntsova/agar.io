/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
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
    private int stepCount = 0;
    private int stepObstacleCount = 0;
    private int angleForStep;
    public void setSteps(int _stepCount){
        stepObstacleCount = _stepCount;
    }
    public int getObstacleSteps(){
        return stepObstacleCount;
    }
     public int getSteps(){
        return stepCount;
    }
    public void setAngleForStep(int _angleForStep){
        angleForStep = _angleForStep;
    }
    
    enum Priority {
        BIG_ONE,
        SMALL_ONE,
        AGAR,
        RANDOM
    }
    
    /**
     * Базовая реализация лишь проверяет, что спрайт не вышел за поля
     * @param mousePosition 
     */
    @Override
    public void update(Point mousePosition) {
        if(stepObstacleCount==0){
            if(stepCount==0){
                int angle = checkGoOutBorder();
                if(angle!=-1){
                    particle.setSpeed(3.0/particle.getSize());
                    //particle.setSpeed(0.1);
                    particle.setAngle(angle);
                    particle.fireCharacteristicsIsChanged();
                    angleForStep = angle;
                    stepCount = (int)(10 + Math.random()*(30));
                    return;
                }
                ArrayList<Particle> particlAround = game.get("bot");
                ArrayList<Particle> players = game.get("player");
                //найдем самую близкую к нам бактерию больше нас
                Particle bigOne = findNearestBiggerParticle(particlAround, players);
                //найдем самую близкую к нам бактерию меньше нас
                Particle smallOne = findNearestSmallerParticle(particlAround, players);
                //найдем самую близкую к нам агарину
                Particle agar = findNearestAgar();
                angle = 0;
                //выберем что приоритетнее в данной ситуации
                //выберем что приоритетнее в данной ситуации
                Priority priority = chooseParticle(smallOne, bigOne, agar);
                //если оказалось приоритетнее убежать от большой бактерии
                switch(priority) {
                    case BIG_ONE:
                        angle = 360 - GameMath.angle(particle.getPosition(), bigOne.getPosition());
                        break;
                    case SMALL_ONE:
                        angle = GameMath.angle(particle.getPosition(), smallOne.getPosition());
                        break;
                    case AGAR:
                        angle = GameMath.angle(particle.getPosition(), agar.getPosition());
                        break;
                    case RANDOM:
                        Random r = new Random();
                        angle = r.nextInt(360);
                        break;
                }
                //Сообщаем частице выбранный угол
                particle.setAngle(angle);
                //Сообщаем частице выбранную скорость
                particle.setSpeed(3.0/particle.getSize());

                
                particle.fireCharacteristicsIsChanged();
                angleForStep = angle;
                stepCount = (int)(5 + Math.random()*(20));
                angle = checkGoOutBorder();
                if(angle!=-1){
                    //particle.setSpeed(0.1);
                    particle.setSpeed(3.0/particle.getSize());
                    particle.setAngle(angle);
                    particle.fireCharacteristicsIsChanged();
                    angleForStep = angle;
                    stepCount = (int)(20 + Math.random()*(200));
                    return;
                }
                
            }
            else {
                 stepCount--; 
                 particle.setAngle(angleForStep);
                 
                 int angle = checkGoOutBorder();
                if(angle!=-1){
                    particle.setSpeed(0.1);
                    particle.setAngle(angle);
                    particle.fireCharacteristicsIsChanged();
                    angleForStep = angle;
                    stepCount = (int)(20 + Math.random()*(200));
                    return;
                }
                particle.fireCharacteristicsIsChanged();
            }
        }
        else{
            stepObstacleCount--;
            particle.setAngle(angleForStep);
            int angle = checkGoOutBorder();
                if(angle!=-1){
                    //particle.setSpeed(0.1);
                    particle.setSpeed(3.0/particle.getSize());
                    particle.setAngle(angle);
                    particle.fireCharacteristicsIsChanged();
                    angleForStep = angle;
                    stepCount = (int)(20 + Math.random()*(200));
                    return;
                }
            particle.fireCharacteristicsIsChanged();
        }
    }
    
    public Priority chooseParticle(Particle smallOne, Particle bigOne, Particle agar){
        Priority priority = Priority.SMALL_ONE;
        //Если на поле вообще нет больше меня и меньше меня
        if(bigOne == null && smallOne == null) {
            //Если есть агар, то к агару, иначе рандом
            priority = agar != null ? Priority.AGAR : Priority.RANDOM;
        }
        //Если на поле есть бактерии больше меня
        else if (bigOne != null) {
           // Если бактерий меньше меня нет
            if(smallOne == null) {
                //Если на поле только большая бактерия и я, убегаем от большой
                if(agar == null)
                    priority = Priority.BIG_ONE;
                //Если на поле есть бактерия больше меня и агар
                else {
                    double distanceToBig = GameMath.distance(particle.getPosition(),bigOne.getPosition()) 
                            - bigOne.getSize()/2;
                    double distanceToAgar = GameMath.distance(particle.getPosition(),bigOne.getPosition());
                     //Если до агара ближе чем до большой бактерии
                     priority = distanceToBig > distanceToAgar ? Priority.AGAR : Priority.BIG_ONE;
                }
            }
            // Если на поле есть бактерии больше и меньше меня
            else {
                double distanceToBig = GameMath.distance(particle.getPosition(),bigOne.getPosition()) 
                        - bigOne.getSize()/2;
                double distanceToSmall = GameMath.distance(particle.getPosition(), smallOne.getPosition()) 
                        - smallOne.getSize()/2;
                double distanceToAgar = GameMath.distance(particle.getPosition(),bigOne.getPosition());
                // Если агара нет или есть но расстояние до маленькой частица больше чем до большой
                if(agar == null || agar != null && distanceToBig > distanceToSmall)
                    priority = distanceToBig > distanceToSmall ? Priority.SMALL_ONE : Priority.BIG_ONE;
                else {
                    //Если маленькая бактерия ближе чем большая
                    priority = distanceToBig > distanceToAgar ? Priority.AGAR : Priority.BIG_ONE;
                }
            }
        }
        return priority;
    }
    
    // !TODO Вынести получение ботов и игроков в общий файл, и передавать как параметр
    // что бы он по три раза не перебирал и не копировал массив
    public Particle findNearestSmallerParticle(ArrayList<Particle> particlAround, ArrayList<Particle> players) {
        particlAround.addAll(players);
        double distToP;
        Particle nearestP = null;
        for(Particle p : particlAround){
            if(p.getSize() < particle.getSize() && Math.abs(p.getSize() - particle.getSize()) > 10){
                distToP = GameMath.distance(p.getPosition(),particle.getPosition());
                if(distToP < MIN_DITANCE){
                    nearestP = p;
                }
           }
        }
        return nearestP;
    }
    
    
    // !TODO Вынести получение ботов и игроков в общий файл, и передавать как параметр
    // что бы он по три раза не перебирал и не копировал массив
    public Particle findNearestBiggerParticle(ArrayList<Particle> particlAround, ArrayList<Particle> players) {
        particlAround.addAll(players);
        double distToP;
        Particle nearestP = null;
        for(Particle p : particlAround){
           if(p.getSize()>particle.getSize() && Math.abs(p.getSize() - particle.getSize()) > 10){
               distToP = GameMath.distance(p.getPosition(),particle.getPosition());
               if(distToP < MIN_DITANCE){
                   nearestP = p;
               }
           }
        }
        return nearestP;
    }
    
    
    // !TODO Здесь можно оставить
    public Particle findNearestAgar(){
        ArrayList<Particle> agars = game.get("agar");
        double distToP;
        Particle nearestP = null;
        for(Particle p : agars){
            distToP = GameMath.distance(p.getPosition(), particle.getPosition());
           // && (nearestP == null || nearestP.getSize() < p.getSize())
            if(distToP < MIN_DITANCE ){
                nearestP = p;
            }
        }
        return nearestP;
    }
    
    public void setCollision(int angle) {
            particle.setAngle(angle);
            particle.setSpeed(0.1);
        
    }
    
    public int checkGoOutBorder() {
        double x = particle.getPosition().getX(),
                y = particle.getPosition().getY();
        if(x > game.getSize().getWidth() ){ 
            return (int)(100 + Math.random()*(160));
            //return 180;
        }
        else if(y > game.getSize().getHeight()){
            return (int)(190 + Math.random()*(160));
            //return 270;
        }
        else if(x < 0){
            ArrayList<Integer> n = new ArrayList<Integer>();
            n.add((int)(1 + Math.random()*(80)));
            n.add((int)(280 + Math.random()*(80)));
            int i = (int)(Math.random());
            return n.get(i);
            //return 0;
        }
        else if(y < 0){
            return (int)(10 + Math.random()*(160));
            //return 90;
        }
        return -1;
    }
}
