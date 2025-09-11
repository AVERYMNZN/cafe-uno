/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules;

/**
 *
 * @author avery
 */
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FontLoader {
    private static final Map<String, Font> cache = new HashMap<>();
    
    private Font loadFont(String path, float size, int style) {
        String key = path + "_" + size + "_" + style;
        
        // Quick cache check
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        
        try (InputStream fontStream = getClass().getClassLoader().getResourceAsStream(path)) {
            if (fontStream == null) {
                throw new IOException("Font not found: " + path);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(style, size);
            cache.put(key, font);
            System.out.println("Loaded font: " + font.getFontName() + " size " + font.getSize2D());
            return font;
            
        } catch (Exception e) {
            e.printStackTrace();
            Font fallback = new Font("Dialog", Font.PLAIN, (int) size);
            cache.put(key, fallback);
            return fallback;
        }
    }
    
    public Font loadHintFont(float size) {
        return loadFont("fonts/Qanelas-Medium.ttf", size, Font.PLAIN);
    }
    
    public Font loadTitleFont(float size) {
        return loadFont("fonts/Qanelas-ExtraBold.otf", size, Font.PLAIN);
    }
    
    public Font loadButtonFont(float size) {
        return loadFont("fonts/Qanelas-Bold.ttf", size, Font.PLAIN);
    }
    
    public Font loadTextFont(float size) {
        return loadFont("fonts/Roboto.ttf", size, Font.PLAIN);
    }
    
    public Font loadTextFontBold(float size) {
        return loadFont("fonts/Roboto.ttf", size, Font.BOLD);
    }
    
    public Font loadCustomFont(String path, float size) {
        return loadFont(path, size, Font.PLAIN);
    }
    
    public Font loadCustomFont(String path, float size, int style) {
        return loadFont(path, size, style);
    }
    
    // Utility method to clear cache if needed
    public void clearCache() {
        cache.clear();
        System.out.println("Font cache cleared");
    }
    
    // Get cache size for debugging
    public int getCacheSize() {
        return cache.size();
    }
}