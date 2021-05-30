import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import figures.*;
import ivisible.IVisible;
import botao.Botao;


class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<Botao> buts = new ArrayList<Botao>();
    Random rand = new Random();
    Figure focus = null;
    Botao focus_button = null;
    Color colors[] = {Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow};


    ListFrame () {

        buts.add(new Botao(0,new Rect(0,0,0,0,Color.BLACK, Color.BLACK)));
        buts.add(new Botao(1,new Ellipse(0,0,0,0,Color.BLACK, Color.BLACK)));
        buts.add(new Botao(2,new Poly(0,0,0,0,Color.BLACK, Color.BLACK)));
        buts.add(new Botao(3, new Line(0, 0, 0, 0, Color.BLACK,Color.WHITE)));

        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<Figure>) o.readObject();
            o.close();
        } catch (Exception x) {
            System.out.println("Erro!, não foi possivel abrir o arquivo" +x);
        }
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    try {
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    } catch (Exception x) {
                        System.out.println("Erro!, não foi possivel criar o arquivo" +x);
                    }
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = rand.nextInt(499);
                    int y = rand.nextInt(499);
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

                    } else if(evt.getKeyChar() == 'c') {    
                        if(focus!=null){
                            focus.fundo = JColorChooser.showDialog(null, "Escolha uma cor de fundo para a figura", colors[0]);
                            focus.contorno = JColorChooser.showDialog(null, "Escolha uma cor de contorno para a figura ", colors[0]);
                            repaint();
                        }
                
                    } else if(evt.getKeyChar() == '+'){
                        if(focus.w < 500 && focus.h < 500){
                            focus.h = focus.h + 10;
                            focus.w = focus.w + 10;
                        }
                    } else if(evt.getKeyChar() == '-'){
                        if(focus.w > 0 && focus.h > 0) {
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
                    focus_button = null;
                    for (Botao but: buts) {
                        if (but.clicked(evt.getX(), evt.getY())){
                            focus_button = but;
                        }
                    }
                    if (focus_button!= null) {
                        buts.remove(focus_button);
                        buts.add(focus_button);
                    }
                    if (focus_button != null && !(evt.getX() >= 0 && evt.getX() <= 80 && evt.getY() >= 0 && evt.getY() <= 200)){
                        if(focus_button.idx==0)
							figs.add(new Rect(evt.getX(),evt.getY(),25,25,colors[rand.nextInt(13)],colors[rand.nextInt(13)]));      
						else if(focus_button.idx==1)
							figs.add(new Ellipse(evt.getX(),evt.getY(),25,25,colors[rand.nextInt(13)],colors[rand.nextInt(13)]));
						else if(focus_button.idx==2)
                            figs.add(new Poly(evt.getX(),evt.getY(),25,25,colors[rand.nextInt(13)],colors[rand.nextInt(13)]));
                        else if(focus_button.idx==3)
                            figs.add(new Line(evt.getX(),evt.getY(),25,25,colors[rand.nextInt(13)],colors[rand.nextInt(13)]));
                        repaint();

                    }
        			focus = null;
        			for(Figure fig: figs) {
                        if (fig.clicked(evt.getX(), evt.getY())){
                            focus = fig;
                            setCursor(new Cursor(Cursor.HAND_CURSOR));
                            
                        } else {
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        }
                    }
                    if (focus!= null) {
                        figs.remove(focus);
                        figs.add(focus);
                    }
                    repaint();
        		}
        	}
        );
        this.setTitle("Lista de Figuras");
        this.setSize(500, 500);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g, fig == focus);
        }
        for (Botao but: this.buts) {
            but.paint(g, but == focus_button);
        }
    }
}

