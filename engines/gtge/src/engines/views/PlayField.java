/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

/**
 *
 * @author homepc
 */
public class PlayField extends com.golden.gamedev.object.PlayField {

    public void setBackground(Background bg) {
        super.setBackground(bg);
    }
    
    @Override
    public void update(long elapsed) {
        super.update(elapsed);
    }
    
    public void render(Graphics2D g) {
        super.render(g.get());
    }
    
}
