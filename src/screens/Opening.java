package screens;

import java.awt.Toolkit;

import buttons.Button;
import buttons.ChooseProfile;
import buttons.CreateProfile;
import buttons.MethodButton;
import main.*;

public class Opening extends Screen{

	public Opening() {
		super(Toolkit.getDefaultToolkit().getImage("./data/opening/opening.png"), 0,
				new Button[] {new ChooseProfile(100,500),new CreateProfile(1000,500),
				new MethodButton(1000,50,200,100,Toolkit.getDefaultToolkit().getImage("./data/quit.png"),"ante")
				}
		);
	}

	@Override
	public void method(String s){
		Casino.exit();
	}
}
