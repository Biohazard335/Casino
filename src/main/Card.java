package main;

public class Card {
	
	public int suit,value,x,y;
	public boolean used =false;
	
	public Card(int suit, int value){
		this.suit=suit;
		this.value=value;
	}
	
	public String toString(){
		String title="";
		if(this.value==13){
			title+="King";
		}else if(this.value==1 || this.value==14){
			title+="Ace";
		}else if(this.value==12){
			title+="Queen";
		}else if(this.value==11){
			title+="Jack";
		}else{
			title+=""+this.value;
		}
		title+=" of ";
		if(this.suit==0){
			title+="Spades";
		}else if(this.suit==1){
			title+="Diamonds";
		}else if(this.suit==2){
			title+="Clubs";
		}else{
			title+="Hearts";
		}
		return title;
	}
}
