/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import settings.PlayerSettings;
import collisions.*;
import events.*;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import models.GameModel;
import models.Particle;
import engines.views.PlayField;
import engines.views.SpriteGroup;
import engines.views.Background;
import engines.views.SystemFont;

/**
 *
 * @author Ivan
 */
public class GameView extends engines.views.GameView {
    
    private final int WIDTH = 2000;
    private final int HEIGHT = 1000;
    
    private final int[][] tiles = new int[WIDTH][HEIGHT];
    
    private final PlayField field = new PlayField();
    
    /**
     * Фон игры
     */
    private Background bg = null;
    
    
    private final GameModel game = new GameModel(WIDTH, HEIGHT);
    
    ArrayList<SpriteView> Sprites = new ArrayList<>();
    
    private SpriteGroup agarParticles = new SpriteGroup("agar");
    private SpriteGroup obstacleParticles = new SpriteGroup("obstacle");
    private ArrayList<SpriteGroup> enemies = new ArrayList<>();
    private SpriteGroup player = new SpriteGroup("player");
    
    private int countBot = 0;
    private int countAgar = 0;
    private int countObstacle = 0;
    private boolean isCreatePlayer = true;
    
    PlayerSettings settings = null;
    
    private SystemFont font;
    private SystemFont bigFont;
    
    private int AteParticles = 0;
    
    private String resultString = "";
    private String againString = "PRESS \"SPACE\" AND WILL START YOUR GAME!";

    /**
     * Добавиь игрока
     * @param name - имя
     * @param picture - изображение
     */
    public void addPlayer(String name, String picture) {
        Particle _player = game.createPlayer(name);
        PlayerView pl = new PlayerView(_player);
        pl.setPicture(picture);
        pl.particle.setGameListener(new GameObserver());
        player.add(pl);
    }
    
    /**
     * Установить настройки
     * @param cAgar - кол-во агара
     * @param cBot - кол-во ботов
     * @param cObstacle -  кол-во препятсвий
     * @param playerCreate - игрок
     */
    public void setSettings(int cAgar, int cBot, int cObstacle, boolean playerCreate) {
        countAgar       = cAgar;
        countBot        = cBot;
        countObstacle   = cObstacle;
        isCreatePlayer  = playerCreate;
    }

    /**
     * Инициализация игры
     */
    @Override
    public void initResources() {
        game.startGame(countAgar, countBot, countObstacle);
        game.setGameListener(new GeneratedObserver());
        
        
        //font  = fontManager.getFont(getImage("libs/font.fnt"));
        //bigFont  = fontManager.getFont(getImage("libs/font.fnt"));

        loadAgars();
        loadBots();
        loadObstacle();
        
        field.addGroup(agarParticles);
        field.addGroup(obstacleParticles);
        field.addGroup(player);
        for(SpriteGroup enemy : enemies)
            field.addGroup(enemy);
        
        field.addCollisionGroup(player, agarParticles, new AgarCollision());
        field.addCollisionGroup(player, obstacleParticles, new ObstaclePlayerCollision());

        
        
        bg = new Background(getImages("img/background.png", 1, 1), tiles);
        bg.setClip(0, 0, this.dimensions().width, this.dimensions().height);
        bg.setTotalClip(WIDTH, HEIGHT);

        // TODO pzdc
        field.setBackground(bg);
        
        settings = new PlayerSettings();
        settings.setGameListener(new SettingsObserver());
        addPlayer("player", "");
        /*if(!isCreatePlayer) {
            settings.setVisible(true);
        }*/
    }

    @Override
    public void update(long l) {
        game.updateGame(mousePosition());
        bg.update(l);
        field.update(l);
    }
    
    public void renderInContext(engines.views.Graphics2D g) {
        bg.render(g);                       
        field.render(g);
        //fnt.drawString(g, "Agar collected: " + String.valueOf(this.agarCollected), 20, 20);
        PlayerView player = (PlayerView) this.player.getActiveSprite();
        if (player != null)
        {
            bg.setToCenter(player);
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
        for( Particle particle : game.get("bot") ) {
            AIView ai = new AIView( particle );
            ai.particle.setGameListener(new GameObserver());
            SpriteGroup aiGroup = new SpriteGroup(particle.getName());
            aiGroup.add(ai);
            field.addGroup(aiGroup);
            field.addCollisionGroup(aiGroup, agarParticles, new AgarCollision());
            field.addCollisionGroup(aiGroup, obstacleParticles, new ObstacleAICollision());
            field.addCollisionGroup(player, aiGroup, new PlayerBotCollision());
            /*for(SpriteGroup enemy : enemies ) {
                field.addCollisionGroup(enemy, aiGroup, new BotBotCollision());
            }*/
            enemies.add(aiGroup);
            Sprites.add(ai);
        }
    }
    
    private void loadPlayers() {
        for( Particle particle : game.get("player")) {
            PlayerView pl = new PlayerView( particle );
            pl.particle.setGameListener(new GameObserver());
            player.add(pl);
        }
    }
    
    private void loadAgars() {
        for( Particle particle : game.get("agar")) {
            AgarView agar = new AgarView( particle );
            agar.particle.setGameListener(new GameObserver());
            agarParticles.add(agar);
        }
    }
    
    private void loadObstacle() {
        for( Particle particle : game.get("obstacle")) {
            ObstacleView obstacle = new ObstacleView( particle );
            obstacle.particle.setGameListener(new GameObserver());
            obstacleParticles.add(obstacle);
        }
    }
    
    /**
     * Возвращает размеры окна для изображения
     * @return 
     */
    public Dimension dimensions() {
        return new Dimension(WIDTH, HEIGHT);
    }
    
    protected class GameObserver implements GameListener {

        @Override
        public void ParticleDied(Particle p) {
            game.removeParticle(p);
            agarParticles.removeInactiveSprites();
            SpriteGroup sg = (SpriteGroup) field.getGroup(p.getName());
            System.out.println("group name = " + p.getName());
            if(sg != null) {
                field.removeGroup(sg);
                enemies.remove(sg);
            }
            if(p.getType().equals("player")) {
                player.removeInactiveSprites();
                resultString = "YOU LOSE WITH SCORE: " + AteParticles;
                AteParticles = 0;
                settings.setVisible(true);
            }
        }

        @Override
        public void AteParticle() {
            AteParticles = AteParticles == -1 ? 0 : (AteParticles + 1);
        }
    }
    
    protected class GeneratedObserver implements GeneratedListener {

        @Override
        public void generatedAgar(Particle p) {
            AgarView agar = new AgarView(p);
            agar.particle.setGameListener(new GameObserver());
            agarParticles.add(agar);
        }

        @Override
        public void generatedBot(Particle p) {
            AIView ai = new AIView(p);
            ai.particle.setGameListener(new GameObserver());
            SpriteGroup aiGroup = new SpriteGroup(ai.particle.getName());
            aiGroup.add(ai);
            enemies.add(aiGroup);
            field.addGroup(aiGroup);
            field.addCollisionGroup(player, aiGroup, new PlayerBotCollision());
            field.addCollisionGroup(aiGroup, agarParticles, new AgarCollision());
            field.addCollisionGroup(aiGroup, obstacleParticles, new ObstacleAICollision());
            for(SpriteGroup group : enemies)
                field.addCollisionGroup(aiGroup, group, new BotBotCollision());
        }
    }
    
    protected class SettingsObserver implements SettingsListener {

        @Override
        public void createNewPlayer(String name, String ava) {
            addPlayer(name, ava);
        }
    }
}
