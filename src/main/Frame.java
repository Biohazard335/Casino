package main;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Frame extends JFrame{

	public static int width, height;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame(int width, int height) {
		Frame.width = width;
		Frame.height = height;
		setSize(Frame.width , Frame.height + 30);
		setResizable(false);
		setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2-20);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addMouseListener(new Mouse());
		Dimension minSize = new Dimension(Frame.width , Frame.height + 30);
		setMinimumSize(minSize);
		add(new Panel());
		pack();
		setVisible(true);
	}

}
