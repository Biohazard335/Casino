package buttons;

import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import main.Casino;

public class ExitButton extends Button {

	public ExitButton(int x, int y) {
		super(x, y, 200, 100, Toolkit.getDefaultToolkit().getImage(
				"./data/exit.png"));
	}

	@Override
	public void function() {
		Casino.screens[Casino.screen].reset();
		Casino.screen = 1;
		try {
			FileOutputStream writer1 = new FileOutputStream("./data/profiles/"
					+ Casino.profile.name + ".txt");
			writer1.write((new String()).getBytes());
			writer1.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"./data/profiles/" + Casino.profile.name + ".txt", true));
			writer.write("" + Casino.profile.money);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
