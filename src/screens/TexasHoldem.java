package screens;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import buttons.Button;
import buttons.ExitButton;
import buttons.MethodButton;
import main.Card;
import main.Casino;
import main.Player;
import main.Screen;

public class TexasHoldem extends Screen{
	
	public int currentcard =0;
	
	public ArrayList<Player> players = new ArrayList<Player>();

	public ArrayList<Card> house = new ArrayList<Card>(5);
	
	public ArrayList<Card> besthand = new ArrayList<Card>();
	
	public ArrayList<String> names =new ArrayList<String>(){private static final long serialVersionUID = 1L;{
		 add("Steven"); add("Barry");add("James");add("Stephen");add("John");add("Other Barry");add("Sterling");
		 add("Bruce");add("Tony");add("Chris");add("Tom");add("Mark");add("Sven");add("Todd");add("Drew");
		 add("Gary");add("Jerry");add("Larry");add("Ron");add("Bill");add("Achmed");add("Walter");add("Jeff");
		 add("Dave");add("Jim");
		 add("Crystal");add("Carol");add("Cheryl");add("Sarah");add("Miranda");add("Lana");add("Amy");add("Jessica");
		 add("Lauren");add("Beatrice");add("Annie");add("Mallory");
	}};
	
	public ArrayList<String> names1=new ArrayList<String>(){private static final long serialVersionUID = 1L;{
		 add("Steven"); add("Barry");add("James");add("Stephen");add("John");add("Other Barry");add("Sterling");
		 add("Bruce");add("Tony");add("Chris");add("Tom");add("Mark");add("Sven");add("Todd");add("Drew");
		 add("Gary");add("Jerry");add("Larry");add("Ron");add("Bill");add("Achmed");add("Walter");add("Jeff");
		 add("Dave");add("Jim");
		 add("Crystal");add("Carol");add("Cheryl");add("Sarah");add("Miranda");add("Lana");add("Amy");add("Jessica");
		 add("Lauren");add("Beatrice");add("Annie");add("Mallory");
	}};
	
	public int pot=0,playervalue=0, winner=0, winindex, bigblind=(int)(Math.random()*6),round=0, call=20,pround=0, ending=0;
	public boolean see=false;
	
	public int betindex=-1;
	
	public boolean raise=false,turn=true;
	
	public String move="";

	public TexasHoldem() {
		super(Toolkit.getDefaultToolkit().getImage("./data/texasholdem/texas.png"), 5, new Button[]{
			new MethodButton(600,200,200,100,Toolkit.getDefaultToolkit().getImage("./data/texasholdem/ante.png"),"ante"),
			new ExitButton(1000, 50)});
	}
	
	public void check(){
		sort();
		//System.out.println(handvalue(
				//new Card[]{deck[currentcard],deck[currentcard+1],deck[currentcard+2],deck[currentcard+3],deck[currentcard+4]}));
		System.out.println("\n"+handvalue(
				new Card[]{new Card(0,1),new Card(0,2),new Card(0,4),new Card(0,3),new Card(0,5)}));
	}
	
	@Override
	public void method(String s){
		if(s.equals("sort")){
			sort();
		}else if(s.equals("ante")){
			change(5);
			turn=true;
			start();
		}else if(s.equals("display")){
			check();
		}else if(s.equals("fold")){
			fold();
		}else if(s.equals("call")){
			call();
		}else if(s.equals("raise")){
			if(!raise){
				raise();
			}else{
				JOptionPane.showMessageDialog(null,
						"You have already raised once this betting round,\nyou may not raise again.", "Sorry!",
						JOptionPane.PLAIN_MESSAGE, null);	
			}
		}else if(s.equals("round")){
			round();
		}else if(s.equals("move")){
			move(players.get(playermoving));
		}else if(s.equals("check")){
			move="checked";
			moveplayers();
		}else if(s.equals("see")){
			see=!see;
		}else if(s.equals("end")){
			end();
		}
		
	}
	
	
	
