/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author 999
 */
public class ImageWork {
    
    public static BufferedImage getImage(String type, int size) throws IOException {
        BufferedImage img = null;
        try {
            Image originalImage = ImageIO.read(new File("img/" + type+ ".png"));
            Image scaled = originalImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            //Создать  BufferedImage
            img = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        } catch(IOException ex) {
            Logger.getLogger("No such file in the directory");
        }
        return img;
    }
    
}
