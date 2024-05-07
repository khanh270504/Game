import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class GameLoader {
	public static int FPS = 60;

	public static int Scale = 1;

	public static int WIDTH = 800;
	public static int HEIGHT = 600;

	public static int HorseSize = 40;

	public static List<vector2> movePosList;

	public static vector2[][] StableList;

	public static vector2[] PlayerOriginPos;

	public static vector2[] HorseBaseOffset;

	public static ImageIcon[] diceStatus;

	public static void Init() {
		InitDiceImage();

		movePosList = new ArrayList<>();

		HorseBaseOffset = new vector2[4];
		HorseBaseOffset[0] = new vector2(-1, -1);
		HorseBaseOffset[1] = new vector2(1, -1);
		HorseBaseOffset[2] = new vector2(-1, 1);
		HorseBaseOffset[3] = new vector2(1, 1);

		PlayerOriginPos = new vector2[4];
		PlayerOriginPos[0] = new vector2(121, 121);
		PlayerOriginPos[1] = new vector2(121, 479);
		PlayerOriginPos[2] = new vector2(479, 479);
		PlayerOriginPos[3] = new vector2(479, 121);

		LoadMovePosFromFile();
		InitStatblePosition();
	}

	private static void LoadMovePosFromFile() {
		try {
			File file = new File("resources/data/toado.txt");
			Scanner scanner = new Scanner(file);

			// Đọc số hàng và số cột từ file
			int rows = scanner.nextInt();
			int columns = scanner.nextInt();

			// Đọc dữ liệu từ file và gán vào mảng
			for (int i = 0; i < rows; i++) {
				vector2 v = new vector2(scanner.nextInt(), scanner.nextInt());
				movePosList.add(v);

			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void InitDiceImage() {
		diceStatus = new ImageIcon[6];

		for (int i = 1; i <= 6; i++) {
			ImageIcon dice = new ImageIcon("resources/images/dice" + Integer.toString(i) + ".png");
			diceStatus[i - 1] = dice;
		}
	}

	private static void InitStatblePosition() {
		StableList = new vector2[4][6];
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 6; j++) {
				StableList[i - 1][j - 1] = movePosList.get(56 + ((i - 1) * 6 - 1 + j));
			}
		}
	}
}
