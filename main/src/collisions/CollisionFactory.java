/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collisions;

import engines.views.BasicCollision;

/**
 * Фабрика генерирующая коллизии
 */
public class CollisionFactory {
    
    /**
     * Создание коллизии
     * @param first - тип первого элемента коллизии
     * @param second - тип второго элемента коллизии
     * @return - группа коллизии
     */
    public static BasicCollision createCollision (String first, String second) {
        BasicCollision collision = null;
        switch(second) {
            case "bot":
                collision = first.equals("bot") 
                        ? new BotBotCollision()
                        : new PlayerBotCollision();
            break;
            case "agar":
                collision = new AgarCollision();
            break;
            case "obstacle":
                collision = first.equals("bot") ? new ObstacleAICollision(): new ObstaclePlayerCollision();
            break;
        }
        return collision;
    }
}
