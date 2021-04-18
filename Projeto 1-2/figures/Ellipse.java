package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.Color;

public class Ellipse extends Figure {
    public Ellipse (int x, int y, int w, int h, Color contorno, Color fundo) {
        super(x,y, w,h, contorno,fundo);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.fundo);
        g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w, this.h ));
        g2d.setColor(this.contorno);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}


