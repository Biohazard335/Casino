package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import screens.Craps;
import screens.SlotMachines;
import screens.TexasHoldem;
import buttons.Button;


public class Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String money="0", bet="0";
	public void paint(Graphics g){
		Screen screen = Casino.screens[Casino.screen];
		g.setColor(Color.WHITE);
		g.fillRect(0,0,1300,710);
		g.drawImage(screen.image, 0, 0, this);
		for(Button b:screen.buttons){
			g.drawImage(b.image, b.x, b.y, this);
		}
		if(Casino.screen==2){
			g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/back.png"),
					600,420,143,200,this);
			for(Card c:screen.hand){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+c.toString()+".png"),
								100+(screen.hand.indexOf(c)*50),420,143,200,this);	
			}
			for(Card c:screen.dealer){
				if(Casino.screens[2].end){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+c.toString()+".png"),
								840+(screen.dealer.indexOf(c)*50),420,143,200,this);	
				}else if(screen.dealer.indexOf(c)==0){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/back.png"),
							840,420,143,200,this);
				}else{
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+c.toString()+".png"),
							840+(screen.dealer.indexOf(c)*50),420,143,200,this);	
				}
			}
		}if(Casino.screen==3){
			Craps craps = (Craps) Casino.screens[3];
			if(craps.die1!=0){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/dice/"+craps.die1+".png"),
						525+(craps.die1x),400+craps.die1y,50,50,this);
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/dice/"+craps.die2+".png"),
						675+(craps.die2x),400+(craps.die2y),50,50,this);
			}
			if(craps.point!=0){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/point.png"),
						550,600,100,50,this);
				if(craps.point>=10){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/green 1.png"),
							650,600,50,50,this);
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/green "+(craps.point-10)+".png"),
							700,600,50,50,this);
				}else{
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/green "+craps.point+".png"),
							650,600,50,50,this);
				}
			}
			if(craps.currentbet!=0){
				if(craps.pass){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/craps/chip.png"),
						440,610,50,50,this);
				}
				else{
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/craps/chip.png"),
							440,540,50,50,this);
				}
			}
		}else if(Casino.screen==4){
			SlotMachines s = (SlotMachines) Casino.screens[Casino.screen];
			g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/slots/paytable.png"),
				50,370,250,334,this);
			g.drawImage(s.first,280,240,150,100,this);
			g.drawImage(s.second,520,240,150,100,this);
			g.drawImage(s.third,770,240,150,100,this);
		}if(Casino.screen==5){
			TexasHoldem th = (TexasHoldem) Casino.screens[5];
			if(th.playermoving<5 && th.pot!=0 && th.buttons.length<3 &&th.turn){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/texasholdem/turn.png"),
					(250*th.playermoving)-15,200,220,220,this);
			}
			if(th.round ==0 && th.pot!=0){
				for(int i=0;i<5;i++){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/back.png"),
							(i*50),500,143,200,this);
				}
			}else if(th.round==1){
				for(int i=0;i<3;i++){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+th.house.get(i).toString()+".png"),
							(i*50),500,143,200,this);
				}
				for(int i=0;i<2;i++){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/back.png"),
							((i+3)*50),500,143,200,this);
				}
			}else if(th.round==2){
				for(int i=0;i<4;i++){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+th.house.get(i).toString()+".png"),
							(i*50),500,143,200,this);
				}
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/back.png"),
							(4*50),500,143,200,this);
			}else if(th.round==3){
				for(Card c:th.house){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+c.toString()+".png"),
							(th.house.indexOf(c)*50),500,143,200,this);
				}
			}
			if(th.round==4){
				for(Card c:th.house){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+c.toString()+".png"),
							(th.house.indexOf(c)*50),500,143,200,this);
				}
				for(Player p:th.players){
					if(p.fold==false){
						for(Card c:p.hand){
							g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+c.toString()+".png"),
									250*th.players.indexOf(p)+(p.hand.indexOf(c)*50),220,143,200,this);
						}
					}else{
						g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/texasholdem/pfold.png"),
								250*th.players.indexOf(p),220,215,200,this);
					}
				}
			}else {
				for(Player p:th.players){
					if(p.fold==false){
						for(Card c:p.hand){
							g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/back.png"),
									250*th.players.indexOf(p)+(p.hand.indexOf(c)*50),220,143,200,this);
						}
						g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/texasholdem/"+p.move+".png"),
								250*th.players.indexOf(p),420,100,50,this);
					}else{
						g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/texasholdem/pfold.png"),
								250*th.players.indexOf(p),220,215,200,this);
					}
				}
			}
			
			/*if(th.see){
				for(Card c:th.house){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+c.toString()+".png"),
							(th.house.indexOf(c)*50),500,143,200,this);
				}
				for(Player p:th.players){
					for(Card c:p.hand){
						g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+c.toString()+".png"),
								250*th.players.indexOf(p)+(p.hand.indexOf(c)*50),220,143,200,this);
					}
				}
			}*/
			for(Player p:th.players){
				for(int i=0;i<(""+p.name).length();i++){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/letters/"+(""+p.name).charAt(i)+".png"),
							250*th.players.indexOf(p)+(20*i),180,30,30,this);
				}
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/texasholdem/"+p.move+".png"),
						250*th.players.indexOf(p),420,100,50,this);
				if(p.move.equals("called")||p.move.equals("raiseto")){
					for(int i=0;i<(""+p.round).length();i++){
						g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/green "+(""+p.round).charAt(i)+".png"),
								250*th.players.indexOf(p)+(40*i)+100,420,50,50,this);
					}
				}
			}
			g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/texasholdem/"+th.move+".png"),
					700,550,100,50,this);
			if(th.move.equals("called")||th.move.equals("raiseto")){
				for(int i=0;i<(""+th.pround).length();i++){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/green "+(""+th.pround).charAt(i)+".png"),
							700+(40*i)+100,550,50,50,this);
				}
			}
			for(Card c:th.hand){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/cards/"+c.toString()+".png"),
						500+(th.hand.indexOf(c)*50),500,143,200,this);
			}
			g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/pot.png"),500,100,100,50,this);
			for(int i=0;i<(""+th.pot).length();i++){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/green "+(""+th.pot).charAt(i)+".png"),
						600+(40*i),100,50,50,this);
			}
		}
		if(Casino.profile!=null){
			if(Casino.screens[Casino.screen].currentbet!=0){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/bet.png"),500,100,100,50,this);
				bet=""+Casino.screens[Casino.screen].currentbet;
				for(int i=0;i<bet.length();i++){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/green "+bet.charAt(i)+".png"),600+(40*i),100,50,50,this);
				}
			}
			g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/money.png"),500,40,100,50,this);
			money=""+Casino.profile.money;
			for(int i=0;i<money.length();i++){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/numbers/green "+money.charAt(i)+".png"),600+(40*i),40,50,50,this);
			}
		}
	}
}