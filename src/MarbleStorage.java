package src;

import java.util.LinkedList;

import javafx.scene.layout.AnchorPane;

public class MarbleStorage {

	public static AnchorPane pieceGroup = new AnchorPane();
	LinkedList<Marble> storage = new LinkedList<Marble>();
	 
	public AnchorPane Balls() {
		//filling every Hexagon with dummy marbles
		for (char ch='A'; ch <= 'I'; ch++) {
			String letterCode = Character.toString(ch);
			for (int j = 0; j < 10; j++) {
				if (Board.hashBoard.get(letterCode + j) != null) {
					Marble p = new Marble(Board.hashBoard.get(letterCode + j).centerX,
							Board.hashBoard.get(letterCode + j).centerY, 20, 0);
					p.setLocationKey(letterCode + j);
					storage.add(p);
				}
			}
		}
		for (int i = 0; i < 10; i++) {

			if (Board.hashBoard.get("A" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("A" + i).centerX,
						Board.hashBoard.get("A" + i).centerY, 20, 1);
				p.setLocationKey("A" + i);
				storage.add(p);
			}
			if (Board.hashBoard.get("B" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("B" + i).centerX,
						Board.hashBoard.get("B" + i).centerY, 20, 1);
				p.setLocationKey("B" + i);
				storage.add(p);

			}
			if (Board.hashBoard.get("C" + i) != null) {
				if (i > 2 && i < 6) {
					Marble p = new Marble(
							Board.hashBoard.get("C" + i).centerX,
							Board.hashBoard.get("C" + i).centerY, 20, 1);
					p.setLocationKey("C" + i);
					storage.add(p);

				}
			}
			if (Board.hashBoard.get("I" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("I" + i).centerX,
						Board.hashBoard.get("I" + i).centerY, 20, 2);
				p.setLocationKey("I" + i);
				storage.add(p);

			}
			if (Board.hashBoard.get("H" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("H" + i).centerX,
						Board.hashBoard.get("H" + i).centerY, 20, 2);
				p.setLocationKey("H" + i);
				storage.add(p);

			}
			if (Board.hashBoard.get("G" + i) != null) {
				if (i > 4 && i < 8) {
					Marble p = new Marble(
							Board.hashBoard.get("G" + i).centerX,
							Board.hashBoard.get("G" + i).centerY, 20, 2);
					p.setLocationKey("G" + i);
					storage.add(p);

				}
			}
			//System.out.println("Number of items in the list: " + storage.size());
		     //System.out.println(storage);
		}

		
		return pieceGroup;
	}


}
