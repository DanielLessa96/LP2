this.addKeyListener (
    new KeyAdapter() {
        public void keyPressed (KeyEvent evt) {
            int x = rand.nextInt(350);
            int y = rand.nextInt(350);
            int w = rand.nextInt(50);
            int h = rand.nextInt(50);
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            int r1 = rand.nextInt(255;
            int g1 = rand.nextInt(255);
            int b1 = rand.nextInt(255);
            if (evt.getKeyChar() == 'r') {
                figs.add(new Rect(x,y, w,h,new Color(r,g,b), new Color(r1,g1,b1)));
            } else if (evt.getKeyChar() == 'e') {
                        igs.add(new Ellipse(x,y, w,h,new Color(r,g,b), new Color(r1,g1,b1)));
            } else if(evt.getKeyCode() == KeyEvent.VK_DELETE){
                    figs.remove(focus);
            } 
            repaint();
        }
    }
);