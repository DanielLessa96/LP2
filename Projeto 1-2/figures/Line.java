package figures;

import java.awt.*;
import java.awt.Color;
import java.awt.geom.Line2D;

public class Line extends Figure {
    public Line (int x, int y, int w, int h, Color contorno, Color fundo) {
        super(x,y, w,h, contorno,fundo);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.contorno);
        g2d.drawLine(this.x, this.y, this.w + this.x, this.h + this.y);
    }
}
