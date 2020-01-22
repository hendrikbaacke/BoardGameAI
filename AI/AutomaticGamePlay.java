package AI;

import java.util.Hashtable;
import AI.WeightOptimisation.GameEnvironment;
import src.BoardMethods;
import src.GameData;
import src.Hexagon;
import src.Move;
import src.Traceback;

/*
 * Automatically play games and get results right away.
 * Some notes:
 * -the board, marbles and move already have to be created.
 * -the parameter is the board.
 * -this will return the board, but it is also possible to return the number of moves.
 * -i made it so that when you push the "perform ai move" button in the gamegui, it automatically runs a whole game.
 * -can run multiple games in a row.
 * -if you want to do it with multiple ais, the ai needs to be changed before this method is ran again.
 */

public class AutomaticGamePlay {
	
	public static int[] playGame(Hashtable<String, Hexagon> startBoard) {
		Hashtable<String, Hexagon> board = BoardMethods.copyHashBoard(startBoard);
		int[] result = new int[2];

		GameData.tb.reset();
		Move.resetMove();
		Move.point = 0;
		Move.point2 = 0;
		Move.point3 = 0;
		Move.automaticGame = true;
		Move.automaticGameEnd = false;
		Move.one = null;
		Move.two = null;
		Move.three = null;
		Move.pushed = false;
		Move.adding = false;
		
		boolean store1 = Move.player1AI;
		boolean store2 = Move.player2AI;
		boolean store3 = Move.player3AI;
		Move.player1AI = true;
		Move.player2AI = true;
		Move.player3AI = true;

		
		while(!Move.automaticGameEnd && Traceback.totalMoves < GameEnvironment.limit){
			Move.checkAI(board);
		}
		System.out.println("winner is " + Move.winnerAutomaticGame);
		
		int winner = Move.winnerAutomaticGame;
		
		result[0] = Traceback.totalMoves;
		result[1] = Move.point - Move.point2;
		
		//Reset everything now, so when the normal game is being player, everything is normal again.
		Move.playersTurn =1;
		Move.point = 0;
		Move.point2 = 0;
		Move.point3 = 0;
		
		Move.winnerAutomaticGame =0;
		Move.automaticGame = false;
		Move.automaticGameEnd = false;
		GameData.tb.reset();
		Move.resetMove();
		Move.player1AI = store1;
		Move.player2AI = store2;
		Move.player3AI = store3;
		Move.one = null;
		Move.two = null;
		Move.three = null;
		Move.pushed = false;
		Move.adding = false;
		Move.ai = false;
		AddNodes.nodeNR = 1;
		ModeDetermination.Counter = 1;
		
		return result;
	}
}
