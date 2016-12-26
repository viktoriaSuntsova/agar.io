/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.*;
import events.GeneratedListener;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author 999
 */
public class GameModel {
    
    /**
     * Группа объектов, участвующих в коллизиях
     */
    private ArrayList<Particle> particles = new ArrayList<>();
    
    /**
     * Число ботов
     */
    private final int botsCount = 25;
    
    /**
     * Число ботов
     */
    private final int agarCount = 50;
    /**
     * Число ботов
     */
    private final int obstacleCount = 10;    
    private int HEIGHT = 0;
    private int WIDTH = 0;
    
    /**
     * Массив контроллеров для игроков и ботов
     */
    private final List<Controller> controllers = new ArrayList<>();
    
    // Класс счетчика частиц
    private class Counter {
        private final Map counter = new HashMap<>();
        
        public int get(String type) {
            if(!counter.containsKey(type)) {
                counter.put(type, 0);
            }
            int currentValue = (int) counter.get(type);
            counter.replace(type, currentValue + 1);
            return (int) counter.get(type);
        }
    }
    
    // счетчик частиц разного типа
    private Counter counter = new Counter();
    Timer timer = new Timer(1000, new ActionListener() {
        /**
        * 
        * @param e событие
        */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                recreateParticles();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    
    /**
     * Создание модели
     * @param maxWidth - ширина
     * @param maxHeight - высота
     */
    public GameModel(int maxWidth, int maxHeight) {
        WIDTH = maxWidth;
        HEIGHT = maxHeight;
    }
    
    /**
     * Начать игру
     * @param agarCount - количество агара
     * @param botsCount - количество ботов
     * @param obstacleCount - количество препятсвий
     */
    public void startGame(int agarCount, int botsCount, int obstacleCount) {
        for(int i = 0; i < agarCount; i++) {
            createAgar();
        }
        for(int i = 0; i < obstacleCount; i++) {
            createObstacle();
        }
        for(int i = 0; i < botsCount; i++) {
            createBot();
        }
        timer.start();
    }
    
    /**
     * Обновить игру
     * @param mousePosition - позиция мыши
     */
    public void updateGame(Point mousePosition) {
        for(int i = 0; i < controllers.size(); i++) {
            controllers.get(i).update(mousePosition);
        }
    }
    /**
     * Создать новые частицы вместо съеденных
     */
    private void recreateParticles() throws InterruptedException {
        for(int i = countParticles("agar"); i < agarCount; i++) {
            Particle p = createAgar();
            fireGeneratedAgar(p);
        }
        for(int i = countParticles("bot"); i < botsCount; i++) {
            Particle p = createBot();
            fireGeneratedBot(p);
        }
    }
    /**
     * Определить позицию
     * @return позиция
     */
    private Point determinePosition() {
        Random r = new Random();
        boolean isNewPoint = false;
        Point point = new Point(r.nextInt(WIDTH), r.nextInt(HEIGHT));
        while(isNewPoint==false){
            point = new Point(r.nextInt(WIDTH), r.nextInt(HEIGHT));
            for (Particle part : particles){
                double x1 = part.getPosition().getX()-part.getSize()-50;
                double x2 = part.getPosition().getX()+part.getSize()+50;
                double y1 = part.getPosition().getY()-part.getSize()-50;
                double y2 = part.getPosition().getY()+part.getSize()+50;
                if((point.getX()<x2&&point.getX()>x1)&&(point.getY()<y2&&point.getY()>y1)){
                    isNewPoint = false;
                    break;
                }
                else{
                    isNewPoint = true;
                }
            }
            if(particles.isEmpty()){
                break;
            }
        }
        return point;
    }
    
    /**
     * Создать игрока
     * @param name - имя
     * @return частица
     */
    public Particle createPlayer(String name) {
        Particle particle = new Particle(determinePosition(), "player", name);
        particles.add( particle );
        controllers.add( new PlayerController(this, particle) );
        return particle;
    }
    
    /**
     * Создать бота
     * @return частица
     */
    public Particle createBot() {
        Particle particle = new Particle(determinePosition(), "bot", "bot_" + counter.get("bot"));
        particles.add( particle );
        AIController ai = new AIController(this, particle);
        controllers.add( ai );
        particle.addController(ai);
        return particle; 
    }
    
    /**
     * Создать агар
     * @return частица 
     */
    public Particle createAgar() {
        Particle particle = new Particle(determinePosition(), "agar", "agar_" + counter.get("agar"));
        particles.add( particle );
        return particle; 
    }
    
    /**
     * Создать препятствие
     * @return частица
     */
    public Particle createObstacle() {
        Particle particle = new Particle(determinePosition(), "obstacle", "obstacle_" + counter.get("obstacle"));
        particles.add( particle );
        return particle; 
    }
    
    /**
     * Получить список частиц
     * @param type- тип частиц
     * @return список частиц
     */
    public ArrayList<Particle> get(String type) {
        ArrayList<Particle> typeParticles = new ArrayList<>();
        ArrayList<Particle> cloneParticles = (ArrayList<Particle>) particles.clone();
        for(Particle p : cloneParticles) {
            if(p.getType().equals(type))
                typeParticles.add(p);
        }
        return typeParticles;
    }
    
    /**
     * Получиь размер
     * @return размер
     */
    public Dimension getSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
    
    /**
     * Удалить частицу
     * @param p - частца
     */
    public void removeParticle(Particle p) {
        particles.remove(p);
        for( Controller c : controllers ) {
            if( c.getParticle() == p ) {
                controllers.remove(c);
                break;
            }
        }
    }
    
    /**
     * Количество частиц
     * @param type - тип частицы
     * @return количество частиц
     */
    public int countParticles(String type) {
        int count = 0;
        for(Particle p : particles) {
            if(p.getType().equals(type))
                count++;
        }
        return count;
    }
    
    private GeneratedListener gameListener = null;
    
    /**
     * Установить слушателя
     * @param g - слушатель
     */
    public void setGameListener( GeneratedListener g ) {
        gameListener = g;
    }
    
    /**
     *
     * @param p частица
     */
    public void fireGeneratedAgar(Particle p) {
        if( gameListener != null )
            gameListener.generatedAgar(p);
    }
    
    public void fireGeneratedBot(Particle p) {
        if( gameListener != null )
            gameListener.generatedBot(p);
    }
    
}
