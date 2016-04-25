package buttons;

import java.awt.Image;

import main.Casino;

public class ChangeScreen extends Button{
	
	public int number;

	public ChangeScreen(int x, int y, Image image, int number) {
		super(x, y, 200, 100, image);
		this.number =number;
	}
	
	@Override
	public void function(){
		Casino.screen=this.number;
	}

}
