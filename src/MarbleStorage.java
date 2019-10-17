package src;


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
							Board.hashBoard.get(letterCode + j).centerY, 0, letterCode + j);

					storage.add(p);
				}
			}
		}
	
		for (int i = 0; i < 10; i++) {

			if (Board.hashBoard.get("A" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("A" + i).centerX,
						Board.hashBoard.get("A" + i).centerY, 1, "A" + i);
				//p.setLocationKey("A" + i);
				Board.hashBoard.get("A" + i).setFull(p);
				storage.add(p);

			}
			if (Board.hashBoard.get("B" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("B" + i).centerX,
						Board.hashBoard.get("B" + i).centerY, 1, "B"+i);
				//p.setLocationKey("B" + i);
				Board.hashBoard.get("B" + i).setFull(p);
				storage.add(p);

			}
			if (Board.hashBoard.get("C" + i) != null) {
				if (i > 2 && i < 6) {
					Marble p = new Marble(
							Board.hashBoard.get("C" + i).centerX,
							Board.hashBoard.get("C" + i).centerY, 1, "C" + i);
					p.setLocationKey("C" + i);
					Board.hashBoard.get("C" + i).setFull(p);
					storage.add(p);

				}
			}
			if (Board.hashBoard.get("I" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("I" + i).centerX,
						Board.hashBoard.get("I" + i).centerY, 2, "I" + i);
				//p.setLocationKey("I" + i);
				Board.hashBoard.get("I" + i).setFull(p);
				storage.add(p);

			}
			if (Board.hashBoard.get("H" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("H" + i).centerX,
						Board.hashBoard.get("H" + i).centerY, 2, "H" + i);
				//p.setLocationKey("H" + i);
				Board.hashBoard.get("H" + i).setFull(p);
				storage.add(p);

			}
			if (Board.hashBoard.get("G" + i) != null) {
				if (i > 4 && i < 8) {
					Marble p = new Marble(
							Board.hashBoard.get("G" + i).centerX,
							Board.hashBoard.get("G" + i).centerY, 2, "G" + i);
					//p.setLocationKey("G" + i);
					Board.hashBoard.get("G" + i).setFull(p);
					storage.add(p);
					

				}
			}
				//System.out.println("Number of items in the list: " + storage.size());
		     	//System.out.println(storage);
	
		}

		return pieceGroup;
	}


}
