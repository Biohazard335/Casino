package buttons;

import java.awt.Image;

import main.Casino;

public class MethodButton extends Button{
	public String string;

	public MethodButton(int x, int y, int width, int height, Image image,String string) {
		super(x, y, width, height, image);
		this.string=string;
	}
	
	@Override
	public void function(){
		Casino.screens[Casino.screen].method(string);
	}

	@Override
	public void explain(){
		Casino.screens[Casino.screen].explain(string);
	}

}
