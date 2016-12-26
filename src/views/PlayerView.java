/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import events.ParticleEvent;
import events.ParticleListener;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import models.Particle;

/** Здесь будет
 *
 * @author 999
 */
public class PlayerView extends SpriteView {
    
    /**
     * Создать отображение для игрока
     * @param _particle- чатсица
     */
    public PlayerView(Particle _particle) {
        particle = _particle;
        setSpeed(particle.getAngle());
        setColor(Color.GREEN);
        setPosition(particle.getPosition());
        particle.addPlayerActionListener(new ParticleObserver());
    }
    
    public void setPicture(String picture) {
        image = picture;
        repaint();
    }
}
