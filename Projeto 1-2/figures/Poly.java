package figures;

import java.awt.*;
import java.awt.Polygon;

public class Poly extends Figure {
    int[] xPoint, yPoint;
    int npoints = 4;
    public Poly (int x, int y, int w, int h, Color contorno, Color fundo) {
        super(x,y, w,h, contorno,fundo);
    }

    public void print () {
        for(int i = 0; i < npoints ; i++) {
            System.out.format("Poligono de (%d) lados nos pontos (%d,%d).\n",
            this.npoints, this.xPoint[i], this.yPoint[i]);
          }  
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.xPoint = new int[]{x, x + w , x, x - w};
        this.yPoint = new int[]{y - h, y , y + h, y };
        g2d.setColor(this.fundo);
        g2d.fill(new Polygon(this.xPoint, this.yPoint, this.npoints));
        g2d.setColor(this.contorno);
        g2d.draw(new Polygon(this.xPoint, this.yPoint, this.npoints));
    }
}