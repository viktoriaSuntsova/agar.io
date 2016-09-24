/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.funbox.GameSettings;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author 999
 */
public class SettingsView extends GameSettings {

    @Override
    public void start() {
        System.out.println("Start");
    }
    
    JCheckBox opengl;

    protected JPanel initSettings() {
       JPanel pane = super.initSettings();
       this.setTitle("Стартовые настройки");
       this.setSize(400, 200);
       // add opengl option
       opengl = new JCheckBox("Вот так", true);
       pane.add(opengl, 0);
       JPanel size = new JPanel();
       size.add(new JLabel("Lable"));
       size.add(new JTextField("Lable"));
       pane.add(size);
       return pane;
    }
}
