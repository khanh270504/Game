
import java.util.ArrayList;
import java.util.List;

public class Player {
	public int id;
	public List<Horse> horseList;
	public String horseImageURL;

	public Player(int _id) {

		id = _id;
		HorseInit();
	}

	private void HorseInit() {
		horseList = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			Horse newHorse = new Horse("resources/images/H" + Integer.toString(id) + ".GIF", id, i);

			horseList.add(newHorse);
		}
	}
}
