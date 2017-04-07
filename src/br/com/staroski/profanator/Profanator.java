package br.com.staroski.profanator;

import javax.swing.JFrame;
import javax.swing.UIManager;

public final class Profanator {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		try {
			ProfanatorFrame frame = new ProfanatorFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private Profanator() {}
}
