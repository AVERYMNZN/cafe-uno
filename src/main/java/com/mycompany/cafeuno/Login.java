/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cafeuno;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.SystemInfo;
import com.mycompany.cafeuno.utilities.StringManager;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modules.ImagePanel;

/**
 *
 * @author avery
 */
public class Login extends JFrame{
    
    private JPanel headerPanel;
    private Point mouseClickPoint;
    private JLabel title;
    private JTextField username;
    private JPasswordField userPassword;
    private JButton login;
    
    public Login() {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("Button.arc", 999);
            UIManager.put("Component.focusedBorderColor", new Color(201, 40, 89));
            UIManager.put("Component.focusColor", new Color(201, 40, 89, 80));
            
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            setupFrame();
            initComponents();
        });
    }
    
    private void initComponents() {
        JPanel imagePanel = new ImagePanel("src/main/resources/images/cafe-login-image.jpg");
        JPanel titleImagePanel = new ImagePanel("src/main/resources/images/cafe-uno-title-image.png");
        
        imagePanel.setBounds(0, 0, 540, 550);
        titleImagePanel.setBounds(620, 100, 210, 90);
        
        add(imagePanel); add(titleImagePanel);
    }
    
    private void setupFrame() {
        setUndecorated(true); // Removes title bar
        getContentPane().setBackground(new Color(235, 235, 235));
        
        if (SystemInfo.isMacOS) {
            getRootPane().putClientProperty("apple.awt.windowTitleVisible", false);
        }
        
        setSize(900, 550);
        setTitle(StringManager.get("app.topHint"));
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 900, 40);
        headerPanel.setBackground(new Color(0, 0, 0, 0));
        headerPanel.setOpaque(false);
        setupWindowDragging();
        add(headerPanel);
        

        
        setVisible(true);
    }
    
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        applyRoundedShape();
    }
    
    private void applyRoundedShape() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (isDisplayable()) {
                    setShape(new RoundRectangle2D.Double(
                        0, 0, getWidth(), getHeight(), 20, 20));
                }
            }
        });
    }
    
    private void setupWindowDragging() {
        headerPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseClickPoint = e.getPoint();
            }
        });
        
        headerPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (mouseClickPoint != null) {
                    Point screenPoint = e.getLocationOnScreen();
                    setLocation(screenPoint.x - mouseClickPoint.x, screenPoint.y - mouseClickPoint.y);
                }
            }
        });
    }
}
