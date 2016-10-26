/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.*;
import events.GameEvent;
import events.GameListener;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
    private final int botsCount = 20;
    
    /**
     * Число ботов
     */
    private final int agarCount = 100;
    /**
     * Число ботов
     */
    private final int obstacleCount = 5;
    
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
        @Override
        public void actionPerformed(ActionEvent e) {
            recreateParticles();
        }
    });
    
    public GameModel(int maxWidth, int maxHeight) {
        WIDTH = maxWidth;
        HEIGHT = maxHeight;
    }
    
    public void startGame() {
        for(int i = 0; i < botsCount; i++) {
            createBot();
        }
        for(int i = 0; i < agarCount; i++) {
            createAgar();
        }
        for(int i = 0; i < obstacleCount; i++) {
            createObstacle();
        }
        createPlayer();
        timer.start();
    }
    
    public void updateGame(Point mousePosition) {
        for(int i = 0; i < controllers.size(); i++) {
            controllers.get(i).update(mousePosition);
        }
    }
    
    private void recreateParticles() {
        for(int i = countParticles("agar"); i < agarCount; i++) {
            Particle p = createAgar();
            fireGeneratedAgar(p);
        }
    }
    
    private Point determinePosition() {
        Random r = new Random();
        return new Point(r.nextInt(WIDTH), r.nextInt(HEIGHT));
    }
    
    public Particle createPlayer() {
        Particle particle = new Particle(determinePosition(), "player", "ivan");
        particles.add( particle );
        controllers.add( new PlayerController(this, particle) );
        return particle;
    }
    
    public Particle createBot() {
        Particle particle = new Particle(determinePosition(), "bot", "bot_" + counter.get("bot"));
        particles.add( particle );
        controllers.add( new AIController(this, particle) );
        return particle; 
    }
    
    public Particle createAgar() {
        Particle particle = new Particle(determinePosition(), "agar", "agar_" + counter.get("agar"));
        particles.add( particle );
        return particle; 
    }
    
    public Particle createObstacle() {
        Particle particle = new Particle(determinePosition(), "obstacle", "obstacle_" + counter.get("obstacle"));
        particles.add( particle );
        return particle; 
    }
    
    public ArrayList<Particle> get(String type) {
        ArrayList<Particle> typeParticles = new ArrayList<>();
        ArrayList<Particle> cloneParticles = (ArrayList<Particle>) particles.clone();
        for(Particle p : cloneParticles) {
            if(p.getType().equals(type))
                typeParticles.add(p);
        }
        return typeParticles;
    }
    
    public Dimension getSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
    
    public void removeParticle(Particle p) {
        particles.remove(p);
        System.out.println("remove" + p.getName());
        for( Controller c : controllers ) {
            if( c.getParticle() == p ) {
                controllers.remove(c);
                break;
            }
        }
    }
    
    public int countParticles(String type) {
        int count = 0;
        for(Particle p : particles) {
            if(p.getType().equals(type))
                count++;
        }
        return count;
    }
    
    private GameListener gameListener = null;
    
    public void setGameListener( GameListener g ) {
        gameListener = g;
    }
    
    public void fireGeneratedAgar(Particle p) {
        GameEvent e = new GameEvent();
        e.setParticle(p);
        if( gameListener != null )
            gameListener.generatedAgar(e);
    }
    
    public void fireGeneratedBot(Particle p) {
        GameEvent e = new GameEvent();
        e.setParticle(p);
        if( gameListener != null )
            gameListener.generatedBot(e);
    }
    public void fireGeneratedPlayer(Particle p) {
        GameEvent e = new GameEvent();
        e.setParticle(p);
        if( gameListener != null )
            gameListener.generatedPlayer(e);
    }
    
}
