package buttons;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import main.Casino;
import main.Profile;

public class CreateProfile extends Button {
	public CreateProfile(int x, int y) {
		super(x, y, 200, 100, Toolkit.getDefaultToolkit().getImage(
				"./data/opening/create.png"));
	}

	@SuppressWarnings("resource")
	@Override
	public void function() {
		String name = "a", line = "a", money1 = "a";
		int money = 0;
		name = JOptionPane.showInputDialog(null,
				"Please enter desired Profile name.", "Create Profile",
				JOptionPane.PLAIN_MESSAGE);
		if (name != null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(
						"./data/profiles/profiles.txt"));
				do {
					line = reader.readLine();
					if (line != null && line.equalsIgnoreCase(name)) {
						line = "repeat";
					}

				} while (line != null && line != "repeat");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line == null) {
				money = JOptionPane.showOptionDialog(null,
						"How much money would you like to start with?",
						"Deposit", 0, JOptionPane.PLAIN_MESSAGE, null,
						new Object[] { "500", "1000", "10000" }, "");
				if (money == 0) {
					money1 = "500";
				} else if (money == 1) {
					money1 = "1000";
				} else if (money == 2) {
					money1 = "10000";
				}
				if (money != -1) {
					try {
						FileOutputStream writer1 = new FileOutputStream(
								"./data/profiles/" + name + ".txt");
						writer1.write((new String()).getBytes());
						writer1.close(); 
						BufferedWriter writer = new BufferedWriter(
								new FileWriter("./data/profiles/" + name
										+ ".txt", true));
						writer.write((new String(money1)));
						writer.flush();
						writer.close();
						writer = new BufferedWriter(new FileWriter(
								"./data/profiles/profiles.txt", true));
						writer.newLine();
						writer.write((new String(name)));
						writer.flush();
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Casino.screen = 1;
					Casino.profile = new Profile(Integer.parseInt(money1), name);
				} else {
					JOptionPane.showMessageDialog(null,
							"A money amount must be selected!", "Error",
							JOptionPane.PLAIN_MESSAGE, null);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Sorry, that name has already been taken", "Error",
						JOptionPane.PLAIN_MESSAGE, null);
			}
			Casino.profile = new Profile(Integer.parseInt(money1), name);
		}
	}
}