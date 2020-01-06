package src;

import java.util.ArrayList;
import java.util.Hashtable;

/*
 * Has methods which with you can:
 * -clone a hashboard
 * -compare two boards
 * -get the marbles in one board from a certain player
 */

public class BoardMethods {
	public static ArrayList<String> hash = Board.hash;
	
	//copies a whole hashboard - depends on the one you get as input
	public static Hashtable<String, Hexagon> copyHashBoard(Hashtable<String, Hexagon> hex){
		Hashtable<String, Hexagon> newBoard = new Hashtable<>();
		for (char i= 'A'; i < 'J'; i++) {
			for (int j = 1; j < 10; j++) {
				String code = Character.toString(i) + j;
				if (hex.containsKey(code)){
					newBoard.put(code, hex.get(code).deepClone());
				}
			}
		}
		return newBoard;
	}
	
	//compares two hashtables, if they contain the same marbles (from the same player), at the same spot, then it returns true
	public static boolean compareHashtables(Hashtable<String, Hexagon> one, Hashtable<String, Hexagon> two) {		
		for(int i = 0; i < hash.size(); i++) {
			if(!one.get(hash.get(i)).empty && !two.get(hash.get(i)).empty) {
				if (one.get(hash.get(i)).marble.playerNumber != two.get(hash.get(i)).marble.playerNumber) {
					return false;
				}
			}
			else if((one.get(hash.get(i)).empty && !two.get(hash.get(i)).empty) || (!one.get(hash.get(i)).empty && two.get(hash.get(i)).empty)) {
				return false;
			}
		}
		return true;
	}
	
	//creates and returns an array list with all the marbles from a certain player
	public static ArrayList<String> getMarblesPlayer(Hashtable<String, Hexagon> board, int playerNumber){
		ArrayList<String> marbles = new ArrayList<>();
		for (int i = 0; i < hash.size(); i++) {
			if (!board.get(hash.get(i)).empty) {
				if (board.get(hash.get(i)).marble.playerNumber == playerNumber) {
					marbles.add(hash.get(i));
				}
			}
		}
		return marbles;
	}
}
