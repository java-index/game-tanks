package game.view;

import javax.swing.*;
import java.awt.*;

public class StartGamePanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(50, 50, 100, 100);
	}
}
