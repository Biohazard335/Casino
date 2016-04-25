package main;

import java.util.ArrayList;

public class Player {
	
	public int money=0,handvalue=0, highcard=0,round=5,stupidity=0;
	public String move="",name="";

	public boolean fold=false,raise=false;
	
	public ArrayList<Card> hand= new ArrayList<Card>();
	public ArrayList<Card> besthand = new ArrayList<Card>();
	
	public Player(int dumb,int money,String name){
		this.stupidity=dumb;
		this.money=money;
		this.name=name;
	}
}