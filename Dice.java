import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.Timer;

public class Dice extends JButton {
	private float count = 0;
	private Timer time;
	public int cnt = 0;

	public int diceValue = 0;

	public boolean isPlaying = false;
	public GamepPanel gamepPanel;

	public Dice() {
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);

		setBounds(275, 275, 50, 50);

		PlayDice();

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isPlaying) {
					PlayDice();
					PanelControl.update();
					cnt++;

				}
			}

		});
		Game.gamePanel.add(this, new Integer(2));
	}

	private void SetDiceValue(int value) {
		setIcon(GameLoader.diceStatus[value - 1]);
		diceValue = value;
	}

	private int RandomDiceValue() {
		Random random = new Random();
		int value = random.nextInt(6) + 1;
		SetDiceValue(value);
		return value;
	}

	private void PlayDice() {
		if (diceValue != 0)
			return;

		isPlaying = true;

		time = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RandomDiceValue();
				count += 0.5f;
				if (count >= 5) {
					count = 0;
					isPlaying = false;
					time.stop();
					GameManager.instance.CheckPlayerCanMove();
				}
			}
		});
		time.start();
	}
}
