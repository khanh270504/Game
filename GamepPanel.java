import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class GamepPanel extends JLayeredPane {

	public GamepPanel() {

		InitPanel();

		AddBackground();

	}

	private void InitPanel() {
		setLayout(null);
		setBounds(0, 0, GameLoader.WIDTH, GameLoader.HEIGHT);
//		setSize(GameLoader.WIDTH, GameLoader.HEIGHT);
		setPreferredSize(new Dimension(GameLoader.WIDTH, GameLoader.HEIGHT));
	}

	private void AddBackground() {

		// BackGround
		ImageIcon boardImage = new ImageIcon("resources/images/board.png");
		JLabel board = new JLabel(boardImage);
		board.setBounds(0, 0, boardImage.getIconWidth(), boardImage.getIconHeight());
		add(board, new Integer(0));
	}

}
