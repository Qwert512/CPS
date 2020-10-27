package Butt1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Button extends JFrame implements ActionListener {

	private JButton button;

	static double Clicks = 0;
	static double CPS = 0;
	static double Time = 3;
	static int scale = 100;
	static int x = scale;
	static int y = scale;
	static long[] times = new long[10000];
	static int num = 0;
	static long startsystime;
	static long lasttime = 0;
	static long now;

	public Button() {
		this.button = new JButton("OwO");

		this.getContentPane().add(this.button);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {

			now = System.currentTimeMillis();
			times[(int) Clicks] = now - lasttime;
			lasttime = now;

			Clicks++;
		}
	}

	public static void main(String[] args) {
		Button bec = new Button();
		bec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bec.setSize((int) 100 * x, (int) 100 * y);
		bec.setVisible(true);
		bec.button.setBackground(Color.RED);
		bec.button.setLabel("RDY?");
		try {
			Thread.sleep((int) 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		bec.button.addActionListener(bec);
		bec.button.setBackground(Color.GREEN);
		bec.button.setLabel("GOOOOO");
		lasttime = System.currentTimeMillis();

		try {
			Thread.sleep((int) Time * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		bec.button.removeActionListener(bec);
		bec.button.setBackground(Color.RED);
		bec.button.setLabel("STOP!!!!");

		try {
			Thread.sleep((int) 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		CPS = Clicks / Time;
		System.out.printf("CPS:   %5.1f\n", CPS);
		bec.button.setLabel(String.format("CPS:%.1f", CPS));
		try {
			Thread.sleep((int) 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.printf("Clicks:%5.1f\n", Clicks);
		bec.button.setLabel(String.format("Clicks:%.1f", Clicks));
		try {
			Thread.sleep((int) 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.printf("Time:  %5.1f\n", Time);
		bec.button.setLabel(String.format("Time:%.1f", Time));
		try {
			Thread.sleep((int) 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		// System.out.println("Differenzen:");
		if (num == 0) {
			System.out.println(String.format("Differenz zu Start:\t%3d", times[num]));
			num = 1;

		}

		while (num < Clicks) {
			System.out.println(times[num]);
			num++;

		}
		num = 0;
		try {

			FileWriter myWriter = new FileWriter("CPS_INFO.txt");

			myWriter.write(String.format("CPS:\t%6.1f\n", CPS));
			myWriter.write(String.format("Clicks:\t%6.1f\n", Clicks));
			myWriter.write(String.format("Time:\t%6.1f\n", Time));
			if (num == 0) {
				myWriter.write(String.format("Differenz zu Start:\t%3d\n", times[num]));
				num = 1;

			}
			while (num < Clicks) {
				myWriter.write(String.format("Differenz zu vorher:\t%3d\n", times[num]));
				num++;
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {

			e.printStackTrace();
		}

		System.exit(0);

	}
}