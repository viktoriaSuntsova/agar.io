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
import java.util.List;
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
    private ArrayList<Particle> players = new ArrayList<>();
    private ArrayList<Particle> AIplayers = new ArrayList<>();
    private ArrayList<Particle> agars = new ArrayList<>();
    private ArrayList<Particle> obstacles = new ArrayList<>();
    
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
        System.out.println("count agars: " + agars.size());
        for(int i = agars.size(); i < agarCount; i++) {
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
        players.add( particle );
        controllers.add( new PlayerController(this, particle) );
        return particle;
    }
    
    public Particle createBot() {
        Particle particle = new Particle(determinePosition(), "bot", "bot_" + (AIplayers.size() + 1));
        AIplayers.add( particle );
        controllers.add( new AIController(this, particle) );
        return particle; 
    }
    
    public Particle createAgar() {
        Particle particle = new Particle(determinePosition(), "agar", "agar_" + (agars.size() + 1));
        agars.add( particle );
        return particle; 
    }
    
    public Particle createObstacle() {
        Particle particle = new Particle(determinePosition(), "obstacle", "obstacle_" + (obstacles.size() + 1));
        obstacles.add( particle );
        return particle; 
    }
    
    public ArrayList<Particle> getBots() {
        return (ArrayList<Particle>) AIplayers.clone();
    }
    
    public ArrayList<Particle> getPlayers() {
        return (ArrayList<Particle>) players.clone();
    }
    
    public ArrayList<Particle> getAgars() {
        return (ArrayList<Particle>) agars.clone();
    }
    
    public Dimension getSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
    
    public void removeParticle(Particle p){
        if("player".equals(p.getType())){
            players.remove(p);
        }
        else if("agar".equals(p.getType())){
            agars.remove(p);
        }
        else if("bot".equals(p.getType())){
            AIplayers.remove(p);
        }
        for( Controller c : controllers ) {
            if( c.getParticle() == p ) {
                controllers.remove(c);
                break;
            }
        }
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
