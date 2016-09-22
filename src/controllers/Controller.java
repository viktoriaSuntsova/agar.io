/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Point;
import models.*;

/**
 *
 * @author 999
 */
public class Controller {
    
    protected GameModel game = null;
    
    protected Particle particle = null;
    
    public Controller(GameModel _game, Particle _particle) {
        game = _game;
        particle = _particle;
    }
    
    public void update(Point mousePosition) {
        
    }
}
