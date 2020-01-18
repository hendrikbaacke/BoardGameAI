package AI;

import java.util.Hashtable;

import src.BoardMethods;
import src.GameData;
import src.Hexagon;
import src.Move;
/*
 * Automatically play games and get results right away.
 */

public class AutomaticGamePlay {
	
	public static void playGame(Hashtable<String, Hexagon> startBoard) {
		Hashtable<String, Hexagon> board = BoardMethods.copyHashBoard(startBoard);
		Move.automaticGame = true;
		Move.player1AI = true;
		Move.player2AI = true;
		Move.player3AI = true;
		
		while(!Move.automaticGameEnd){
			Move.checkAI(board);
		}
		
		System.out.println("winner is " + Move.winnerAutomaticGame);
		
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
	}
	
}
