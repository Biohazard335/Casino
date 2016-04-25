package main;


import screens.*;
public class Casino {

	public static Frame frame = new Frame(1300, 700);
	
	public static String s="";
	public static int i=0;

	public static int screen = 0;
	
	public static Screen[] screens= {new Opening(),new Hub(), new Blackjack(), new Craps(), new SlotMachines(), new TexasHoldem()};
	
	public static Profile profile;
	
	public static void exit(){
		System.exit(0);
	}
	
	public static void pause(String s,int i){
		Casino.s=s;
		Casino.i=i;
	}
				    
	public static  Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					frame.repaint();
					if(i!=0){
						try {
							Thread.sleep(100*i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						i=0;
						screens[screen].method(s);
					}else{
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});


	public static void main(String[] args) {
		thread.start();
	}
}