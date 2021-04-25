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
            } 
            repaint();
        }
    }
);
