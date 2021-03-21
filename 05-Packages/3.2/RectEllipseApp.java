import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import figuras.*;

class RectEllipseApp {
    public static void main (String[] args) {
        RectEllipseFrame frame = new RectEllipseFrame();
        frame.setVisible(true);
    }
}

class RectEllipseFrame extends JFrame {
    Rect r1, r2, r3;
    Ellipse e1, e2, e3;

    RectEllipseFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Rect + Ellipse");
        this.setSize(500, 500);
        this.r1 = new Rect(50,50, 100,30, new Color(255, 255, 0), new Color(255, 0, 0));
        this.r2 = new Rect(200,50, 50,30, new Color(0, 255, 0), new Color(0, 0, 255));
        this.r3 = new Rect(300,50, 60,40, new Color(0, 0, 0), new Color(255, 0, 255));
        this.e1 = new Ellipse(50,100, 100,30, new Color(102, 255, 51), new Color(255, 102, 0));
        this.e2 = new Ellipse(200,100, 30,100, new Color(0, 255, 255), new Color(255, 0, 102));
        this.e3 = new Ellipse(300,100, 100,60, new Color(204, 0, 0), new Color(204, 153, 0));
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
    }
}
