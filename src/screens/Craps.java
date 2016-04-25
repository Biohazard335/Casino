package screens;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import buttons.Button;
import buttons.ExitButton;
import buttons.MethodButton;
import main.Casino;
import main.Screen;

public class Craps extends Screen{
	
	public int die1=0,die2=0,die1x=0,die1y=0,die2x=0,die2y=0, total=0, point=0,betchoice=-1,
			additional=0, betindex=-1;
	public boolean win=false, pass=true;
	public ArrayList<String> single = new ArrayList<String>();
	public ArrayList<String> multi = new ArrayList<String>();
	public ArrayList<Integer> singlemoneys= new ArrayList<Integer>();
	public ArrayList<Integer> multimoneys = new ArrayList<Integer>();
	public ArrayList<Integer> eraseindexes = new ArrayList<Integer>();
	
	public Craps() {
		super(Toolkit.getDefaultToolkit().getImage("./data/craps/craps.png"), 3, 
			new Button[]{
				new ExitButton(1000, 50),
				new MethodButton(100,600,400,100,Toolkit.getDefaultToolkit().getImage("./data/craps/pass line.png"),"passbet"),
				new MethodButton(200,530,350,70,Toolkit.getDefaultToolkit().getImage("./data/craps/dontpassbar.png"),"dontpass"),
				new MethodButton(0,200,100,400,Toolkit.getDefaultToolkit().getImage("./data/craps/pass line2.png"),"passbet"),
				new MethodButton(100,200,70,350,Toolkit.getDefaultToolkit().getImage("./data/craps/dontpassbar2.png"),"dontpass")
			}
		);
	}
	
	public void display(){
		String string="";
		if(pass){
			string+="Pass line bet: $"+currentbet;
		}else{
			string+="Don't pass bet: $"+currentbet;
		}
		string+="\nMulti:";
		for(String s:multi){
			string+="\n     "+s+": $"+multimoneys.get(multi.indexOf(s));
		}
		if(multi.size()==0){
			string+="\n     None";
		}
		string+="\nSingle:";
		for(String s:single){
			string+="\n     "+s+": $"+singlemoneys.get(single.indexOf(s));
		}
		if(single.size()==0){
			string+="\n     None";
		}
		JOptionPane.showMessageDialog(null,
				string, "Bets",
				JOptionPane.PLAIN_MESSAGE, null);
	}
	
