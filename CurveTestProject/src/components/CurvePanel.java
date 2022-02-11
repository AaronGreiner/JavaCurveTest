package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.CubicCurve2D;
import javax.swing.JPanel;

public class CurvePanel extends JPanel {
    
    private Point start = new Point(100, 100);
    private Point end = new Point(600, 600);
    private Point control1 = new Point(600, 100);
    private Point control2 = new Point(100, 600);
    
    private boolean move_ok = false;
    
    private Point pos_mouse = new Point(0, 0);
    
    public CurvePanel() {
        
        init();
    }
    
    private void init() {
        
        setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PanelmousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                PanelmouseReleased(e);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                PanelmouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                PanelmouseExited(e);
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                PanelmouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                PanelmouseMoved(e);
            }
        });
    }
    
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Antialiasing
        
        g2.setColor(Color.red);
        g2.fillOval(start.x-3, start.y-3, 7, 7);
        
        g2.setColor(Color.red);
        g2.fillOval(end.x-3, end.y-3, 7, 7);
        
        g2.setColor(Color.green);
        g2.fillOval(control1.x-3, control1.y-3, 7, 7);
        
        g2.setColor(Color.yellow);
        g2.fillOval(control2.x-3, control2.y-3, 7, 7);
        
        CubicCurve2D c = new CubicCurve2D.Double();
        c.setCurve(start.x, start.y, control1.x, control1.y, control2.x, control2.y, end.x, end.y);
        g2.setColor(Color.white);
        g2.draw(c);
    }
    
    private float getDistPoint(Point p){
        
        float x1 = pos_mouse.x;
        float y1 = pos_mouse.y;
        float x2;
        float y2;
        float dist;
        
        x2 = p.x;
        y2 = p.y;
        
        dist = (float)Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        
        return dist;
    }
    
    private void updatePos(int x, int y) {
        
        float f_start = getDistPoint(start);
        float f_end = getDistPoint(end);
        float f_control1 = getDistPoint(control1);
        float f_control2 = getDistPoint(control2);
        
        if (f_start <= f_end && f_start <= f_control1 && f_start <= f_control2) {
            
            start.x = x;
            start.y = y;
            
            this.repaint();
            
        } else if (f_end <= f_start && f_end <= f_control1 && f_end <= f_control2) {
            
            end.x = x;
            end.y = y;
            
            this.repaint();
            
        } else if (f_control1 <= f_end && f_control1 <= f_start && f_control1 <= f_control2) {
            
            control1.x = x;
            control1.y = y;
            
            this.repaint();
            
        } else if (f_control2 <= f_end && f_control2 <= f_control1 && f_control2 <= f_start) {
            
            control2.x = x;
            control2.y = y;
            
            this.repaint();
        }
        
    }
    
    private void PanelmousePressed(MouseEvent e) {
        if (move_ok) {
            
            updatePos(e.getX(), e.getY());
            this.repaint();
        }
    }
    
    private void PanelmouseDragged(MouseEvent e) {
        if (move_ok) {
            
            updatePos(e.getX(), e.getY());
            this.repaint();
        }
    }
    
    private void PanelmouseMoved(MouseEvent e) {
        pos_mouse.x = e.getX();
        pos_mouse.y = e.getY();
    }
    
    private void PanelmouseReleased(MouseEvent e) {
        
    }
    
    private void PanelmouseEntered(MouseEvent e) {
        move_ok = true;
    }
    
    private void PanelmouseExited(MouseEvent e) {
        move_ok = false;
    }
    
    
}
