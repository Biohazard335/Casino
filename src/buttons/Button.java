package buttons;
import java.awt.Image;

import main.Casino;

public class Button {
	public int x = 0, y = 0, width = 0, height = 0;
	public Image image;

	public Button(int x, int y, int width, int height, Image image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}

	public void function() {
		Casino.screen+=1;
	}
	
	public void explain(){
		
	}

}