	@Override
	public void method(String s){
		if(s.equals("passbet") || s.equals("dontpass")){
			passdont(s);
		}else if(s.equals("display")){
			display();
		}else if(s.equals("shoot")){
			shoot();
		}else if(s.contains("single")){
			single(s);
		}else if(s.contains("buy")){
			prebuy(s);
		}else if(s.contains("hard")){
			hardways(s);
		}
	}
	public void hardways(String s){
		betindex=-1;
		for(String s2:multi){
			if(s.equals(s2)){
				betindex=multi.indexOf(s2);
			}
		}
		if(betindex==-1){
		betchoice= JOptionPane.showOptionDialog(null,
				"Would you like to place a bet on a Hard "+s.charAt(4)+(s.length()==6?s.charAt(5):"")+"?",
				"Bet",0,JOptionPane.PLAIN_MESSAGE, null,
				new Object[] {"20","50","100","Cancel"},"");
			if (betchoice == 0) {
				multimoneys.add(20);
				Casino.profile.money-=20;
			} else if (betchoice == 1) {
				multimoneys.add(50);
				Casino.profile.money-=50;
			} else if (betchoice == 2) {
				multimoneys.add(100);
				Casino.profile.money-=100;
			}if(betchoice!=-1 && betchoice!=3){
				multi.add(s);
			}
		}else{
			betchoice= JOptionPane.showOptionDialog(null,
					"Would you like to add to your bet on a Hard "+s.charAt(4)+(s.length()==6?s.charAt(5):"")+"?",
					"Bet",0,JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"20","50","100","Remove","Cancel"},"");
			if (betchoice == 0) {
				multimoneys.set(betindex,multimoneys.get(betindex)+20);
				Casino.profile.money-=20;
			} else if (betchoice == 1) {
				multimoneys.set(betindex,multimoneys.get(betindex)+50);
				Casino.profile.money-=50;
			} else if (betchoice == 2) {
				multimoneys.set(betindex,multimoneys.get(betindex)+100);
				Casino.profile.money-=100;
			}if(betchoice==3){
				multi.remove(betindex);
				multimoneys.remove(betindex);
			}	
		}
		betindex=-1;
	}
	
	public void single(String s){
		String value="-1";
		if(s.charAt(6)=='c'){
			value="Any Craps";
		}else if(s.charAt(6)=='f'){
			value="The Field";
		}else if(s.length()==7){
			value=""+s.charAt(6);
		}else{
			value=""+s.charAt(6)+s.charAt(7);
		}
		betindex=-1;
		for(String s2:single){
			if(value.equals(s2)){
				betindex=single.indexOf(s2);
			}
		}
		if(betindex==-1){
			betchoice= JOptionPane.showOptionDialog(null,
					"Would you like to place a single roll bet on "+ value +"?",
					"Bet",0,JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"20","50","100","Cancel"},"");
			if (betchoice == 0) {
				singlemoneys.add(20);
				Casino.profile.money-=20;
			} else if (betchoice == 1) {
				singlemoneys.add(50);
				Casino.profile.money-=50;
			} else if (betchoice == 2) {
				singlemoneys.add(100);
				Casino.profile.money-=100;
			}if(betchoice!=-1 && betchoice!=3){
				single.add(value);
			}
		}else{
			betchoice= JOptionPane.showOptionDialog(null,
					"Would you like to add to your single roll bet on "+ value +"?",
					"Bet",0,JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"20","50","100","Remove","Cancel"},"");
			if (betchoice == 0) {
				singlemoneys.set(betindex,singlemoneys.get(betindex)+20);
				Casino.profile.money-=20;
			} else if (betchoice == 1) {
				singlemoneys.set(betindex,singlemoneys.get(betindex)+50);
				Casino.profile.money-=50;
			} else if (betchoice == 2) {
				singlemoneys.set(betindex,singlemoneys.get(betindex)+100);
				Casino.profile.money-=100;
			}if(betchoice==3){
				single.remove(betindex);
				singlemoneys.remove(betindex);
			}	
		}
		betindex=-1;
	}
	
	public void prebuy(String s){
		for(String n:multi){
			if(n.contains("buy") || n.contains("lay")){
				if(s.charAt(3)==n.charAt(3)){
					betindex=multi.indexOf(n);
					break;
				}else{
					betindex=-1;
				}
			}else if(n.contains("place")){
				if(n.charAt(5)==s.charAt(3)){
					betindex=multi.indexOf(n);
					break;
				}else{
					betindex=-1;
				}
			}
		}
		if(betindex==-1){
			betchoice = JOptionPane.showOptionDialog(null,
					"Would you like to place a bet on "+s.charAt(3)+(s.length()==5?s.charAt(4):"")+"?",
					"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "Place", "Buy", "Lay","Cancel" }, "");
			if(betchoice!=-1 && betchoice!=3){
				buy(betchoice, Integer.parseInt(""+s.charAt(3)));
			}
		}else{
			betchoice = JOptionPane.showOptionDialog(null,
					"Change your bet on "+s.charAt(3)+(s.length()==5?s.charAt(4):"")+"?",
					"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "Place", "Buy", "Lay","Forfeit","Cancel" }, "");
			if(betchoice!=-1 && betchoice!=3 && betchoice!=4){
				multi.remove(betindex);
				multimoneys.remove(betindex);
				buy(betchoice, Integer.parseInt(""+s.charAt(3)));
				betindex=-1;
			}else if(betchoice==3){
				multi.remove(betindex);
				multimoneys.remove(betindex);
				betindex=-1;
			}
		}
		
	}
	
	public void buy(int i,int num){
		if(num==1){
			num=10;
		}
		betchoice = JOptionPane.showOptionDialog(null,
				"How much money would you like to bet?",
				"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,
				new Object[] { "20", "50", "100" }, "");
		if (betchoice == 0) {
			multimoneys.add(20);
			Casino.profile.money-=20;
		} else if (betchoice == 1) {
			multimoneys.add(50);
			Casino.profile.money-=50;
		} else if (betchoice == 2) {
			multimoneys.add(100);
			Casino.profile.money-=100;
		}if(betchoice!=-1){
			if(i==0){
				multi.add("place"+num);
			}else if(i==1){
				multi.add("buy"+num);
			}else if(i==2){
				multi.add("lay"+num);
			}
		}
	}
	
	public void passdont(String s){
		if(currentbet==0){
			betchoice = JOptionPane.showOptionDialog(null,
					"How much money would you like to bet?",
					"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "20", "50", "100" }, "");
			if (betchoice == 0) {
				currentbet=20;
			} else if (betchoice == 1) {
				currentbet=50;
			} else if (betchoice == 2) {
				currentbet=100;
			}if(betchoice!=-1){
				if(s.equals("passbet")){
					pass=true;
				}
				if(s.equals("dontpass")){
					pass=false;
				}
				Casino.profile.money-=currentbet;
				change(1);
			}
			betchoice=-1;
		}else{
			if( ( (s.equals("passbet")&&pass==true) || (s.equals("dontpass")&& pass==false) ) && point!=0){
				betchoice = JOptionPane.showOptionDialog(null,"Would you like to add to the current bet?",
						"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,new Object[] { "20", "50", "100", "cancel" }, "");
				if (betchoice == 0) {
					additional+=20;
					Casino.profile.money-=20;
				} else if (betchoice == 1) {
					additional+=50;
					Casino.profile.money-=50;
				} else if (betchoice == 2) {
					additional+=100;
					Casino.profile.money-=100;
				}
			}else if(point!=0){
				betchoice = JOptionPane.showOptionDialog(null,"Switch current bet?",
						"Bet", 0, JOptionPane.PLAIN_MESSAGE, null,new Object[] { "Yes", "No" }, "");
				if(betchoice==0){
					pass=!pass;
				}
			}
		}
	}
	
	@Override
	public void change(int i){
		if(i==0){
			buttons= new Button[]{new ExitButton(1050, 50),
					new MethodButton(100,600,400,100,Toolkit.getDefaultToolkit().getImage("./data/craps/pass line.png"),"passbet"),
					new MethodButton(200,530,350,70,Toolkit.getDefaultToolkit().getImage("./data/craps/dontpassbar.png"),"dontpass"),
					new MethodButton(0,200,100,400,Toolkit.getDefaultToolkit().getImage("./data/craps/pass line2.png"),"passbet"),
					new MethodButton(100,200,70,350,Toolkit.getDefaultToolkit().getImage("./data/craps/dontpassbar2.png"),"dontpass")
				};
		}else if(i==1){
			buttons= new Button[]{
					new MethodButton(575,200,200,100,Toolkit.getDefaultToolkit().getImage("./data/craps/check.png"),"display"),
					new ExitButton(1050, 50),
					new MethodButton(850,50,200,100,Toolkit.getDefaultToolkit().getImage("./data/craps/shoot.png"),"shoot"),
					new MethodButton(100,600,400,100,Toolkit.getDefaultToolkit().getImage("./data/craps/pass line.png"),"passbet"),
					new MethodButton(200,530,350,70,Toolkit.getDefaultToolkit().getImage("./data/craps/dontpassbar.png"),"dontpass"),
					new MethodButton(0,200,100,400,Toolkit.getDefaultToolkit().getImage("./data/craps/pass line2.png"),"passbet"),
					new MethodButton(100,200,70,350,Toolkit.getDefaultToolkit().getImage("./data/craps/dontpassbar2.png"),"dontpass"),
					new MethodButton(170,200,55,91,Toolkit.getDefaultToolkit().getImage("./data/craps/buy4.png"),"buy4"),
					new MethodButton(225,200,55,91,Toolkit.getDefaultToolkit().getImage("./data/craps/buy5.png"),"buy5"),
					new MethodButton(280,200,55,91,Toolkit.getDefaultToolkit().getImage("./data/craps/buy6.png"),"buy6"),
					new MethodButton(335,200,55,91,Toolkit.getDefaultToolkit().getImage("./data/craps/buy8.png"),"buy8"),
					new MethodButton(390,200,55,91,Toolkit.getDefaultToolkit().getImage("./data/craps/buy9.png"),"buy9"),
					new MethodButton(445,200,55,91,Toolkit.getDefaultToolkit().getImage("./data/craps/buy10.png"),"buy10"),
					new MethodButton(850,200,400,75,Toolkit.getDefaultToolkit().getImage("./data/craps/single7.png"),"single7"),
					new MethodButton(850,250,200,100,Toolkit.getDefaultToolkit().getImage("./data/craps/hard4.png"),"hard4"),
					new MethodButton(1050,250,200,100,Toolkit.getDefaultToolkit().getImage("./data/craps/hard6.png"),"hard6"),
					new MethodButton(1050,350,200,100,Toolkit.getDefaultToolkit().getImage("./data/craps/hard8.png"),"hard8"),
					new MethodButton(850,350,200,100,Toolkit.getDefaultToolkit().getImage("./data/craps/hard10.png"),"hard10"),
					new MethodButton(850,450,133,100,Toolkit.getDefaultToolkit().getImage("./data/craps/single3.png"),"single3"),
					new MethodButton(983,450,134,100,Toolkit.getDefaultToolkit().getImage("./data/craps/single2.png"),"single2"),
					new MethodButton(1117,450,133,100,Toolkit.getDefaultToolkit().getImage("./data/craps/single12.png"),"single12"),
					new MethodButton(850,550,200,100,Toolkit.getDefaultToolkit().getImage("./data/craps/single11.png"),"single11"),
					new MethodButton(1050,550,200,100,Toolkit.getDefaultToolkit().getImage("./data/craps/single11.png"),"single11"),
					new MethodButton(850,650,400,75,Toolkit.getDefaultToolkit().getImage("./data/craps/singlec.png"),"singlec"),
					new MethodButton(170,430,430,100,Toolkit.getDefaultToolkit().getImage("./data/craps/field.png"),"singlef")
			};
		}
	}
	
	public void shoot(){
		die1=(int)(Math.random()*6+1);
		die2=(int)(Math.random()*6+1);
		die1x=(int)(Math.random()*100);
		die1y=(int)(Math.random()*50);
		die2x=(int)(Math.random()*100);
		die2y=(int)(Math.random()*50);
		total=die1+die2;
		if(total==7){
			for(String s:multi){
				if(s.contains("place")){
					JOptionPane.showMessageDialog(null,"Your place bet on "+s.charAt(5)+(s.length()==7?s.charAt(6):"")+" lost!",
							"Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
					eraseindexes.add(multi.indexOf(s));
				}else if(s.contains("buy")){
					JOptionPane.showMessageDialog(null,"Your buy bet on "+s.charAt(3)+(s.length()==5?s.charAt(4):"")+" lost!",
							"Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
					eraseindexes.add(multi.indexOf(s));	
				}else if(s.contains("hard")){
					JOptionPane.showMessageDialog(null,"Your bet on a Hard "+s.charAt(4)+(s.length()==6?s.charAt(5):"")+" lost!",
							"Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
					eraseindexes.add(multi.indexOf(s));	
				}else if(s.contains("lay")){
					JOptionPane.showMessageDialog(null,"Your lay bet on "+s.charAt(3)+(s.length()==5?s.charAt(4):"")+" won!",
							"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
					if(s.charAt(3)=='4' || s.charAt(3)=='1'){
						Casino.profile.money+=(int)((1+(1.0/2.0))*multimoneys.get(multi.indexOf(s)))
								-(.05*multimoneys.get(multi.indexOf(s)));
					}else if(s.charAt(3)=='5' || s.charAt(3)=='9'){
						Casino.profile.money+=(int)((1+(2.0/3.0))*multimoneys.get(multi.indexOf(s)))
								-(.05*multimoneys.get(multi.indexOf(s)));		
					}else if(s.charAt(3)=='6' || s.charAt(3)=='8'){
						Casino.profile.money+=(int)((1+(5.0/6.0))*multimoneys.get(multi.indexOf(s)))
								-(.05*multimoneys.get(multi.indexOf(s)));		
					}
				}
			}
			for(int i=eraseindexes.size()-1;i>=0;i--){
				multi.remove((int)eraseindexes.get(i));
				multimoneys.remove((int)eraseindexes.get(i));
			}
			eraseindexes= new ArrayList<Integer>();
		}
		for(String s: multi){
			if(s.contains("place")) {
				if((s.charAt(5)=='1' && total==10)|| (s.charAt(5)=='4' &&total==4)){
					JOptionPane.showMessageDialog(null,"Your place bet on "+s.charAt(5)+(s.length()==7?s.charAt(6):"")+" won!",
							"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
					Casino.profile.money+=(int)((1+(9.0/5.0))*multimoneys.get(multi.indexOf(s)));
					
				}else if((s.charAt(5)=='5' && total==5)||(s.charAt(5)=='9' && total==9)){
					JOptionPane.showMessageDialog(null,"Your place bet on "+s.charAt(5)+" won!", "Congratulations!",
							JOptionPane.PLAIN_MESSAGE, null);
					Casino.profile.money+=(int)((1+(7.0/5.0))*multimoneys.get(multi.indexOf(s)));
					
				}else if((s.charAt(5)=='6' && total==6) || (s.charAt(5)=='8' && total==8)){
					JOptionPane.showMessageDialog(null,"Your place bet on "+s.charAt(5)+"  won!", "Congratulations!",
							JOptionPane.PLAIN_MESSAGE, null);
					Casino.profile.money+=(int)((1+(7.0/6.0))*multimoneys.get(multi.indexOf(s)));
					
				}
			}
			if(s.contains("buy")) {
				if(Integer.parseInt(""+s.charAt(3))==total || (Integer.parseInt(""+s.charAt(3))==1 && total==10)){
					JOptionPane.showMessageDialog(null,"Your buy bet on "+(s.charAt(3)=='1'?"10":s.charAt(3))+" won!",
						"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
					if(total==4 || total == 10){
						Casino.profile.money+=(int)((1+(2.0/1.0))*multimoneys.get(multi.indexOf(s)))-(.05*multimoneys.get(multi.indexOf(s)));
					}else if(total==5 || total==9){
						Casino.profile.money+=(int)((1+(3.0/2.0))*multimoneys.get(multi.indexOf(s)))-(.05*multimoneys.get(multi.indexOf(s)));	
					}else{
						Casino.profile.money+=(int)((1+(6.0/5.0))*multimoneys.get(multi.indexOf(s)))-(.05*multimoneys.get(multi.indexOf(s)));		
					}
				}
			}
			if(s.contains("lay")){
				if(Integer.parseInt(""+s.charAt(3))==total || (Integer.parseInt(""+s.charAt(3))==1 && total==10)){
					JOptionPane.showMessageDialog(null,"Your lay bet on "+(s.charAt(3)=='1'?"10":s.charAt(3))+" lost!",
						"Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
					eraseindexes.add(multi.indexOf(s));
				}
			}
			if(s.contains("hard")){
				if(total==Integer.parseInt(s.length()==6?(""+s.charAt(4)+s.charAt(5)):""+s.charAt(4))){
					if(die1==die2){
						if((int)s.charAt(4)=='4' || (int)s.charAt(4)=='1'){
							JOptionPane.showMessageDialog(null,"Your bet on a Hard "+(s.charAt(4)=='1'?"10":s.charAt(4))+" won!",
									"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
							Casino.profile.money+=(int)((1+(7.0/1.0))*multimoneys.get(multi.indexOf(s)));
						}else{
							JOptionPane.showMessageDialog(null,"Your bet on a Hard "+s.charAt(4)+" won!",
									"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
							Casino.profile.money+=(int)((1+(9.0/1.0))*multimoneys.get(multi.indexOf(s)));
						}
					}else{
						JOptionPane.showMessageDialog(null,"Your bet on a Hard "+(s.charAt(4)=='1'?"10":s.charAt(4))+" lost!",
								"Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
					}
					eraseindexes.add(multi.indexOf(s));	
				}
			}
		}

		for(int i=eraseindexes.size()-1;i>=0;i--){
			multi.remove((int)eraseindexes.get(i));
			multimoneys.remove((int)eraseindexes.get(i));
		}
		eraseindexes= new ArrayList<Integer>();
		
		
		for(String s: single){
			if(s.equals("Any Craps")){
				if (total==2 || total==12 || total==3){
					JOptionPane.showMessageDialog(null,"Your bet on any Craps won!",
						"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
					Casino.profile.money+=(int)((1+(7.0/1.0))*singlemoneys.get(single.indexOf(s)));	
				}else{
					JOptionPane.showMessageDialog(null,"Your bet on any Craps lost!",
							"Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
				}
			}else if(s.equals("The Field")){
				if(total==3||total==4||total==9||total==10||total==11){
					JOptionPane.showMessageDialog(null,"Your Field bet won!",
							"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
						Casino.profile.money+=(int)((1+(1.0/1.0))*singlemoneys.get(single.indexOf(s)));	
					
				}else if(total==2){
					JOptionPane.showMessageDialog(null,"Your Field bet won!",
							"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
						Casino.profile.money+=(int)((1+(2.0/1.0))*singlemoneys.get(single.indexOf(s)));	
					
				}else if(total==12){
					JOptionPane.showMessageDialog(null,"Your Field bet won!",
							"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
						Casino.profile.money+=(int)((1+(3.0/1.0))*singlemoneys.get(single.indexOf(s)));	
				}else{
					JOptionPane.showMessageDialog(null,"Your Field bet lost!",
							"Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
					
				}
			}else if(Integer.parseInt(s)==total){
				JOptionPane.showMessageDialog(null,"Your bet on "+s+" won!",
					"Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
				if(s.equals("11")|| s.equals("3")){
					Casino.profile.money+=(int)((1+(15.0/1.0))*singlemoneys.get(single.indexOf(s)));
				}
				else if(s.equals("7")){
					Casino.profile.money+=(int)((1+(4.0/1.0))*singlemoneys.get(single.indexOf(s)));
				}		
				else{
					Casino.profile.money+=(int)((1+(30.0/1.0))*singlemoneys.get(single.indexOf(s)));
				}
			}else{
				JOptionPane.showMessageDialog(null,"Your bet on "+s+" lost!",
					"Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
			}
		}
		
		single= new ArrayList<String>();
		
		if(point==0){
			if(total==7 || total== 11){
				win=pass;
				end();
			}else if(total==2||total==3||total==12){
				win=!pass;
				end();
			}else{
				point=total;
			}	
		}else{
			if(total==7){
				win=!pass;
				end();
			}else{
				if(total==point){
					win=pass;
					end();
				}else{
					
				}
			}
		}
	}
	
	@Override
	public void end(){
		if(win){
			if(pass)
				JOptionPane.showMessageDialog(null,"Your Pass Line bet won!", "Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
			else
				JOptionPane.showMessageDialog(null,"Your Don't Pass bet won!", "Congratulations!",JOptionPane.PLAIN_MESSAGE, null);
			Casino.profile.money+=(int)((1+(1.0/1.0))*currentbet);
			if(point == 4 || point == 10){
				if(pass)
					Casino.profile.money+=(int)((1+(2.0/1.0))*additional);
				else
					Casino.profile.money+=(int)((1+(1.0/2.0))*additional);	
			}else if(point==5 || point==9){
				if(pass)
					Casino.profile.money+=(int)((1+(3.0/2.0))*additional);
				else
					Casino.profile.money+=(int)((1+(2.0/3.0))*additional);	
			}else if(point==6 || point == 8){
				if(pass)
					Casino.profile.money+=(int)((1+(6.0/5.0))*additional);
				else
					Casino.profile.money+=(int)((1+(5.0/6.0))*additional);	
			}
			point=0;
		}else{
			if(pass)
				JOptionPane.showMessageDialog(null,"Your Pass Line bet lost!", "Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
			else
				JOptionPane.showMessageDialog(null,"Your Don't Pass bet lost!", "Unfortunate!",JOptionPane.PLAIN_MESSAGE, null);
		}
		simplereset();
	}
	
	public void simplereset(){
		die1=0;die2=0;die1x=0;die1y=0;die2x=0;die2y=0;total=0;point=0;betchoice=-1;additional=0;
		currentbet=0;
		win=true;
		pass=false;
		change(0);
	}
	
	@Override
	public void reset(){
		die1=0;die2=0;die1x=0;die1y=0;die2x=0;die2y=0;total=0;point=0;betchoice=-1;additional=0;
		single = new ArrayList<String>();
		multi = new ArrayList<String>();
		singlemoneys = new ArrayList<Integer>();
		multimoneys = new ArrayList<Integer>();
		currentbet=0;
		win=true;
		pass=false;
		change(0);
	}

	@Override
	public void explain(String s){
		String string="";
		if(s.equals("passbet")){
			string="";
		}else if(s.equals("dontpass")){
			string="";
		}else if(s.equals("display")){
			string="";
		}else if(s.equals("shoot")){
			string="";
		}else if(s.contains("single")){
			string="";
		}else if(s.contains("buy")){
			string="";
		}else if(s.contains("hard")){
			string="";
		}
		JOptionPane.showMessageDialog(null,
				string, "Information",
				JOptionPane.PLAIN_MESSAGE, null);
	}
}