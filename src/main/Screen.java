package main;
import java.awt.Image;
import java.util.ArrayList;

import buttons.Button;

public class Screen {
	public Image image;
	public int screennumber = 0;
	public Button[] buttons;
	public Card[] deck= new Card[52];
	public ArrayList<Card> hand = new ArrayList<Card>();
	public ArrayList<Card> dealer = new ArrayList<Card>();
	public int currentbet=0;
	public boolean end=false;


	public Screen(Image image, int screennumber, Button[] buttons) {
		this.image = image;
		this.screennumber = screennumber;
		this.buttons = buttons;
	}
	
	public void change(int i){
	}
	public void method(String s){
	}
	public void explain(String s){
	}
	public void start(){
	}
	public void reset(){
	}
	public void end(){
	}
}
