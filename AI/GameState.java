package AI;

import java.util.Hashtable;

import src.Hexagon;

public class GameState {
	//all the information needed to store the move you made (from the first one)(we need to store first, second and third and moveTo in case the move needs to be done again):
	private String first;
	private String second;
	private String third;
	private String moveTo;
	
	private int point;
	private int point2;
	//optional:
	private int point3;
	
	private int turn;
	
	private Hashtable<String, Hexagon> boardState;
	
	
	
}