	@Override
	public void change(int i){
		if(i==0){
			buttons=new Button[]{
					new MethodButton(600,200,200,100,Toolkit.getDefaultToolkit().getImage("./data/texasholdem/ante.png"),"ante"),
					new ExitButton(1000, 50),
			};
		}else if(i==1){
			buttons=new Button[]{
					new MethodButton(700,600,200,100,Toolkit.getDefaultToolkit().getImage("./data/texasholdem/call.png"),"call"),
					new MethodButton(900,600,200,100,Toolkit.getDefaultToolkit().getImage("./data/texasholdem/raise.png"),"raise"),
					new MethodButton(1100,600,200,100,Toolkit.getDefaultToolkit().getImage("./data/texasholdem/fold.png"),"fold"),
			};
		}else if(i==2){
			buttons=new Button[]{
					new MethodButton(700,600,200,100,Toolkit.getDefaultToolkit().getImage("./data/texasholdem/check.png"),"check"),
					new MethodButton(900,600,200,100,Toolkit.getDefaultToolkit().getImage("./data/texasholdem/raise.png"),"raise"),
					new MethodButton(1100,600,200,100,Toolkit.getDefaultToolkit().getImage("./data/texasholdem/fold.png"),"fold"),	
			};
		}else if(i==5){
			buttons=new Button[]{
			};
		}
	}
	
	@Override
	public void start(){
		int temp=0;
		if(names.size()<5){
			names=names1;
		}
		for(int i=0;i<5;i++){
			players.add(new Player(((int)(Math.random()*21)-10),(int)(Math.random()*500+500),
					names.get( (int) (temp=(int)(Math.random()*names.size())) ) ));
			names.remove(temp);
		}
		for(Player p:players){
			if(players.indexOf(p)==bigblind){
				p.money-=20;
				p.move="bigblind";
				p.round=20;
				pot+=20;
			}else if(players.indexOf(p)==bigblind-1){
				p.money-=10;
				p.move="smallblind";
				p.round=10;
				pot+=10;
			}else{
				p.money-=5;
				p.move="anted";
				p.round=5;
				pot+=5;
			}
		}
		if(bigblind==5){
			Casino.profile.money-=20;
			move="bigblind";
			pround=20;
			pot+=20;
		}else if(bigblind==0){
			Casino.profile.money-=10;
			move="smallblind";
			pround=10;
			pot+=10;
		}else{
			Casino.profile.money-=5;
			move="anted";
			pround=5;
			pot+=5;
		}
		sort();
		deal();
		checkall();
		if(bigblind!=4){
			if(bigblind==5){
				playermoving=0;
			}else{
				playermoving=bigblind+1;
			}
			moveplayers();
		}else{
			change(1);
		}
	}
	public void call(){
		betindex = JOptionPane.showOptionDialog(null,
				"Call $"+call+"?",
				"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,
				new Object[] { "Yes", "No"}, "");
		if(betindex==0){
			Casino.profile.money-=(call-pround);
			pot+=(call-pround);
			pround=call;
			moveplayers();
			move="called";
		}
		betindex=-1;
	}
	
