/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author avery
 */
@Component
public class StringManager {
    private static final Properties props = new Properties();
    
    static {
        try(InputStream input = StringManager.class.getClassLoader().getResourceAsStream("strings.properties")) {
            if(input != null) {
                props.load(input);
            } else {
                System.out.println("Could not find strings.properties");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            System.out.println("Missing property for key: " + key);
            return "!" + key + "!";
        }
        return value;
    }
}
