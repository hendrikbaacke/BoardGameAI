package AI;

import java.util.Hashtable;

import src.Board;
import src.Hexagon;

public class GameState {
	//all the information needed to store the move you made (from the first one)(we need to store first, second and third and moveTo in case the move needs to be done again):
	//use this to store every valid gamestate
	
	//store which move needs to be done to get to this state - such as which player's turn it is and which move they perform
	//if there is no second selected, it means that it is null
	public String first;
	public String second;
	public String third;
	public String moveTo;
	public int turn;
	
	
	//later needed for the evaluation function
	public int point1;
	public int point2;
	//optional:
	public int point3;
	
	public Hashtable<String, Hexagon> boardState;
	
	
	public GameState(String first, String second, String third, String moveTo, int point1, int point2, int point3, int turn, Hashtable<String, Hexagon> old) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.moveTo = moveTo;
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		this.turn = turn;
		
		//make a deep copy of the current board
		this.boardState = Board.copyHashBoard(old);
	}
	
}
