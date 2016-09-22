/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.TileBackground;
import events.ParticleListener;
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

        Players.get(0).getGroup().setBackground(bg);
    }

    @Override
    public void update(long l) {
        game.updateGame(mousePosition());
        //spriteGroup.update(l);
        bg.update(l);
        field.update(l);
    }

    @Override
    public void render(Graphics2D g) {
        //spriteGroup.render(g);
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
            AIView ai = new AIView( particle, getImage("img/agar1.png") );
            field.addGroup(ai.getGroup());
            AIPlayers.add(ai);
        }
    }
    
    private void loadPlayers() {
        for( Particle particle : game.getPlayers()) {
            PlayerView player = new PlayerView( particle, getImage("img/agar.png") );
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
