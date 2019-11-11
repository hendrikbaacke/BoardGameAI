package src;

import java.util.ArrayList;

public class Traceback {
	private static ArrayList<MarbleStorage> traceback = new ArrayList<MarbleStorage>();
	public static int current= 0;
	
	public void add() {
		traceback.add(Board.boardMarbles.clone());
		System.out.println("Number of moves up until now: " + (current + 1));
		current++;
	}
	
	public ArrayList<MarbleStorage> getTB() {
		return traceback;
	}
	
	public MarbleStorage goBack(int movesAgo) {
		if (current - movesAgo >= 0) {
			MarbleStorage find = traceback.get(current-movesAgo);
			return find;
		}
		else {
			MarbleStorage find = new MarbleStorage();
			return find;
		}
		
	}
	
	public void reset() {
		traceback = new ArrayList<MarbleStorage>();
	}
}
