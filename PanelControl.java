import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelControl extends JPanel {
	public static JLabel jLabel;
	public JLabel jLabel2;
	public static Font font = new Font("TimeNewRoman", Font.BOLD, 15);
//	public static Border border = BorderFactory.createLineBorder(Color.BLACK, 5);

	public PanelControl() {
		jLabel2 = new JLabel("~~~~~LƯỢT CHƠI HIỆN TẠI~~~~~");
		jLabel = new JLabel("Đội xanh dương đi trước!!!");
		jLabel.setFont(font);
		jLabel.setForeground(Color.blue);
		setPreferredSize(new Dimension(200, 600));
		setLayout(new GridLayout(6, 1));
		setBackground(Color.LIGHT_GRAY);
		add(jLabel2);
		add(jLabel);
//		setBorder(border);

	}

	public static void update() {
		jLabel.setHorizontalAlignment(SwingConstants.CENTER); // Đặt căn giữa ngang
		jLabel.setVerticalAlignment(SwingConstants.CENTER);
		if (GameManager.currentPlayer == 0) {
			jLabel.setText("Đến lượt đội xanh dương!");
			jLabel.setFont(font);
			jLabel.setForeground(Color.blue);
//			border = BorderFactory.createLineBorder(Color.blue, 5);
		}
		if (GameManager.currentPlayer == 1) {
			jLabel.setText("Đến lượt đội đỏ!");
			jLabel.setFont(font);
			jLabel.setForeground(Color.red);
//			border = BorderFactory.createLineBorder(Color.red, 5);
		}
		if (GameManager.currentPlayer == 2) {
			jLabel.setText("Đến lượt đội xanh lá!");
			jLabel.setFont(font);
			jLabel.setForeground(Color.green);
//			border = BorderFactory.createLineBorder(Color.green, 5);
		}
		if (GameManager.currentPlayer == 3) {
			jLabel.setText("Đến lượt đội vàng!");
			jLabel.setFont(font);
			jLabel.setForeground(Color.yellow);
//			border = BorderFactory.createLineBorder(Color.yellow, 5);
		}

	}

	public static JButton button1;
	public static JButton button2;
}
