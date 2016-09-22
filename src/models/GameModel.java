/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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
    
    /**
     * Число ботов
     */
    private final int botsCount = 10;
    
    /**
     * Число ботов
     */
    private final int spriteCount = 10;
    
    /**
     * Массив контроллеров для игроков и ботов
     */
    private final List<Controller> controllers = new ArrayList<>();
    
    public GameModel(int maxWidth, int maxHeight) {
        createPlayer(maxWidth, maxHeight);
        createBot(maxWidth, maxHeight);
        //createBot(maxWidth, maxHeight);
        //createBot(maxWidth, maxHeight);
    }
    
    private void startGame() {
        
    }
    
    public void updateGame(Point mousePosition) {
        for(Controller controller : controllers) {
            controller.update(mousePosition);
        }
    }
    
    public Particle createPlayer(int maxWidth, int maxHeight) {
        Particle particle = new Particle(maxWidth, maxHeight);
        particle.setName("ivan");
        players.add( particle );
        controllers.add( new PlayerController(this, particle) );
        return particle;
    }
    
    public Particle createBot(int maxWidth, int maxHeight) {
        Particle particle = new Particle(maxWidth, maxHeight);
        particle.setName("bot_" + (AIplayers.size() + 1));
        AIplayers.add( particle );
        controllers.add( new AIController(this, particle) );
        return particle; 
    }
    
    public ArrayList<Particle> getBots() {
        return AIplayers;
    }
    
    public ArrayList<Particle> getPlayers() {
        return players;
    }
    
}
