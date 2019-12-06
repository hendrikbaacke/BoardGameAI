package AI;

import java.util.Hashtable;
import src.Board;
import src.Hexagon;
import src.Move;

//should work

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
	public double evaluatedValue;
	
	
	public boolean valid;
	//later needed for the evaluation function
	public int point1;
	public int point2;
	//optional:
	public int point3;
	
	public Hashtable<String, Hexagon> boardState;
	
	GameState oldGameState;
	
	//rootNode
	public GameState(Hashtable<String, Hexagon> state, int turn) {
		this.boardState = Board.copyHashBoard(state);
		this.point1 = move.getScore1();
		this.point2 = move.getScore2();
		this.point3 = move.getScore3();
		//last one who moved
		this.turn = turn;
	}
	public GameState() {
		
	}
	
	public GameState(String first, String second, String third, String moveTo, GameState old) {
		//needed if we want a more extended tree
		this.turn = AddNodes.changePlayer(old.turn);
		int save = Board.move.playersTurn;
		Board.move.playersTurn = this.turn;
		Board.move.adding = true;
		
		this.first = first;
		this.second = second;
		this.third = third;
		this.moveTo = moveTo;
		
		
			//make a deep copy of the current board
			this.boardState = Board.copyHashBoard(old.boardState);
		
			if (first != null) {
				move.select(first, boardState);
				System.out.println("i am here");
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
			}
			
			move.select(moveTo, boardState);
			move.resetMove();
			
			 valid = !Board.compareHashtables(boardState, old.boardState);
		
			//scores old
			point1 = old.point1;
			point2 = old.point2;
			point3 = old.point3;
		
			if (Board.move.pushed) {
				if (turn == 1) {
					point1++;
				}
				else if(turn ==2) {
					point2++;
				
				}
				else if (turn ==3) {
					point3++;
				}
			}
		oldGameState = old;
		
		//set the turn back to the one that was actually needed
		move.pushed = false;
		move.playersTurn = save;
		move.adding = false;
		EvaluationFunction eval= new EvaluationFunction(this);
		evaluatedValue=eval.evaluate();
	}
	
	
}
