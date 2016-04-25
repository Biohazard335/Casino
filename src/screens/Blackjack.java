package screens;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import buttons.Button;
import buttons.ExitButton;
import buttons.MethodButton;
import main.Card;
import main.Casino;
import main.Screen;

public class Blackjack extends Screen {
	public static int currentcard = 0, playerhand = 0,
			dealerhand = 0, ace = 0, dace = 0;
	
	public boolean doubling=false;

	public Blackjack() {
		super(Toolkit.getDefaultToolkit().getImage("./data/blackjack/blackjack.png"), 2, new Button[] {
			new MethodButton(600,200,200,100,Toolkit.getDefaultToolkit().getImage("./data/blackjack/bet.png"),"bet"),
			new ExitButton(1000, 50) });
		currentbet = 0;
		currentcard = 0;
		playerhand = 0;
		dealerhand = 0;
		ace = 0;
		dace = 0;
	}

	@Override
	public void method(String s) {
		if (s.equals("sort")) {
			sort();
		} else if (s.equals("hit")) {
			hit();
		} else if (s.equals("doubledown")) {
			doubledown();
		} else if(s.equals("bet")){
			bet();
		} else if(s.equals("stand")){
			dealerend();
		} else if(s.equals("surrender")){
			surrender();
		} else if(s.equals("dealerhit")){
			dealerhit();
		}
	}

	public void sort() {
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int o = 1; o < 14; o++) {
				do {
					index = (int) (Math.random() * 52);
					if (deck[index] == null) {
						deck[index] = new Card(i, o);
						break;
					}
				} while (true);
			}
		}

	}

	@Override
	public void change(int i) {
		if (i == 1) {
			buttons = new Button[] { 
					new MethodButton(130,200,100,100,Toolkit.getDefaultToolkit().getImage("./data/blackjack/hit.png"),"hit"),
					new MethodButton(300,200,100,100,Toolkit.getDefaultToolkit().getImage("./data/blackjack/stand.png"),"stand"),
					new MethodButton(470,200,100,100,Toolkit.getDefaultToolkit().getImage("./data/blackjack/double.png"),"doubledown"),
					new MethodButton(600,200,200,100,Toolkit.getDefaultToolkit().getImage("./data/blackjack/surrender.png"),"surrender")
			};
		} else if (i == 0) {
			buttons = new Button[] {
					new MethodButton(550,200,200,100,Toolkit.getDefaultToolkit().getImage("./data/blackjack/bet.png"),"bet"),
					new ExitButton(1000, 50)		
			};
		}
	}

	@Override
	public void start() {
		sort();
		dealerhit();
		dealerhit();
		hit();
		hit();
	}

	public void hit() {
		hand.add(deck[currentcard]);
		if (deck[currentcard].value == 13 || deck[currentcard].value == 11
				|| deck[currentcard].value == 12) {
			playerhand += 10;
		} else if (deck[currentcard].value == 1) {
			playerhand += 11;
			ace += 1;
		} else {
			playerhand += deck[currentcard].value;
		}
		if (playerhand > 21) {
			while (true) {
				if (ace != 0 && playerhand > 21) {
					playerhand -= 10;
					ace -= 1;
				} else
					break;
			}
			if (playerhand > 21 && doubling==false) {
				end();
			}
		}
		currentcard += 1;
	}

	public void dealerhit() {
		dealer.add(deck[currentcard]);
		if (deck[currentcard].value == 13 || deck[currentcard].value == 11
				|| deck[currentcard].value == 12) {
			dealerhand += 10;
		} else if (deck[currentcard].value == 1) {
			dealerhand += 11;
			dace += 1;
		} else {
			dealerhand += deck[currentcard].value;
		}
		if (dealerhand > 21) {
			while (true) {
				if (dace != 0 && dealerhand > 21) {
					dealerhand -= 10;
					dace -= 1;
				} else
					break;
			}
		}
		currentcard += 1;
		if(end){
			dealerend();
		}
	}

	public void doubledown() {
		doubling=true;
		hit();
		doubling=false;
		Casino.profile.money -= currentbet;
		currentbet += currentbet;
		dealerend();
	}
	
	public void surrender(){
		Casino.profile.money+=currentbet/2;
		reset();
	}
	
	public void bet(){
		currentbet = JOptionPane.showOptionDialog(null,
				"How much money would you like to bet?",
				"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,
				new Object[] { "20", "50", "100","Cancel"}, "");
		
		switch(currentbet){
			case 0:
				currentbet = 20;
				break;
			case 1:
				currentbet = 50;
				break;
			case 2:
				currentbet = 100;
				break;
		}
		if(currentbet!=-1 && currentbet!=3){
			change(1);
			Casino.profile.money-=currentbet;
			start();
		}else{
			currentbet=0;
		}
	}

	@Override
	public void reset() {
		Casino.screens[2] = new Blackjack();
	}
	
	public void dealerend(){
		end=true;
		if(dealerhand<=17){
			Casino.pause("dealerhit",5);
		}else{
			Casino.frame.repaint();
			end();
		}
	}

	@Override
	public void end() {
		if (playerhand <= 21) {
			if(dealerhand>21){
				JOptionPane.showMessageDialog(null,
						"The dealer busts!\nYou won "+(2*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money += (2 * currentbet);
			}else if (dealerhand > playerhand) {
				JOptionPane.showMessageDialog(null,
						"The dealer had a better hand than you.",
						"Dealer wins!", JOptionPane.PLAIN_MESSAGE, null);
			} else if (dealerhand == playerhand) {
				JOptionPane.showMessageDialog(null,
						"The hands tied, so the dealer wins.", "Dealer wins!",
						JOptionPane.PLAIN_MESSAGE, null);
			} else if (playerhand > dealerhand && playerhand <= 21) {
				JOptionPane.showMessageDialog(null,
						"You had the better hand!\nYou won " + (2 * currentbet)
								+ " dollars!", "You win!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money += 2 * currentbet;
			}
		}else{
			JOptionPane.showMessageDialog(null,
					"You're card values have exeeded 21!\nYou lose!",
					"Bust!", JOptionPane.PLAIN_MESSAGE, null);
		}
		reset();
	}

	@Override
	public void explain(String s){
		String string="";
		if(s.equals("hit")){
			string="";
		}else if(s.equals("doubledown")){
			string="";
		}else if(s.equals("end"/*stand*/)){
			string="";
		}else if(s.equals("surrender")){
			string="";
		}
		JOptionPane.showMessageDialog(null,
				string, "Information",
				JOptionPane.PLAIN_MESSAGE, null);
	}
	

}
