package src;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.layout.AnchorPane;

//i added something to this so you can see for every hexagon whether it contain a marble or not - and the marble is linked to it as well!!
//so if this gets updated correctly, the marbles are accessible through the hashboard as well

public class MarbleStorage {
	public static AnchorPane pieceGroup = new AnchorPane();
	LinkedList<Marble> storage = new LinkedList<Marble>();

	public AnchorPane Balls() {
		for (char ch='A'; ch <= 'I'; ch++) {
			String letterCode = Character.toString(ch);
			for (int j = 0; j < 10; j++) {
				if (Board.hashBoard.get(letterCode + j) != null) {
					Marble p = new Marble(Board.hashBoard.get(letterCode + j).centerX,
					Board.hashBoard.get(letterCode + j).centerY, 0, letterCode + j, true);
					storage.add(p);
				}
			}
		}
	//if number of players = 2
	if (Board.numberPlayers == 2) {
		for (int i = 0; i < 10; i++) {
			if (Board.hashBoard.get("A" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("A" + i).centerX,
						Board.hashBoard.get("A" + i).centerY, 1, "A" + i, true);
				//p.setLocationKey("A" + i);
				Board.hashBoard.get("A" + i).setFull(p);
				storage.add(p);
			}
			if (Board.hashBoard.get("B" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("B" + i).centerX,
						Board.hashBoard.get("B" + i).centerY, 1, "B"+i, true);
				//p.setLocationKey("B" + i);
				Board.hashBoard.get("B" + i).setFull(p);
				storage.add(p);
			}
			if (Board.hashBoard.get("C" + i) != null) {
				if (i > 2 && i < 6) {
					Marble p = new Marble(
							Board.hashBoard.get("C" + i).centerX,
							Board.hashBoard.get("C" + i).centerY, 1, "C" + i, true);
					p.setLocationKey("C" + i);
					Board.hashBoard.get("C" + i).setFull(p);
					storage.add(p);
				}
			}
			if (Board.hashBoard.get("I" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("I" + i).centerX,
						Board.hashBoard.get("I" + i).centerY, 2, "I" + i, true);
				//p.setLocationKey("I" + i);
				Board.hashBoard.get("I" + i).setFull(p);
				storage.add(p);
			}
			if (Board.hashBoard.get("H" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("H" + i).centerX,
						Board.hashBoard.get("H" + i).centerY, 2, "H" + i, true);
				//p.setLocationKey("H" + i);
				Board.hashBoard.get("H" + i).setFull(p);
				storage.add(p);
			}
			if (Board.hashBoard.get("G" + i) != null) {
				if (i > 4 && i < 8) {
					Marble p = new Marble(
							Board.hashBoard.get("G" + i).centerX,
							Board.hashBoard.get("G" + i).centerY, 2, "G" + i, true);
					//p.setLocationKey("G" + i);
					Board.hashBoard.get("G" + i).setFull(p);
					storage.add(p);
				}
			}
		}
	}
	//now, numberPlayers 3
	else {
		for (int i = 0; i < 10; i++) {
			//player 1
			if (Board.hashBoard.get("A" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("A" + i).centerX,
						Board.hashBoard.get("A" + i).centerY, 1, "A" + i, true);
				//p.setLocationKey("A" + i);
				Board.hashBoard.get("A" + i).setFull(p);
				storage.add(p);
			}
			if (Board.hashBoard.get("B" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("B" + i).centerX,
						Board.hashBoard.get("B" + i).centerY, 1, "B"+i, true);
				//p.setLocationKey("B" + i);
				Board.hashBoard.get("B" + i).setFull(p);
				storage.add(p);
			}
		}
		//player 2
		char start = 'I';
		for (int i = 6; i >= 5; i--) {
			for (int j = 0; j < 9; j++) {
				String lettercode = Character.toString(start);
				int temp = i-j;
				if (Board.hashBoard.containsKey((lettercode+temp))) {
					Marble p = new Marble(Board.hashBoard.get(lettercode + temp).centerX, Board.hashBoard.get(lettercode + temp).centerY, 2, lettercode + temp, true);
					Board.hashBoard.get(lettercode + temp).setFull(p);
					storage.add(p);
				}
				start = (char) (start -1);
			}
			start = 'I';
		}
		
		//player 3
		for (int j = 8; j < 10; j++) {
			for (char ch='A'; ch <= 'I'; ch++) {
				String letterCode = Character.toString(ch);
				if (Board.hashBoard.containsKey((letterCode+j))) {
					//row.add(Board.hashBoard.get(letterCode+j));
					Marble p = new Marble(Board.hashBoard.get(letterCode + j).centerX, Board.hashBoard.get(letterCode + j).centerY, 3, letterCode + j, true);
					Board.hashBoard.get(letterCode + j).setFull(p);
					storage.add(p);
				}
			}
		}
		
	}
		return pieceGroup;
	}
	
	public MarbleStorage clone() {
		MarbleStorage marbles = new MarbleStorage();
		marbles.storage = new LinkedList<Marble>();
		for(int i = 0; i < this.storage.size(); i++) {
			marbles.storage.add(this.storage.get(i).deepClone());
		}
		return marbles;
	}
	
}
