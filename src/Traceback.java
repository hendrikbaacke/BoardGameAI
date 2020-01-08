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
	public static int current= 1;
	public static int totalMoves = 1;
	private int tracebacksize = 10;
	
	//add a hashboard to the traceback
	public void add() {
		if (current < tracebacksize) {
			current++;
		}
		else {
			current = 1;
		}
		
		if (traceback.size() < tracebacksize) {
			traceback.add(BoardMethods.copyHashBoard(Board.hashBoard));
		}
		else {
			traceback.set(current, BoardMethods.copyHashBoard(Board.hashBoard));
		}
		
		System.out.println("Number of moves up until now: " + (totalMoves));
		totalMoves++;
	}
	
	//return the whole arraylist
	public ArrayList<Hashtable<String, Hexagon>> getTB() {
		return traceback;
	}
	
	// reset the traceback fully
	public void reset() {
		traceback = new ArrayList<Hashtable<String, Hexagon>>();
	}
}
