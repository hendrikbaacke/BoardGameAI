package AI;

import java.util.Hashtable;

import src.Board;
import src.Hexagon;
import src.Move;

public class GameState {
	//all the information needed to store the move you made (from the first one)(we need to store first, second and third and moveTo in case the move needs to be done again):
	//use this to store every valid gamestate
	
	//store which move needs to be done to get to this state - such as which player's turn it is and which move they perform
	//if there is no second selected, it means that it is null
	public String first;
	public String second;
	public String third;
	public String moveTo;
	private Move move = src.Board.move;
	public int turn;
	public boolean valid = true;
	
	
	//later needed for the evaluation function
	public int point1;
	public int point2;
	//optional:
	public int point3;
	
	public Hashtable<String, Hexagon> boardState;
	
	
	public GameState(String first, String second, String third, String moveTo, Hashtable<String, Hexagon> old) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.moveTo = moveTo;
		
		//make a deep copy of the current board
		this.boardState = Board.copyHashBoard(old);
		if (first != null) {
			move.select(first, boardState);
			if (second != null) {
				move.select(second, boardState);
				if (third != null) {
					move.select(third, boardState);
				}
				else {
					move.select(first, boardState);
				}
			}
			else {
				move.select(first, boardState);
			}
			move.select(moveTo, boardState);
		}
		//scores
	}
	
}
