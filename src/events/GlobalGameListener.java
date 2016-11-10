/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.util.EventListener;

/**
 *
 * @author 999
 */
public interface GlobalGameListener extends EventListener {
    public void StartGame(GlobalGameEvent e);
    public void StartGameWithPlayer(GlobalGameEvent e);
}
