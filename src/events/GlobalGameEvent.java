/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.util.EventObject;

/**
 *
 * @author 999
 */
public class GlobalGameEvent {
    
    public int countBot = 15;
    public int countAgar = 50;
    public int countObstacle = 10;
    public boolean createPlayer = false;
    public String playerName = "=)";
    public String playerAva = null;
    
    /**
     *
     * @param cBot
     * @param cAgar
     * @param cObtacle
     */
    public GlobalGameEvent(int cBot, int cAgar, int cObtacle) {
         countBot = cBot;
         countAgar = cAgar;
         countObstacle = cObtacle;
    }
    
    public GlobalGameEvent(int cBot, int cAgar, int cObtacle, boolean player, String name, String ava) {
         countBot = cBot;
         countAgar = cAgar;
         countObstacle = cObtacle;
         createPlayer = player;
         playerName = name;
         playerAva = ava;
    }
    
}
