import java.util.LinkedList;

import javafx.scene.layout.AnchorPane;

public class MarbleStorage {

	public static AnchorPane pieceGroup = new AnchorPane();
	LinkedList<Marble> storage = new LinkedList<Marble>();
	 
	public AnchorPane Balls() {
		for (int i = 0; i < 10; i++) {

			if (Board.hashBoard.get("A" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("A" + i).centerX,
						Board.hashBoard.get("A" + i).centerY, 1);
				storage.add(p);
			}
			if (Board.hashBoard.get("B" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("B" + i).centerX,
						Board.hashBoard.get("B" + i).centerY, 1);
				storage.add(p);

			}
			if (Board.hashBoard.get("C" + i) != null) {
				if (i > 2 && i < 6) {
					Marble p = new Marble(Board.hashBoard.get("C" + i).centerX,
							Board.hashBoard.get("C" + i).centerY, 1);
					storage.add(p);

				}
			}
			if (Board.hashBoard.get("I" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("I" + i).centerX,
						Board.hashBoard.get("I" + i).centerY, 2);
				storage.add(p);

			}
			if (Board.hashBoard.get("H" + i) != null) {
				Marble p = new Marble(Board.hashBoard.get("H" + i).centerX,
						Board.hashBoard.get("H" + i).centerY, 2);
				storage.add(p);

			}
			if (Board.hashBoard.get("G" + i) != null) {
				if (i > 4 && i < 8) {
					Marble p = new Marble(Board.hashBoard.get("G" + i).centerX,
							Board.hashBoard.get("G" + i).centerY, 2);
					storage.add(p);

				}
			}
			//System.out.println("Number of items in the list: " + storage.size());
			//System.out.println(storage);
		}
		return pieceGroup;
	}
}
