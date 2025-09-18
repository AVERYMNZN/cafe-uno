/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cafeuno;

import appconfig.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

/**
 *
 * @author avery
 */
public class CafeUno {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


        SwingUtilities.invokeLater(() -> {
            Login login = context.getBean(Login.class);
            login.setVisible(true);
        });
    }
}
