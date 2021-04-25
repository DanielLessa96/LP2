this.addMouseListener(
 new MouseAdapter() {
  public void mousePressed(MouseEvent evt) {
    focus = null;
    for(Figure fig: figs) {
      if (fig.getClass().equals(Poly.class)){
        if((fig.x - fig.w) <= evt.getX() &  (fig.y - fig.h) <= evt.getY() & (fig.x + fig.w)>= evt.getX() & (fig.y + fig.h) >= evt.getY()){
        figs.remove(fig);
        focus = fig;
        figs.add(focus);
        repaint();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
      }
      if(fig.x <= evt.getX() & fig.y <= evt.getY() & (fig.x + fig.w)>= evt.getX() & (fig.y + fig.h) >= evt.getY()) {       				
        figs.remove(fig);
        focus = fig;
        figs.add(focus);
        repaint();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
       } else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
  }
);
