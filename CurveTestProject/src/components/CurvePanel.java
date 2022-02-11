package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class CurvePanel extends JPanel {
    
    
    public CurvePanel() {
        
        init();
    }
    
    private void init() {
        
        setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
        
    }
    
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Antialiasing
        
        
    }
    
}
