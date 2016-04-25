package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import buttons.Button;

public class Mouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(Button b : Casino.screens[Casino.screen].buttons){
			if(e.getX()>b.x && e.getX()<b.x+b.width && e.getY()>b.y+25 && e.getY()<b.y+b.height){
				if(e.getButton()==MouseEvent.BUTTON1){
					b.function();
				}else{
					b.explain();
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
