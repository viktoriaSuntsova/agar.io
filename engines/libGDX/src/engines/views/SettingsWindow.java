/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.GroupLayout;

/**
 *
 * @author homepc
 */
public class SettingsWindow extends JDialog implements ActionListener, Runnable {
    
    public JButton close = new JButton("close");
    public JButton btnOK = new JButton("ok");
    
    public SettingsWindow() {
        setTitle("Настройки");
        JPanel panel = initSettings();
        
        setSize(600, 400);
        setVisible(true);
        
        JPanel btns = new JPanel();
        GroupLayout btnLayout = new GroupLayout(btns);
        btns.setLayout(btnLayout);
        btns.add(btnOK);
        
        btnOK.addActionListener(this);
        
        add(btns);
        add(panel);
    }
    
    /**
     *Начать игру
     */
    public void start() {
        setVisible(false);
    }
    
    public JPanel initSettings() {
        JPanel panel = new JPanel();
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "ok") {
            start();   
        }
    }

    @Override
    public void run() {
        int i = 1;
    }
}
