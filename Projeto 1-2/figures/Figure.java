package figures;

import java.awt.Graphics;
import java.awt.Color;

public abstract class Figure {
    public int x, y;
    public int w, h;
    public Color contorno;
    Color fundo;
    
    public Figure (int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
    }
    
     public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public abstract void paint (Graphics g);
}