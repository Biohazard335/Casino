package screens;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

import buttons.Button;
import buttons.ExitButton;
import buttons.MethodButton;
import main.Casino;
import main.Screen;

public class SlotMachines extends Screen {
	
	
	public String[] wheel ={"lemon","lemon","lemon","watermelon","watermelon", "cherry","cherry","seven",
			"bar","bar","bar","bar","bar"};
	
	public int f=0,s=0,t=0;
	
	public Image first,second,third;
	public boolean go=false;

	public SlotMachines() {
		super(Toolkit.getDefaultToolkit().getImage("./data/slots/slots.png"), 4, new Button[]{
			new MethodButton(500,450,200,100,Toolkit.getDefaultToolkit().getImage("./data/slots/bet.png"),"bet"),
			new MethodButton(980,200,67,174,Toolkit.getDefaultToolkit().getImage("./data/slots/arm.png"),"spin"),
			new ExitButton(1000, 50)});
	}

	@Override
	public void method(String s){
		if(s.equals("bet")){
			bet();
		}else if(s.equals("spin")){
			if(currentbet!=0 && go==false){
				buttons=new Button[]{
						new MethodButton(500,450,200,100,Toolkit.getDefaultToolkit().getImage("./data/slots/bet.png"),"bet"),
						new MethodButton(980,200,67,174,Toolkit.getDefaultToolkit().getImage("./data/slots/armdown.png"),"spin"),
						new ExitButton(1000, 50)};
				go=true;
				one();
			}
		}else if(s.equals("1")){
			one();
		}else if(s.equals("2")){
			two();
		}else if(s.equals("3")){
			three();
		}else if(s.equals("end")){
			spin();
		}
	}
	
	public void one(){
		f=(int)(Math.random()*10);
		first=Toolkit.getDefaultToolkit().getImage("./data/slots/"+wheel[f]+".png");
		Casino.pause("2",(int)(Math.random()*5)+7);
	}
	public void two(){
		s=(int)(Math.random()*10);
		second=Toolkit.getDefaultToolkit().getImage("./data/slots/"+wheel[s]+".png");
		Casino.pause("3",(int)(Math.random()*5)+7);
	}
	public void three(){
		t=(int)(Math.random()*10);
		third=Toolkit.getDefaultToolkit().getImage("./data/slots/"+wheel[t]+".png");
		Casino.pause("end",5);
	}
	
	public void spin(){
		if(wheel[f].equals(wheel[s]) && wheel[f].equals(wheel[t])){
			if(wheel[f].equals("lemon")){
				JOptionPane.showMessageDialog(null,
						"You won "+(50*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=51*currentbet;
			}else if(wheel[f].equals("watermelon")){
				JOptionPane.showMessageDialog(null,
						"You won "+(100*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=101*currentbet;
				
			}else if(wheel[f].equals("cherry")){
				JOptionPane.showMessageDialog(null,
						"You won "+(500*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=501*currentbet;
				
			}else if(wheel[f].equals("seven")){
				JOptionPane.showMessageDialog(null,
						"You won "+(1000*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=1001*currentbet;
			}
		}else if((wheel[f].equals(wheel[s]) || wheel[f].equals(wheel[t])) 
				&& !wheel[s].equals("bar")&& !wheel[t].equals("bar")&& !wheel[f].equals("seven")){
			if(wheel[f].equals("lemon")){
				JOptionPane.showMessageDialog(null,
						"You won "+(10*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=11*currentbet;
			}else if(wheel[f].equals("watermelon")){
				JOptionPane.showMessageDialog(null,
						"You won "+(20*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=21*currentbet;
				
			}else if(wheel[f].equals("cherry")){
				JOptionPane.showMessageDialog(null,
						"You won "+(30*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=31*currentbet;
			}
		}else if(wheel[t].equals(wheel[s])&& !wheel[f].equals("bar")&& !wheel[s].equals("bar")
				&& !wheel[t].equals("seven")){
			if(wheel[t].equals("lemon")){
				JOptionPane.showMessageDialog(null,
						"You won "+(10*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=11*currentbet;
			}else if(wheel[t].equals("watermelon")){
				JOptionPane.showMessageDialog(null,
						"You won "+(20*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=21*currentbet;
				
			}else if(wheel[t].equals("cherry")){
				JOptionPane.showMessageDialog(null,
						"You won "+(30*currentbet)+" dollars!", "Victory!",
						JOptionPane.PLAIN_MESSAGE, null);
				Casino.profile.money+=31*currentbet;
			}
		}else{
			JOptionPane.showMessageDialog(null,
					"You got nothin!", "Unfortunate",
					JOptionPane.PLAIN_MESSAGE, null);
		}
		currentbet=0;
		go=false;
		buttons =new Button[]{
				new MethodButton(500,450,200,100,Toolkit.getDefaultToolkit().getImage("./data/slots/bet.png"),"bet"),
				new MethodButton(980,200,67,174,Toolkit.getDefaultToolkit().getImage("./data/slots/arm.png"),"spin"),
				new ExitButton(1000, 50)};
	}

	
	public void bet(){
		currentbet = JOptionPane.showOptionDialog(null,
				"How much money would you like to bet?",
				"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,
				new Object[] { "1", "2", "5","10","Cancel"}, "");
		switch(currentbet){
			case 0:
				currentbet = 1;
				break;
			case 1:
				currentbet = 2;
				break;
			case 2:
				currentbet = 5;
				break;
			case 3:
				currentbet = 10;
				break;
			case 4:
				currentbet = -1;
				break;
		}
		if(currentbet!=-1){
			Casino.profile.money-=currentbet;
			first=Toolkit.getDefaultToolkit().getImage("./data/slots/.png");
			second=Toolkit.getDefaultToolkit().getImage("./data/slots/.png");
			third=Toolkit.getDefaultToolkit().getImage("./data/slots/.png");
		}else{
			currentbet=0;
		}
	}
}