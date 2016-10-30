/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agar.io;

import com.golden.gamedev.GameLoader;
import java.awt.Dimension;
import views.GameView;

/**
 *
 * @author 999
 */
public class AgarIo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        GameView game_view = new GameView();
        game.setup(game_view, new Dimension(800,600), false);//Инициализация графического движка
        game.start();
    }
    
}
