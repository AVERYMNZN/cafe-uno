package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CustomRoundButton extends JComponent {
    private String text;
    private Icon icon;
    private Color backgroundColor = Color.WHITE;
    private Color hoverColor = new Color(248, 249, 250);
    private Color pressedColor = new Color(242, 243, 244);
    private Color textColor = new Color(60, 64, 67);
    private Color borderColor = new Color(218, 220, 224);
    private int cornerRadius = 50; // Changed from 25 to match Google's style
    private boolean isHovered = false;
    private boolean isPressed = false;
    private List<ActionListener> actionListeners = new ArrayList<>();

    public CustomRoundButton(String text) {
        this(text, null);
    }

    public CustomRoundButton(String text, Icon icon) {
        this.text = text;
        this.icon = icon;

        // Remove default preferred size for null layout
        setFocusable(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                isPressed = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                requestFocusInWindow();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
                if (contains(e.getPoint())) {
                    fireActionPerformed();
                }
            }
        });

        // Add keyboard support
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE ||
                        e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    isPressed = true;
                    repaint();
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE ||
                        e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    isPressed = false;
                    repaint();
                    fireActionPerformed();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Create rounded rectangle
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius);

        // Fill background
        Color bgColor = backgroundColor;
        if (isPressed) {
            bgColor = pressedColor;
        } else if (isHovered) {
            bgColor = hoverColor;
        }
        g2.setColor(bgColor);
        g2.fill(roundedRect);

        // Draw border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1f));
        g2.draw(roundedRect);

        // Draw focus indicator (subtle) with matching radius
        if (isFocusOwner()) {
            g2.setColor(new Color(66, 133, 244, 60));
            g2.setStroke(new BasicStroke(1.5f));
            // Recalculate effective radius for focus indicator
            int focusRadius = cornerRadius >= height / 2 ? height - 1 : cornerRadius - 1;
            g2.draw(new RoundRectangle2D.Float(1, 1, width - 2, height - 2, focusRadius, focusRadius));
        }

        // Calculate content positioning
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = text != null ? fm.stringWidth(text) : 0;
        int iconWidth = icon != null ? icon.getIconWidth() : 0;
        int iconHeight = icon != null ? icon.getIconHeight() : 0;
        int gap = (icon != null && text != null) ? 10 : 0; // Slightly larger gap for better spacing
        int totalWidth = iconWidth + gap + textWidth;

        int startX = (width - totalWidth) / 2;
        int iconY = (height - iconHeight) / 2;
        int textY = (height - fm.getHeight()) / 2 + fm.getAscent();

        // Draw icon
        if (icon != null) {
            icon.paintIcon(this, g2, startX, iconY);
        }

        // Draw text
        if (text != null) {
            g2.setColor(textColor);
            // Font already set above
            g2.drawString(text, startX + iconWidth + gap, textY);
        }

        g2.dispose();
    }

    public void addActionListener(ActionListener listener) {
        actionListeners.add(listener);
    }

    public void removeActionListener(ActionListener listener) {
        actionListeners.remove(listener);
    }

    private void fireActionPerformed() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, text);
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(event);
        }
    }

    // Getters and setters
    public String getText() { return text; }
    public void setText(String text) { this.text = text; repaint(); }

    public Icon getIcon() { return icon; }
    public void setIcon(Icon icon) { this.icon = icon; repaint(); }

    public Color getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(Color backgroundColor) { this.backgroundColor = backgroundColor; repaint(); }

    public int getCornerRadius() { return cornerRadius; }
    public void setCornerRadius(int cornerRadius) { this.cornerRadius = cornerRadius; repaint(); }

    // Additional setters for customization
    public void setHoverColor(Color hoverColor) { this.hoverColor = hoverColor; }
    public void setPressedColor(Color pressedColor) { this.pressedColor = pressedColor; }
    public void setTextColor(Color textColor) { this.textColor = textColor; repaint(); }
    public void setBorderColor(Color borderColor) { this.borderColor = borderColor; repaint(); }
}