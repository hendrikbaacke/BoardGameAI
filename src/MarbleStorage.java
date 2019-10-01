import java.util.LinkedList;

public class MarbleStorage {
	LinkedList<Marble> storage = new LinkedList<Marble>();
	
	public void startSituation() {
		int player = 1;
		int marbleRowStart = 5;
		String code = null;
		
		for (int i = 0; i < 2; i++) {
			for (int k = 1; k < marbleRowStart; k++) {
				String letter = Character.toString((char)('A'+i));
				String number = Integer.toString(k);
				code = letter + number;
			}
			Marble now = new Marble(code, player, 10);
			storage.add(now);
			marbleRowStart++;
		}
			
		player = 2;
		for (int j = 8; j > 6; j--) {
			
			
		}
		
	}
	
}
