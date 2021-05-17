package figures;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Line2D;

import interfaces.IVisible;

import java.awt.Color;

public abstract class Figure implements IVisible {
    public int x, y;
    public int w, h;
    public Color contorno, fundo;
    
    
    public Figure (int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
    }
    
     public void drag (int dx, int dy) {
        this.x = dx - (this.w)/2;
        this.y = dy - (this.h)/2;
    }

    public boolean clicked (int x, int y) {
       if (this.getClass().equals(Poly.class)){
            return ((this.x - this.w) <= x && (this.x + this.w) >= x  && (this.y - this.h) <= y && (this.y + this.h) >= y);
        }else {
            return (this.x <= x && (this.x + this.w) >= x && this.y <= y && (this.y + this.h) >= y );       				
        }
    }
    

    public void paintfocus (Graphics g) {
        g.setColor(Color.red);
        if (this.getClass().equals(Poly.class)){
            g.setColor(Color.red);
            g.drawRect(this.x - this.w, this.y - this.h, 2*this.w, 2*this.h);
        } else {
            g.setColor(Color.red);
            g.drawRect(this.x - 5, this.y - 5, this.w + 10, this.h + 10);
        }
        
    }
}