package buttons;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import main.Casino;
import main.Profile;

public class ChooseProfile extends Button{

	public ChooseProfile(int x, int y) {
		super(x, y, 200, 100, Toolkit.getDefaultToolkit().getImage("./data/opening/choose.png"));
	}

	@SuppressWarnings("resource")
	@Override
	public void function(){
		Object[] objects;
		int  counter=0;
		String line, money,profile;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"./data/profiles/profiles.txt"));
			do {
				line=reader.readLine();
				if (line != null) {
					counter+=1;
				}
			} while (line != null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		objects=new Object[counter];
		counter=0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"./data/profiles/profiles.txt"));
			do {
				line=reader.readLine();
				if (line != null) {
					objects[counter]=line;
					counter+=1;
				}
			} while (line != null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		profile=(String)JOptionPane.showInputDialog(null,"Choose a profile","Profile",JOptionPane.PLAIN_MESSAGE,null,
				objects,objects[0]);
		if(profile!=null){
			try {
				BufferedReader reader = new BufferedReader(new FileReader(
						"./data/profiles/"+profile+".txt"));
				money=reader.readLine();
				Casino.profile=new Profile(Integer.parseInt(money),profile);
				Casino.screen=1;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
