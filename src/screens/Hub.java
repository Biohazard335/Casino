package screens;

import java.awt.Toolkit;

import buttons.Button;
import buttons.ChangeScreen;
import buttons.MethodButton;
import main.*;

public class Hub extends Screen{

	public Hub() {
		super(Toolkit.getDefaultToolkit().getImage("./data/hub/hub.png"), 1,
				new Button[] {new ChangeScreen(100,300,Toolkit.getDefaultToolkit().getImage("./data/hub/blackjack.png"),2),
					new ChangeScreen(1000,300,Toolkit.getDefaultToolkit().getImage("./data/hub/craps.png"),3),
					new ChangeScreen(550,500,Toolkit.getDefaultToolkit().getImage("./data/hub/slotmachines.png"),4),
					new ChangeScreen(550,300,Toolkit.getDefaultToolkit().getImage("./data/hub/texasholdem.png"),5),
					new MethodButton(1000,50,200,100,Toolkit.getDefaultToolkit().getImage("./data/exit.png"),"")
				}
		);
	}
	
	@Override
	public void method(String s){
		Casino.screen=0;
		Casino.profile=null;
	}
}