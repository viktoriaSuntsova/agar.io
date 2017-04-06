/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

import com.golden.gamedev.Game;
import java.awt.Dimension;

/**
 * Загрузчик игры
 */
public class GameLoader {
    
    public GameLoader() {
        m_loader = new com.golden.gamedev.GameLoader();
    }
    /**
     * Устанавливает игру для отображения
     * @param game игра
     * @param windowSize размер экрана
     * @param fullscreen играть в фуллскрин?
     */
    public void setup(Game game, Dimension windowSize, boolean fullscreen) {
        m_loader.setup(game, windowSize, fullscreen);
    }
    
    /**
     * Запускает игру, блокируя дальнейшее исполнение
     */
    public void start() {
        m_loader.start();
    }
    
    /**
     * Загрузчик игры
     */
    com.golden.gamedev.GameLoader m_loader;
}
