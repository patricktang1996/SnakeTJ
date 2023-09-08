package version1;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class StartGame {
	public static void main(String[] args) {
		JFrame jf =new JFrame();
		jf.setTitle("Snake Game   by:TJ");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((width-700)/2,(height-700)/2,700,700);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		GamePanel gp = new GamePanel();
		jf.add(gp);
		jf.setVisible(true);
	}
}
