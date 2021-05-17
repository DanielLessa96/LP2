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
    private static final long serialVersionUID = 1L;
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();
    Figure focus = null;
    Color colors[] = {Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow};


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

                    if (evt.getKeyChar() == 'r') {
                        figs.add(new Rect(x,y, w,h,colors[rand.nextInt(13)],colors[rand.nextInt(13)]));

                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(x,y, w,h,colors[rand.nextInt(13)],colors[rand.nextInt(13)]));

                    } else if (evt.getKeyChar() == 'l') {
                        figs.add(new Line(x,y, w,h,colors[rand.nextInt(13)],colors[rand.nextInt(13)]));

                    } else if (evt.getKeyChar() == 'p') {
                            figs.add(new Poly(x,y, w,h,colors[rand.nextInt(13)],colors[rand.nextInt(13)]));

                    } else if(evt.getKeyChar() == '+'){
                        if(focus.w < 350 & focus.h < 350){
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

                    } else if(evt.getKeyCode() == KeyEvent.VK_DELETE){
                        figs.remove(focus);
                        focus = null;
                    } 
                    repaint();
                }
            }
        );

        this.addMouseMotionListener(
        	new MouseMotionAdapter() {
        		public void mouseDragged(MouseEvent evt) {
                    if (focus != null) {
                        focus.drag(evt.getX(), evt.getY());
                        repaint();
                    }
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
                                focus = fig;
                                setCursor(new Cursor(Cursor.HAND_CURSOR));
                                repaint();
                            }
                        }else if(fig.x <= evt.getX() && fig.y <= evt.getY() && (fig.x + fig.w)>= evt.getX() && (fig.y + fig.h) >= evt.getY()) {       				
                            focus = fig;
                            setCursor(new Cursor(Cursor.HAND_CURSOR));
                            repaint();
                        } else {
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            repaint();
                        }
                    }
                    if (focus!= null) {
                        figs.remove(focus);
                        figs.add(focus);
                    }
        		}
        	}
        );
        this.setTitle("Lista de Figuras");
        this.setSize(1024, 720);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        if (focus != null){
            focus.paintfocus(g);
        }
    }
}

