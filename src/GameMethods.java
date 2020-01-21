package src;

import java.util.Hashtable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

/*
 * Some general methods needed to play the game or to make gamestates.
 * Includes:
 * -changing player
 * -changing the colours back to normal
 */

public class GameMethods {

	public static boolean AlertON = true;

	//changes a turn automatically
	public static int changePlayer(int playersTurn) {
		if (playersTurn ==1) {
			playersTurn = 2;
		}
		else {
			if (GameData.numberPlayers == 2 || GameData.numberPlayers == 3 && playersTurn ==3) {
				playersTurn = 1;
			}
			else if (GameData.numberPlayers == 3 && playersTurn ==2){
				if (playersTurn == 2) {
					playersTurn = 3;
				}
			}
		}
		return playersTurn;
	}
	
	//changes the turn back
	public static int changeBack(int playerNr) {
		if (playerNr ==3) {
			return 2;
		}
		else if (playerNr ==2) {
			return 1;
		}
		else if (playerNr ==1 && GameData.numberPlayers ==3) {
			return 3;
		}
		else {
			return 2;
		}
	}
	
	public static void coloursBackToNormal(Hashtable<String, Hexagon> board) {
		if (!Move.adding) {
			if(board.get(Move.first).marble.playerNumber == 1) {
				board.get(Move.first).marble.setFill(Color.BLACK);
				if(Move.second != null && !board.get(Move.second).empty) {
					board.get(Move.second).marble.setFill(Color.BLACK);
				}
				if(Move.third != null) {
					board.get(Move.third).marble.setFill(Color.BLACK);
				}
			}
			if(board.get(Move.first).marble.playerNumber == 2) {
				board.get(Move.first).marble.setFill(Color.GRAY);
				if(Move.second != null && !board.get(Move.second).empty) {
					board.get(Move.second).marble.setFill(Color.GRAY);
				}
				if(Move.third!=null && !board.get(Move.third).empty) {
					board.get(Move.third).marble.setFill(Color.GRAY);
				}
			}
			if (GameData.numberPlayers ==3) {
				if(board.get(Move.first).marble.playerNumber == 3) {
					board.get(Move.first).marble.setFill(Color.DARKGREEN);
					if(Move.second != null && !board.get(Move.second).empty) {
						board.get(Move.second).marble.setFill(Color.DARKGREEN);
					}
					if(Move.third!=null && !board.get(Move.third).empty) {
						board.get(Move.third).marble.setFill(Color.DARKGREEN);
					}
				}
			}
		}
	}
	
	//checking whether the game is finished and actually finishing it
		public static void gameFinished() {
			GameGui.score_text1.setText(String.valueOf(Move.point));
			GameGui.score_text2.setText(String.valueOf(Move.point2));
			if (GameData.numberPlayers == 3) {
				GameGui.score_text3.setText(String.valueOf(Move.point3));
			}
			if(AlertON && !Move.automaticGame) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Game Over");
				alert.setHeaderText("Winner is:");
				if (Move.point == 6) {
					String s = "Game over, Player 1 won!";
					alert.setContentText(s);
					alert.show();
					GameGui.winner_text.setText("Game over, Player 1 won!");
				}

				if (Move.point2 == 6) {

					String s = "Game over, Player 2 won!";
					alert.setContentText(s);
					alert.show();
					GameGui.winner_text.setText("Game over, Player 2 won!");
				}

				if (GameData.numberPlayers == 3) {
					if (Move.point3 == 6) {
						String s = "Game over, Player 3 won!";
						alert.setContentText(s);
						alert.show();
						GameGui.winner_text.setText("Game over, Player 3 won!");
					}
				}

				alert.setOnCloseRequest(event ->
				{
					System.out.println("CLOSING");
					System.exit(0);
				});
			}
			else if (Move.automaticGame){
				if (Move.point ==6) {
					Move.automaticGameEnd =true;
					Move.winnerAutomaticGame =1;
				}
				else if(Move.point2 ==6) {
					Move.automaticGameEnd =true;
					Move.winnerAutomaticGame = 2;
				}
				else if(Move.point3==6) {
					Move.automaticGameEnd =true;
					Move.winnerAutomaticGame = 3;
				}
			}
		}
}
