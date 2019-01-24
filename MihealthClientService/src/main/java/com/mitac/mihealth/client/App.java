package com.mitac.mihealth.client;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.mitac.mihealth.client.view.MainFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	EventQueue.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });    
    }
}
