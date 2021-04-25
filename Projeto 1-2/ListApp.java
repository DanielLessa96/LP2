import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;


import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();
    Figure focus = null;
    


    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = rand.nextInt(350);
                    int y = rand.nextInt(350);
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);
                    int r1 = rand.nextInt(255);
                    int g1 = rand.nextInt(255);
                    int b1 = rand.nextInt(255);
                    int r2 = rand.nextInt(255);
                    int g2 = rand.nextInt(255);
                    int b2 = rand.nextInt(255);
                    if (evt.getKeyChar() == 'r') {
                        figs.add(new Rect(x,y, w,h,new Color(r1,g1,b1), new Color(r2,g2,b2)));

                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(x,y, w,h,new Color(r1,g1,b1), new Color(r2,g2,b2)));

                    } else if (evt.getKeyChar() == 'l') {
                        figs.add(new Line(x,y, w,h,new Color(r1,g1,b1), new Color(r2,g2,b2)));

                    } else if (evt.getKeyChar() == 'p') {
                            figs.add(new Poly(x,y, w,h,new Color(r1,g1,b1), new Color(r2,g2,b2)));
                        
                    } else if(evt.getKeyCode() == KeyEvent.VK_DELETE){
                        figs.remove(focus);

                    } else if(evt.getKeyChar() == '+'){
                        if(focus.w < 350 & focus.h < 350) {
                          focus.h = focus.h + 10;
                            focus.w = focus.w + 10;
                        }
                    } else if(evt.getKeyChar() == '-'){
                        if(focus.w > 0 & focus.h > 0) {
                            focus.h = focus.h - 10;
                            focus.w = focus.w - 10;
                        }
                    } else if(evt.getKeyCode() == KeyEvent.VK_UP){
                        focus.y = focus.y - 10;
                    } else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
                        focus.y = focus.y + 10;
                    } else if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                        focus.x = focus.x - 10;
                    } else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                        focus.x = focus.x + 10;
                   } 
                    repaint();
                }
            }
        );

        this.addMouseMotionListener(
        	new MouseMotionAdapter() {
        		public void mouseDragged(MouseEvent evt) {
                    focus.x = evt.getX() - (focus.w/2);
        			focus.y = evt.getY() - (focus.h/2);
                    repaint();
                }
        	}
        );
        
        this.addMouseListener(
        	new MouseAdapter() {
        		public void mousePressed(MouseEvent evt) {
        			focus = null;
        			for(Figure fig: figs) {
                        if (fig.getClass().equals(Poly.class)){
                            if((fig.x - fig.w) <= evt.getX() &  (fig.y - fig.h) <= evt.getY() & (fig.x + fig.w)>= evt.getX() & (fig.y + fig.h) >= evt.getY()){
                                figs.remove(fig);
                                focus = fig;
                                figs.add(focus);
                                repaint();
                                setCursor(new Cursor(Cursor.HAND_CURSOR));
                            }
                        }
                        if(fig.x <= evt.getX() & fig.y <= evt.getY() & (fig.x + fig.w)>= evt.getX() & (fig.y + fig.h) >= evt.getY()) {       				
                            figs.remove(fig);
                            focus = fig;
                            figs.add(focus);
                            repaint();
                            setCursor(new Cursor(Cursor.HAND_CURSOR));
        				} else {
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        }
        			}
        		}
        	}
        );

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        if (focus != null){
            if (focus.getClass().equals(Poly.class)){
                g2d.setColor(Color.red);
                g2d.drawRect(focus.x - focus.w, focus.y - focus.h, 2*focus.w, 2*focus.h);
            } else {
                g2d.setColor(Color.red);
                g2d.drawRect(focus.x - 5, focus.y - 5, focus.w + 10, focus.h + 10);
            }
        }
    }
}

