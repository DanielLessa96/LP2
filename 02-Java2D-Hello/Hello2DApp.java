import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        frame.setVisible(true);
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(350,350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        getContentPane().setBackground(Color.white);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.green);
        int w = getWidth();
        int h = getHeight();
        //g2d.drawLine(w/2,0, w/2,h);
        //g2d.drawLine(0,h/2, w,h/2);
        int x0 = 100;
        int y0 = 100;
        int xf = x0 + w - 1;
        int yf = y0 + h - 1;
        int pmx = (x0 + xf) / 2;
        int pmy = (y0 + yf) / 2;
        g2d.fillRect(x0, y0, w, h);
        int xPoint[] = {pmx,xf - 50, pmx,x0 + 50};
        int yPoint[] = {y0 + 50,pmy, yf - 50,pmy};
        int npoints = 4;
        g2d.setPaint(Color.yellow);
        g2d.fillPolygon(xPoint, yPoint, npoints);
        g2d.setPaint(Color.blue);
        g2d.fillOval(3 * (pmx + 33) / 4,(3 * pmy) / 4, w/4, w/4);
    }
}
