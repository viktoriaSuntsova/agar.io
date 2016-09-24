/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.TileBackground;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import models.GameModel;
import models.Particle;

/**
 *
 * @author 999
 */
public class GameView extends Game {
    
    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;
    
    private int[][] tiles = new int[WIDTH][HEIGHT];
    
    private final PlayField field = new PlayField();
    
    /**
     * Фон игры
     */
    private TileBackground bg = null;
    
    
    private GameModel game = new GameModel(WIDTH, HEIGHT);
    
    ArrayList<PlayerView> Players = new ArrayList<>();
    ArrayList<AIView> AIPlayers = new ArrayList<>();

    @Override
    public void initResources() {
        
        loadPlayers();
        loadBots();

        bg = new TileBackground(getImages("img/background.png", 1, 1), tiles);
        bg.setClip(0, 0, this.dimensions().width, this.dimensions().height);

        field.setBackground(bg);
    }

    @Override
    public void update(long l) {
        game.updateGame(mousePosition());
        bg.update(l);
        field.update(l);
    }

    @Override
    public void render(Graphics2D g) {
        bg.render(g);
        field.render(g);
        if (Players.get(0) != null) {
            bg.setToCenter(Players.get(0));
        }
    }
    
    /**
     * Текущая позиция координат мыши
     * @return 
     */
    public Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }
    
    private void loadBots() {
        for( Particle particle : game.getBots() ) {
            AIView ai = new AIView( particle );
            field.addGroup(ai.getGroup());
            field.addCollisionGroup( ai.getGroup(), Players.get(0).getGroup(), new Collision());
            AIPlayers.add(ai);
        }
    }
    
    private void loadPlayers() {
        for( Particle particle : game.getPlayers()) {
            PlayerView player = new PlayerView( particle );
            field.addGroup(player.getGroup());
            Players.add(player );
        }
    }
    
    private void loadSprites() {
        for( Particle particle : game.getPlayers()) {
            PlayerView player = new PlayerView( particle );
            field.addGroup(player.getGroup());
            Players.add(player );
        }
    }
    
    /**
     * Возвращает размеры окна для изображения
     * @return 
     */
    public Dimension dimensions() {
        return new Dimension(WIDTH, HEIGHT);
    }
    
}
