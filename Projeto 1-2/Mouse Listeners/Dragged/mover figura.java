

this.addMouseMotionListener(
        	new MouseMotionAdapter() {
        		public void mouseDragged(MouseEvent evt) {
        			focus.x = evt.getX() - (focus.w/2);
        			focus.y = evt.getY() - (focus.h/2);
                    repaint();
                }
        	}
        );
        
       