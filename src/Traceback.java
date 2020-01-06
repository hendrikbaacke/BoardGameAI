package src;

import java.util.ArrayList;
import java.util.Hashtable;

/*
 * Stores a traceback, in hashtable form.
 * The traceback is an arraylist -> with index 1 being the board at the start of the game.
 * At this point, this is infinite - can be used to refrain from choices that will bring us to the same point as before. So, prevent loops.
 */

public class Traceback {
	private static ArrayList<Hashtable<String, Hexagon>> traceback = new ArrayList<>();
	public static int current= 0;
	
	//add a hashboard to the traceback
	public void add() {
		traceback.add(BoardMethods.copyHashBoard(Board.hashBoard));
		System.out.println("Number of moves up until now: " + (current + 1));
		current++;
	}
	
	//return the whole arraylist
	public ArrayList<Hashtable<String, Hexagon>> getTB() {
		return traceback;
	}
	
	//return the hashtable that is ... moves back (input), so eg if you do goBack(1) it finds the previous hashboard
	public Hashtable<String, Hexagon> goBack(int movesAgo) {
		if (current - movesAgo >= 0) {
			Hashtable<String, Hexagon> find = traceback.get(current-movesAgo);
			return find;
		}
		else {
			Hashtable<String, Hexagon> find = new Hashtable<>();
			return find;
		}
		
	}
	
	// reset the traceback fully
	public void reset() {
		traceback = new ArrayList<Hashtable<String, Hexagon>>();
	}
}