	public void raise(){
		if(call!=0){
			betindex = JOptionPane.showOptionDialog(null,
					"Raise how much?",
					"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "$5", "$10","$20","Cancel"}, "");
			if(betindex==0){
				Casino.profile.money-=(call-pround)+5;
				pot+=(call-pround)+5;
				pround=call+5;
				call+=5;
				moveplayers();
				raise=true;
				move="raiseto";
			}else if(betindex==1){
				Casino.profile.money-=(call-pround)+10;
				pot+=(call-pround)+10;
				pround=call+10;
				call+=10;
				raise=true;
				move="raisto";
				moveplayers();
			}else if(betindex==2){
				Casino.profile.money-=(call-pround)+20;
				pot+=(call-pround)+20;
				pround=call+20;
				call+=20;
				raise=true;
				move="raiseto";
				moveplayers();
			}
			betindex=-1;
		}else{
			betindex = JOptionPane.showOptionDialog(null,
					"Raise bet to $20?",
					"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "Yes","Cancel"}, "");
			if(betindex==0){
				Casino.profile.money-=20;
				pot+=20;
				pround=20;
				call+=20;
				raise=true;
				move="raiseto";
				moveplayers();
			}
		}
		betindex=-1;
	}
	
	public void fold(){
		round=4;
		JOptionPane.showMessageDialog(null,
				"You folded", "unfortunate!",
				JOptionPane.PLAIN_MESSAGE, null);
		reset();
	}
	
	
	public void round(){
		round+=1;
		raise=false;
		if(round!=4){
			call=0;
			pround=0;
			move="";
			for(Player p:players){
				if(!p.fold){
					p.round=0;
					p.move="";
					p.raise=false;
				}
			}
			moveplayers();
		}else{
			Casino.pause("end",10);
		}
		turn=true;
	}
	public int playermoving=0;
	
	public void moveplayers(){
		change(5);
		do{
			if(playermoving<5 && players.get(playermoving).fold){
				playermoving+=1;
			}else{
				break;
			}
		}while(true);
		if(playermoving!=5){
			if((players.get(playermoving).round!=call||(call==0 && !players.get(playermoving).move.equals("checked")))){
				Casino.pause("move",20);
			}else{
				turn=false;		
				Casino.frame.repaint();
				Casino.pause("round",20);
			}
		}else{
			if(call!=0 && pround!=call){
				change(1);
				playermoving=0;
			}else if(move.equals("checked") || call!=0){
				Casino.pause("round",20);
			}else{
				change(2);
				playermoving=0;
			}
		}
	}
	
	public void move(Player p){
		int choice=0;
		int confidence=0;
		int percentage=(int)(Math.random()*101);
		if(p.handvalue<10000){
			confidence=p.handvalue-5;
		}else if(p.handvalue<20000){
			confidence=5+p.handvalue-10000;	
		}else if(p.handvalue<30000){
			confidence=25+(p.handvalue-20000)/200;	
		}else if(p.handvalue<40000){
			confidence=45+(p.handvalue-30000)/2;	
		}else if(p.handvalue<60000){
			confidence=65;	
		}else if(p.handvalue<70000){
			confidence=80;
		}else if(p.handvalue<80000){
			confidence=80+(p.handvalue-80000)/200;
		}else if(p.handvalue<90000){
			confidence=90+(p.handvalue-90000)/2;
		}else{
			confidence=100;
		}
		confidence+=p.stupidity;
		confidence-=(int)(call/p.money);
		
		if(confidence<15){
			if(percentage<30){
				choice=0;
			}else if(percentage<90){
				choice=1;
			}else if(percentage<95){
				choice=2;
			}else if(percentage<98){
				choice=3;
			}else{
				choice=4;
			}
		}else if(confidence<30){
			if(percentage<3){
				choice=0;
			}else if(percentage<60){
				choice=1;
			}else if(percentage<85){
				choice=2;
			}else if(percentage<95){
				choice=3;
			}else{
				choice=4;
			}
		}else if(confidence<50){
			if(percentage<1){
				choice=0;
			}else if(percentage<65){
				choice=1;
			}else if(percentage<80){
				choice=2;
			}else if(percentage<90){
				choice=3;
			}else{
				choice=4;
			}
		}else if(confidence<70){
			if(percentage<1){
				choice=0;
			}else if(percentage<40){
				choice=1;
			}else if(percentage<70){
				choice=2;
			}else if(percentage<80){
				choice=3;
			}else{
				choice=4;
			}
		}else if(confidence<85){
			if(percentage<1){
				choice=0;
			}else if(percentage<30){
				choice=1;
			}else if(percentage<65){
				choice=2;
			}else if(percentage<80){
				choice=3;
			}else{
				choice=4;
			}
		}else{
			if(percentage<0){
				choice=0;
			}else if(percentage<25){
				choice=1;
			}else if(percentage<55){
				choice=2;
			}else if(percentage<70){
				choice=3;
			}else{
				choice=4;
			}
		}
		if(choice>1 && p.raise==true){
			choice=1;
		}
		
		if(call!=0){
			if(choice==0){
				p.move="folded";
				p.fold=true;
			}else if(choice==1){
				p.move="called";
				p.money-=call-p.round;
				pot+=call-p.round;
				p.round=call;
			}else if(choice==2){
				p.move="raiseto";
				p.money-=call-p.round+5;
				pot+=call-p.round+5;
				p.round=call+5;
					p.raise=true;
					call+=5;
			}else if(choice==3){
				p.move="raiseto";
				p.money-=call-p.round+10;
				pot+=call-p.round+10;
				p.round=call+10;
				p.raise=true;
				call+=10;
			}else if(choice==4){
				p.move="raiseto";
				p.money-=call-p.round+20;
				pot+=call-p.round+20;
				p.round=call+20;
				p.raise=true;
				call+=20;
			}
		}else{
			if(choice==0 || choice ==1){
				p.move="checked";
			}else{
				p.move="raiseto";
				p.money-=20;
				pot+=20;
				p.round=20;
				call+=20;
			}
		}
		playermoving+=1;
		moveplayers();
	}
	
	String name ="Barry";
	@Override
	public void end(){
		turn=false;
		for(Player p:players){
			if(p.handvalue>winner && !p.fold){
				winner=p.handvalue;
				winindex=players.indexOf(p);
			}
		}
		if(playervalue>winner){
			JOptionPane.showMessageDialog(null,
					"You won "+pot+" dollars!", "Victory!",
					JOptionPane.PLAIN_MESSAGE, null);
			Casino.profile.money+=pot;
			reset();
		}else{
			name=players.get(winindex).name;
			JOptionPane.showMessageDialog(null,
					name+" won "+pot+" dollars!", "You lost!",
					JOptionPane.PLAIN_MESSAGE, null);
			reset();
		}
	}
	
	public void checkall(){
		house.add(hand.get(0));
		house.add(hand.get(1));
		for(int x1 = 0;x1<3;x1++){
			for(int x2 = x1+1;x2<4;x2++){
				for(int x3=x2+1;x3<5;x3++){
					for(int x4=x3+1;x4<6;x4++){
						for(int x5=x4+1;x5<7;x5++){
							int temp=(int)handvalue(new Card[]{house.get(x1),house.get(x2),house.get(x3),house.get(x4),house.get(x5)});
							if(temp>playervalue){
								playervalue=temp;
								besthand=new ArrayList<Card>();
								besthand.add(house.get(x1));besthand.add(house.get(x2));besthand.add(house.get(x3));
								besthand.add(house.get(x4));besthand.add(house.get(x5));
							}
						}
					}
				}
			}
		}
		house.remove(hand.get(0));
		house.remove(hand.get(1));
		//System.out.println(playervalue);
		for(Player p:players){
			house.add(p.hand.get(0));
			house.add(p.hand.get(1));
			for(int x1 = 0;x1<3;x1++){
				for(int x2 = x1+1;x2<4;x2++){
					for(int x3=x2+1;x3<5;x3++){
						for(int x4=x3+1;x4<6;x4++){
							for(int x5=x4+1;x5<7;x5++){
								int temp=(int)handvalue(new Card[]{house.get(x1),house.get(x2),house.get(x3),house.get(x4),house.get(x5)});
								if(temp>p.handvalue){
									p.handvalue=temp;
									p.besthand=new ArrayList<Card>();
									p.besthand.add(house.get(x1));p.besthand.add(house.get(x2));p.besthand.add(house.get(x3));
									p.besthand.add(house.get(x4));p.besthand.add(house.get(x5));
								}
							}
						}
					}
				}
			}
			house.remove(p.hand.get(0));
			house.remove(p.hand.get(1));	
			//System.out.println(players.indexOf(p)+":"+p.handvalue);
		}
	}
	
	public double handvalue(Card[] phand){
		
		/* high card = 00000
		 * pair = 10000
		 * 2 pair = 20000
		 * 3 of a kind = 30000
		 * straight = 50000
		 * flush = 60000
		 * full house = 70000
		 * 4 of a kind = 80000
		 * Straight flush = 90000
		 * +
		 * face value
		 * 		   
		 */
		double value=0.0;

		for(Card c:phand){
			if(c.value==1){
				c.value=14;
			}
		}
		for(int x = 0;x<4;x++){
			for(int i = x+1;i<5;i++){
				Card c = new Card(-1,-1);
				if(phand[x].value>phand[i].value){
					c=phand[x];
					phand[x]=phand[i];
					phand[i]=c;
				}
			}
		}
		if((phand[0].value==phand[1].value-1) &&(phand[1].value==phand[2].value-1)
				&&(phand[2].value==phand[3].value-1) && ((phand[3].value==phand[4].value-1)|| (phand[0].value==2 && phand[4].value==14))){
			value+=50000;
			if(phand[0].value==2 && phand[4].value==14){
				value+=phand[3].value;
			}else{
				value+=phand[4].value;
			}
		}
		if(phand[0].suit==phand[1].suit && phand[1].suit==phand[2].suit &&
				phand[2].suit==phand[3].suit && phand[3].suit==phand[4].suit){
			value+=60000;
		}else{
			String[] pairs= {"a","a","a","a","a"};
			for(int x=0;x<5;x++){
				int counter=0;
				for (int i=0;i<5;i++){
					if(phand[x].value==phand[i].value){
						counter+=1;
					}
				}
				pairs[x]=counter+"X"+phand[x].value;
			}
			for(String s:pairs){
				if(s.charAt(0)=='2'){
					value+=5000;//this will occur twice
					if(value == 5000||value>30000)
						value+=(double)Integer.parseInt(""+s.charAt(2)+(s.length()>3?s.charAt(3):""));
					else if(value>15000 && value<20000){
						value+=(double)Integer.parseInt(""+s.charAt(2)+(s.length()>3?s.charAt(3):""))*100;
					}
				}else if(s.charAt(0)=='3'){
					value+=10000;//this will occur 3 times
					value+=(double)Integer.parseInt(""+s.charAt(2)+(s.length()>3?s.charAt(3):""))/3 *100;
				}else if(s.charAt(0)=='4'){
					value+=20000;//this will occur 4 times
					value+=(double)Integer.parseInt(""+s.charAt(2)+(s.length()>3?s.charAt(3):""))/2;
				}
			}
		}
		if(value>=40000 && value<=50000){
			value+=30000;
		}
		if(value>=110000){
			value=90000;
		}
		if(value==0){
			value=phand[4].value;
		}
		return value;
	}
	
	@Override
	public void reset(){
		currentcard=0; pot=0;playervalue=0; winner=0; winindex=0;round=0; call=20;playermoving=0;
		move="";
		players = new ArrayList<Player>();
		house = new ArrayList<Card>(5);
		besthand = new ArrayList<Card>();
		hand= new ArrayList<Card>();
		change(0);
		bigblind+=1;
		if(bigblind==6){
			bigblind=0;
		}
	}
	
	public void deal(){
		this.hand.add(deck[currentcard]);
		currentcard+=1;
		this.hand.add(deck[currentcard]);
		currentcard+=1;
		for(Player player:players){
			player.hand.add(deck[currentcard]);
			currentcard+=1;
			player.hand.add(deck[currentcard]);
			currentcard+=1;
		}
		for(int i=0;i<5;i++){
			house.add(deck[currentcard]);
			currentcard+=1;
		}
	}
	

	public void sort() {
		deck= new Card[52];
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
	
}
