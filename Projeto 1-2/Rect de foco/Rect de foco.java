
public void paint (Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    super.paint(g);
    for (Figure fig: this.figs) {
        fig.paint(g);
    }
    if (focus != null){
        g2d.setColor(Color.red);
        g2d.drawRect(focus.x - 5, focus.y - 5, focus.w + 10, focus.h + 10);
    }
    focus.paint(g);
    
}