import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Horse extends JButton{
    
    public int team;
    public int id;
    public vector2 Position;
    public int PositionIndex;
    
    public int StepCount = 0;
    public boolean isOnStable = false;
    public boolean isWin = false;
    public int countHorse = 0;
    public boolean isOnBoard = false;

    public Horse(String imageURL, int _team, int _id){
        Position = new vector2(0, 0);
        team = _team;
        id = _id;
        PositionIndex = 0;

        ImageIcon i = new ImageIcon(imageURL);
        Image image = i.getImage();
        Image scaledImage = image.getScaledInstance(GameLoader.HorseSize, GameLoader.HorseSize, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));

        
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);

        Game.gamePanel.add(this, new Integer(1));

        GoHome();

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnClick();
            }
        });
    }

    public void OnClick(){
        GameManager.instance.HorseSelected(this);
    }

    public void setPosition(vector2 newPos){
        Position = newPos;
        setBounds(Position.x - GameLoader.HorseSize/2, Position.y - GameLoader.HorseSize/2, GameLoader.HorseSize, GameLoader.HorseSize);
    }

    public void Move(int step){
        if (StepCount >= 55){
        	if (step == 6) {
        	   GoHome();
        	   Game.gamePanel.remove(this);
        	    GameManager.instance.RemoveHorse(id);
        	    System.out.println(GameManager.instance.PlayerList[GameManager.currentPlayer].horseList.size());
        	    isWin = true;
        	    return;
        	}

            isOnStable = true;
            StepCount += step;
            PositionIndex = 56 + team * 6 + step;
            setPosition(GameLoader.movePosList.get(PositionIndex));
            return;
        }

        if (StepCount < 55){
            StepCount += step;
            PositionIndex += step;

            if (PositionIndex > 55){
             //   System.out.println("hello");
                PositionIndex -= 56;
            }

            setPosition(GameLoader.movePosList.get(PositionIndex));
            return;
        }
    }

    public void Start(){
        isOnBoard = true;
        PositionIndex = team * 14;
        Move(0);

        GameManager.instance.HorseOnBoard.add(this);
    }

    public void Die(){
        isOnBoard = false;
        GoHome();
        GameManager.instance.HorseOnBoard.remove(this);
    }

    public void GoHome(){
        setPosition(new vector2(GameLoader.PlayerOriginPos[team].x + GameLoader.HorseBaseOffset[id].x * GameLoader.HorseSize, GameLoader.PlayerOriginPos[team].y + GameLoader.HorseBaseOffset[id].y * GameLoader.HorseSize));
    }

    public static int IndexAfterMove(Horse _horse, int step){
        int indexAferterMove = _horse.PositionIndex + step;
        if (indexAferterMove > 55){
            return indexAferterMove - 56;
        }
        return indexAferterMove;
    }   
}
