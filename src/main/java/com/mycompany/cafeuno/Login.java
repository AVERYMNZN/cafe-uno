/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cafeuno;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.SystemInfo;
import components.CustomRoundButton;
import modules.FontLoader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import javax.annotation.PostConstruct;
import javax.swing.*;

import modules.ImagePanel;
import modules.StringManager;
import org.springframework.stereotype.Component;

/**
 *
 * @author avery
 */
@Component
public class Login extends JFrame{
    
    private JPanel headerPanel;
    private Point mouseClickPoint;
    private JLabel usernameLabel, passwordLabel, loginWithLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private CustomRoundButton googleLoginButton;
    
    public Login() {
    }

    private void initialize() {
        SwingUtilities.invokeLater(() -> {
            setLookAndFeel();
            setupFrame();
            initComponents();

        });
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("Button.arc", 999);
            UIManager.put("Component.focusedBorderColor", new Color(34, 35, 38));
            UIManager.put("Component.focusColor", new Color(34, 35, 38, 80));

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    
    private void initComponents() {
        FontLoader fl = new FontLoader();

        JPanel imagePanel = new ImagePanel("src/main/resources/images/cafe-login-image.jpg");
        JPanel titleImagePanel = new ImagePanel("src/main/resources/images/cafe-uno-title-image.png");

        usernameLabel = new JLabel(StringManager.get("username.label"));
        usernameField = new JTextField();

        passwordLabel = new JLabel(StringManager.get("password.label"));
        passwordField = new JPasswordField();

        loginButton = new JButton(StringManager.get("login.label"));

        loginWithLabel = new JLabel(StringManager.get("loginWith.label"));

        googleLoginButton = new CustomRoundButton(StringManager.get("googleLogin.label"), new ImageIcon(new ImageIcon("src/main/resources/images/search.png")
                .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));

        // Set fonts
        usernameLabel.setFont(fl.loadHintFont(16f));
        usernameField.setFont(fl.loadTextFont(14f));

        passwordLabel.setFont(fl.loadHintFont(16f));
        passwordField.setFont(fl.loadTextFont(14f));

        loginButton.setFont(fl.loadButtonFont(18f));

        loginWithLabel.setFont(fl.loadHintFont(12f));

        googleLoginButton.setFont(fl.loadButtonFont(16f));
        // Set colors
        loginButton.setForeground(Color.white);
        loginButton.setBackground(new Color(34, 35, 38));

        // Set bounds
        imagePanel.setBounds(0, 0, 540, 550);
        titleImagePanel.setBounds(620, 60, 210, 90);

        usernameLabel.setBounds(620, 190, 100, 20);
        usernameField.setBounds(615, 190+25, 225, 40);

        passwordLabel.setBounds(620, 190+25+55, 100, 20);
        passwordField.setBounds(615, 190+25+55+25, 225, 40);

        loginButton.setBounds(621, 190+25+55+25+70, 210, 50);

        loginWithLabel.setBounds(697, 190+25+55+25+70+90, 100, 20);
        googleLoginButton.setBounds(621, 190+25+55+25+70+60, 210, 50);
        // Add components
        add(imagePanel); add(titleImagePanel);
        add(usernameLabel); add(usernameField);
        add(passwordLabel); add(passwordField);
        add(loginButton);
//        add(loginWithLabel);
        add(googleLoginButton);
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
