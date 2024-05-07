import java.util.ArrayList;
import java.util.List;

public class GameManager {
	public static GameManager instance;

	private Player[] PlayerList;
	public static int currentPlayer = 0;

	public List<Horse> HorseOnBoard;

	private Dice dice;

	public GameManager() {
		instance = this;

		Init();

//		PlayerList[0].horseList.get(0).Start();

//		PlayerList[1].horseList.get(0).Start();

	}

	private void Init() {
		HorseOnBoard = new ArrayList<>();
		InitPlayerList();
		InitDice();
	}

	private void InitDice() {
		dice = new Dice();
	}

	private void InitPlayerList() {
		PlayerList = new Player[4];
		for (int i = 0; i < 4; i++) {
			Player newPlayer = new Player(i);
			PlayerList[i] = newPlayer;
		}
		currentPlayer = 0;
	}

	public void HorseSelected(Horse horse) {
		if (dice.diceValue == 0 || dice.isPlaying) {
			return;
		}

		if (horse.team == PlayerList[currentPlayer].id) {
			if (horse.isOnBoard) {
				int CheckCanMove = CheckHorseCanMove(horse);
				if (CheckCanMove != -1) {
					currentPlayer += 1;
//					if (currentPlayer == 4) {
//						currentPlayer = 0;
//					}

					if (CheckCanMove == 1) {
						horse.Move(dice.diceValue);
					} else {
						KillHorse(horse.PositionIndex + dice.diceValue);
						horse.Move(dice.diceValue);
					}

					if (dice.diceValue == 6) {
						currentPlayer -= 1;
					}
					currentPlayer %= 4;

					dice.diceValue = 0;
				}
			} else {
				if (CheckHorseCanStart(horse)) {
					KillHorse(horse.team * 14);
					horse.Start();

					dice.diceValue = 0;
				}
			}
		}
	}

	 public int CheckHorseCanMove(Horse _horse){
        int canMove = 1;
        for (int i = 0; i < HorseOnBoard.size(); i++){
            if (HorseOnBoard.get(i).PositionIndex != _horse.PositionIndex) {
                if ((Horse.IndexAfterMove(_horse, dice.diceValue) - HorseOnBoard.get(i).PositionIndex) > 0 &&
                (Horse.IndexAfterMove(_horse, dice.diceValue) - HorseOnBoard.get(i).PositionIndex) <= 6){
                    return -1;
                }
                if (HorseOnBoard.get(i).PositionIndex == Horse.IndexAfterMove(_horse, dice.diceValue)){

                    if (HorseOnBoard.get(i) == _horse || HorseOnBoard.get(i).team == _horse.team){
                        return -1;
                    }
                    else{
                        canMove = 2;
                    }
                }
            }

            if (_horse.StepCount == 55){
                return 1;
            }

            if (_horse.StepCount + dice.diceValue > 55 && !_horse.isOnStable){
                return -1;
            }

        }
        return canMove;
    }


	public void KillHorse(int horsePosIndex) {
		for (int i = 0; i < HorseOnBoard.size(); i++) {
			if (HorseOnBoard.get(i).PositionIndex == horsePosIndex) {
				HorseOnBoard.get(i).Die();
				break;
			}
		}
	}

	public boolean CheckHorseCanStart(Horse _horse) {
		if (dice.diceValue != 6) {
			return false;
		}

		for (int i = 0; i < HorseOnBoard.size(); i++) {
			if (HorseOnBoard.get(i).PositionIndex == _horse.team * 14 && HorseOnBoard.get(i).team == _horse.team
					&& HorseOnBoard.get(i) != _horse) {
				return false;
			}
		}
		return true;
	}

	public void CheckPlayerCanMove() {
		boolean next = true;
		System.out.println(currentPlayer);
		switch (currentPlayer) {
		case 0:
			System.out.println("Lượt chơi của xanh dương");
			break;
		case 1:
			System.out.println("Lượt chơi của đỏ");
			break;
		case 2:
			System.out.println("Lượt chơi của xanh lá");
			break;
		case 3:
			System.out.println("Lượt chơi của vàng");
			break;
		}
		for (Horse horse : PlayerList[currentPlayer].horseList) {
			if (horse.isOnBoard) {
				if (CheckHorseCanMove(horse) != -1) {
					next = false;
				}
			} else {
				if (CheckHorseCanStart(horse)) {
					next = false;
				}
			}
		}

		if (next) {
			Next();
		}
	}

	private void Next() {
		currentPlayer += 1;

		dice.diceValue = 0;
//		if (currentPlayer == 4) {
//			currentPlayer = 0;
//		}
		currentPlayer %= 4;
		System.out.println("Next");

	}

	public Player getCurrentPlayer() {
		return PlayerList[currentPlayer];
	}
}
