package src;

import java.util.ArrayList;
import java.util.Hashtable;

import AI.GameState;
import AI.PerformAIAction;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;


//trying to clean this one up a bit
/*
 * Whenever a move on the board or in a gamestate needs to be performed, this class's methods are used.
 */

public class Move {
	//first, second, and third marble code that need to be moved (the hexagon in the hashboard contains the marbles of the same code- which can be removed and added quickly)
	public static String first;
	public static String second;
	public static String third;
	public static int point= 0;
	public static int point2= 0;
	public static int point3 = 0;
	public static boolean pushed = false;
	public static boolean adding = false;
	
	//following arraylist can be used to find the ones that need to be moved:
	public static ArrayList<String> selectedMarbles = new ArrayList<String>();
	public static int nrSelected = 0; //keep track of how many marbles are selected

	//code of the hexagon the marble needs to be moved to
	public static String moveTo;
	
	//when the selection process is done, this will be true:
	private static boolean selected = false;
	
	//keep track of the player that needs to move a marble
	public static int playersTurn = 1;
	
	//if THIS MOVE is ai or not
	public static boolean ai = false;
	
	//which players are ai players
	public static boolean player1AI = false;
	public static boolean player2AI = false;
	public static boolean player3AI = false;
	
	//choose if we use greedy or not
	private static boolean greedy = true;
	
	//separately
	private static boolean greedyPlayer1 = false;
	private static boolean greedyPlayer2 = false;
	

	public Move() {
		first = null;
		second = null;
		third = null;
		moveTo = null;
	}
	
	//select marbles
		public void select(String code, Hashtable<String, Hexagon> board) {
			//check whether a marble from the player is selected, then set that as a code and add it to the selection:
			if (first == null && !board.get(code).empty) {
				if (board.get(code).marble.playerNumber == playersTurn) {
					//System.out.println("selected 1");
					board.get(code).marble.setFill(Color.PURPLE);
					first = code;
					nrSelected++;
					selectedMarbles.add(code);
				}
			}
			//if the first and second are the same, end selection (you're done) it also checks whether they are adjacent
			//if not adjacent: the second selected marble becomes the first - else just add it to the selection
			else if(second == null  && !board.get(code).empty && !selected) {
				if (board.get(code).marble.playerNumber == playersTurn) {
					if (first.equals(code)) {
						selected = true;
						board.get(code).marble.setFill(Color.ORANGE);
					}
					else if(!board.get(code).adjacent(first)) {
						resetSelection(code, board);
					}
					else{
						second = code;
						board.get(code).marble.setFill(Color.AQUAMARINE);
						nrSelected++;
						selectedMarbles.add(code);
					}
				}
			}
			//quite similar to the second selection - checks whether it is adjacent to the second one, if not, then it becomes the first in the selection
			//if  this is done, selected becomes true automatically
			else if(third == null && !selected && !board.get(code).empty) {
				if (board.get(code).marble.playerNumber == playersTurn) {
					if(first.equals(code) || second.equals(code)) {
						board.get(first).marble.setFill(Color.ORANGE);
						board.get(second).marble.setFill(Color.YELLOW);
						selected = true;
					}
					else if(!board.get(code).adjacent(second) || !GameData.rows.sameRowThree(first, second, code)) {
						resetSelection(code, board);
					}
					else {
						third = code;
						nrSelected++;
						selectedMarbles.add(code);
						selected = true;
						board.get(first).marble.setFill(Color.ORANGE);
						board.get(second).marble.setFill(Color.YELLOW);
						board.get(third).marble.setFill(Color.YELLOW);
					}
				}
			}
			//so if all of these are not possible, try if it is possible to move it to the place you want to move it to
			//if it's not, then it will automatically reset
			else{
				if (selected && board.get(code).adjacent(first)) {
					if (!board.get(code).empty) {
						if (board.get(code).marble.playerNumber == playersTurn) {
							resetSelection(code, board);
							}
						else {
							moveTo = code;
						}
						}
					else {
						moveTo = code;
					}
				}	
				else if (!board.get(code).empty && board.get(code).marble.playerNumber ==playersTurn) {
					resetSelection(code, board);
				}
			}
			
			if(moveTo != null && Board.hash.contains(moveTo)) {
				GameMethods.coloursBackToNormal(board);
				move(board);
			}
			if (board.equals(Board.hashBoard)) {
				GameGui.player_text.setText(String.valueOf(playersTurn));
			}

		}
	
		public void resetSelection(String code, Hashtable<String, Hexagon> board) {
			selectedMarbles.clear();
			GameMethods.coloursBackToNormal(board);
			first = code;
			second = null;
			third = null;
			board.get(code).marble.setFill(Color.PURPLE);
			selectedMarbles.add(code);
			nrSelected = 1;
		}
		
		public void move(Hashtable<String, Hexagon> board) {
			//check if valid -> if not, reset, else: perform movement, change player, resetmove
			
			if (nrSelected == 1) {
				if(validMoveOne(board, first, moveTo)) {
					//System.out.println(Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
					performMovementOne(board);
					moveForAll(board);
				}
			}
			else if(nrSelected ==2) {
				if(validMoveTwo(board, first, second, moveTo)) {
					performMovementTwo(board);
					moveForAll(board);
				}
			}
			else if(nrSelected ==3) {
				if(validMoveThree(board, first, second, third, moveTo)) {
					performMovementThree(board);
					moveForAll(board);
				}
			}
		}
		
		public void moveForAll(Hashtable<String, Hexagon> board) {
			if (board.equals(Board.hashBoard) && !adding) {
				GameMethods.gameFinished();
				playersTurn = GameMethods.changePlayer(playersTurn);
				GameData.tb.add();
				pushed = false;
				if (Move.player1AI == false && (this.greedy || GameData.numberPlayers ==3)) {
					checkAI();
				}
			}
			resetMove();
		}
		
	//automatically perform the move for the ai -> create tree, search and perform the move!!
	public static void checkAI()  {
		if (playersTurn ==1 && player1AI || playersTurn ==2 && player2AI || playersTurn ==3 && player3AI) {
			performAI();
		}
	}
	
	public static void performAI() {
		GameState state = new GameState(BoardMethods.copyHashBoard(Board.hashBoard),GameMethods.changeBack(playersTurn));
		if (greedy || GameData.numberPlayers ==3 || greedyPlayer2) {
			System.out.println("1 layer");
			PerformAIAction.createGameTree(state, 1);
			AI.PerformAIAction.perform(true);
		}
		else {
			System.out.println("2 layers");
			PerformAIAction.createGameTree(state, 2);
			AI.PerformAIAction.perform(greedy);
		}
		
	}
	
	//up until here, it's cleaned up
	
	//moves one single marble
	public void performMovementOne(Hashtable<String, Hexagon> board) {
		//System.out.println("move one");
		Marble moving = board.get(first).marble;
		board.get(first).setEmpty();
		board.get(moveTo).setFull(moving);
		moving.setLocationKey(moveTo);
		moving.updateLocation(board);
	
	}
	
	public void performMovementTwo(Hashtable<String, Hexagon> board) {
		//System.out.println("move two");
		
		//if it is moves sideways, then it can never push another marble
		if(GameData.rows.sideways(first, second, moveTo)) {
			moveSideways(board);
		}
		else if(board.get(moveTo).empty) {
			//System.out.println("moving");
			Marble moving = board.get(second).marble;
			board.get(second).setEmpty();
			board.get(moveTo).setFull(moving);
			moving.setLocationKey(moveTo);
			moving.updateLocation(board);
		}
		else if (board.get(moveTo).marble.playerNumber != playersTurn){
			String newHex = GameData.rows.adjacentDirection(moveTo, GameData.rows.direction(first, moveTo));
			if(board.containsKey(newHex)) {
				if (board.get(newHex).empty){
					doPushOne(board); 
				}
			}
			else {
				doPushOne(board);
			}
			
		}
		
	}
	
	public void performMovementThree(Hashtable<String, Hexagon> board) {
		//System.out.println("moveThree");
		
		String newHex = GameData.rows.adjacentDirection(moveTo, GameData.rows.direction(first, moveTo));
		//if it is moves sideways, then it can never push another marble
		if(GameData.rows.sideways(first, second, moveTo)) {
			moveSideways(board);
		}
		else if(board.get(moveTo).empty) {
			//System.out.println("movement for 3");
			Marble moving=board.get(third).marble;
			board.get(third).setEmpty();
			board.get(moveTo).setFull(moving);
			moving.setLocationKey(moveTo);
			moving.updateLocation(board);
		}
		else if (board.get(moveTo).marble.playerNumber != playersTurn){
			
			if (board.containsKey(newHex)){
				if (board.get(newHex).empty){
					doPushOne(board);
				}
				else if(board.get(newHex).marble.playerNumber != playersTurn) {
				String newnewHex = GameData.rows.adjacentDirection(newHex, GameData.rows.direction(first, moveTo));
					if (board.containsKey(newnewHex)){
							if(board.get(newnewHex).empty) {
								doPushTwo(board);
							}
					}
					else {
						doPushTwo(board);
					}
				}
			}
			else {
				doPushOne(board);
			}
	}
	}
	
	public void moveSideways(Hashtable<String, Hexagon> board) {
		int direction = GameData.rows.direction(first, moveTo);
		//System.out.println("direction is " + direction);
		char letterFirst = first.charAt(0);
		char letterSecond = second.charAt(0);
		
		
		String letterFirstSt = first.substring(0,1);
		String letterSecondSt = second.substring(0,1);
		
		
		String numberFirst = first.substring(1);
		String numberSecond = second.substring(1);
		
		
		int numberOne = Integer.parseInt(numberFirst);
		int numberTwo = Integer.parseInt(numberSecond);
		
		//when the value is one bigger
		int numberOnePlus = numberOne + 1;
		int numberTwoPlus = numberTwo + 1;
		
		char letterOnePlus = (char) (letterFirst +1);
		char letterTwoPlus = (char) (letterSecond +1);
		
		String letterOnePlusSt = Character.toString(letterOnePlus);
		String letterTwoPlusSt = Character.toString(letterTwoPlus);
		
		
		//when the value is one smaller
		int numberOneMinus = numberOne - 1;
		int numberTwoMinus = numberTwo - 1;
		
		char letterOneMinus = (char) (letterFirst -1);
		char letterTwoMinus = (char) (letterSecond -1);
		
		String letterOneMinusSt = Character.toString(letterOneMinus);
		String letterTwoMinusSt = Character.toString(letterTwoMinus);
		
		//set the marbles
		Marble firstMar = board.get(first).marble;
		Marble secondMar = board.get(second).marble;
		
		String keyOne =null;
		String keyTwo =null;
		String keyThree =null;
		
		if (direction ==1) {
			keyOne = letterFirstSt + numberOneMinus;
			keyTwo = letterSecondSt + numberTwoMinus;
			
			
		}
		
		else if (direction ==2) {
			keyOne = letterOnePlusSt + numberOne;
			keyTwo = letterTwoPlusSt + numberTwo;
			
		}
		
		else if(direction ==3) {
			keyOne = letterOnePlusSt + numberOnePlus;
			keyTwo = letterTwoPlusSt + numberTwoPlus;
			
		}
		else if (direction ==4) {
			keyOne = letterFirstSt + numberOnePlus;
			keyTwo = letterSecondSt + numberTwoPlus;
			
		}
		else if (direction ==5) {
			keyOne = letterOneMinusSt + numberOne;
			keyTwo = letterTwoMinusSt + numberTwo;
			
			
		}
		else if (direction ==6) {
			keyOne = letterOneMinusSt + numberOneMinus;
			keyTwo = letterTwoMinusSt + numberTwoMinus;
			
		}
		
		if (third != null) {
			char letterThird = third.charAt(0);
			String letterThirdSt = third.substring(0,1);
			String numberThird = third.substring(1);
			int numberThree = Integer.parseInt(numberThird);
			
			int numberThreePlus = numberThree + 1;
			char letterThreePlus = (char) (letterThird + 1);
			String letterThreePlusSt = Character.toString(letterThreePlus);
			int numberThreeMinus = numberThree -1;
			char letterThreeMinus = (char) (letterThird -1);
			String letterThreeMinusSt = Character.toString(letterThreeMinus);
			
			Marble thirdMar = board.get(third).marble;
			
			if(direction ==1) {
				keyThree = letterThirdSt + numberThreeMinus;
			}
			if (direction ==2) {
				keyThree = letterThreePlusSt + numberThree;
			}
			if (direction ==3) {
				keyThree = letterThreePlusSt + numberThreePlus;
			}
			if (direction ==4) {
				keyThree = letterThirdSt + numberThreePlus;
			}
			if (direction ==5){
				keyThree = letterThreeMinusSt + numberThree;
			}
			if (direction ==6) {
				keyThree = letterThreeMinusSt + numberThreeMinus;
			}
			
			
			board.get(third).setEmpty();
			board.get(keyThree).setFull(thirdMar);
			thirdMar.setLocationKey(keyThree);
			thirdMar.updateLocation(board);
		}
		
		board.get(first).setEmpty();
		board.get(keyOne).setFull(firstMar);
		firstMar.setLocationKey(keyOne);
		firstMar.updateLocation(board);
		
		board.get(second).setEmpty();
		board.get(keyTwo).setFull(secondMar);
		secondMar.setLocationKey(keyTwo);
		secondMar.updateLocation(board);
		
	}
	
	//push one marble
	public void doPushOne(Hashtable<String, Hexagon> board) {
		if (third== null) {
			Marble moving= board.get(second).marble;
			Marble removing = board.get(moveTo).marble;
		
			board.get(second).setEmpty();
			board.get(moveTo).setEmpty();
			board.get(moveTo).setFull(moving);
		
			String keyAdj = GameData.rows.adjacentDirection(moveTo, GameData.rows.direction(first, moveTo));
		
			if(board.containsKey(keyAdj)) {
				board.get(keyAdj).setFull(removing);
				removing.setLocationKey(keyAdj);
				removing.updateLocation(board);
				//System.out.println("pushed");
			}
			else {
				if (board.equals(Board.hashBoard) && !adding) {
				GameData.score[playersTurn-1]++;
				GameGui.MainScene.getChildren().remove(removing);
				Board.boardMarbles.storage.remove(removing);
				GameGui.Screen.getChildren().remove(removing);
				GameGui.pp.getChildren().remove(removing);
					if(playersTurn==1) {
						point++;
						//System.out.println(playersTurn + " gets a point");
					}else if (playersTurn ==2) {
						point2++;
					}
					else {
						point3++;
					}
					
				}
				else {
					pushed = true;
				}
			}
			moving.setLocationKey(moveTo);
			moving.updateLocation(board);
		}
		else {
			Marble moving=board.get(third).marble;
			Marble removing = board.get(moveTo).marble;
		
			board.get(third).setEmpty();
			board.get(moveTo).setEmpty();
			board.get(moveTo).setFull(moving);
		
			String keyAdj = GameData.rows.adjacentDirection(moveTo, GameData.rows.direction(first, moveTo));
		
			if(board.containsKey(keyAdj)) {
				board.get(keyAdj).setFull(removing);
				removing.setLocationKey(keyAdj);
				removing.updateLocation(board);
				//System.out.println("pushed");
			}
			else {
				if (board.equals(Board.hashBoard) && !adding) {
					GameData.score[playersTurn-1]++;
					GameGui.MainScene.getChildren().remove(removing);
					Board.boardMarbles.storage.remove(removing);
					GameGui.Screen.getChildren().remove(removing);
					GameGui.pp.getChildren().remove(removing);
					if(playersTurn==1) {
						point++;
						//System.out.println(playersTurn + " gets a point");
					}else if (playersTurn ==2){
						point2++;
					}
					else {
						point3++;
					}
					removing.setFill(Color.PINK);
					//System.out.println("out of board");
				}
			}

			moving.setLocationKey(moveTo);
			moving.updateLocation(board);
		}

	}


	//push two marbles
	public void doPushTwo(Hashtable<String, Hexagon> board) {
		Marble moving=board.get(third).marble;
		Marble removing = board.get(moveTo).marble;
		
		board.get(third).setEmpty();
		board.get(moveTo).setEmpty();
		board.get(moveTo).setFull(moving);
		
		String keyAdj = GameData.rows.adjacentDirection(moveTo, GameData.rows.direction(first, moveTo));
		String keyAdjTwo = GameData.rows.adjacentDirection(keyAdj, GameData.rows.direction(first, moveTo));
		//System.out.println("keyAdjTwo is " + keyAdjTwo);
		
		
		//as it's already determined it is possible to move it, doesn't need to be checked
		if(board.containsKey(keyAdjTwo)) {
			board.get(keyAdjTwo).setFull(removing);
			removing.setLocationKey(keyAdjTwo);
			removing.updateLocation(board);	
			//System.out.println("pushed 2");
		}
		else {
			if (board.equals(Board.hashBoard) && !adding) {
				GameData.score[playersTurn-1]++;
				//System.out.println(playersTurn + " gets a point");
				Board.boardMarbles.storage.remove(removing);
				GameGui.MainScene.getChildren().remove(removing);
				GameGui.Screen.getChildren().remove(removing);
				GameGui.pp.getChildren().remove(removing);
				if(playersTurn==1) {
					point++;
					//System.out.println(playersTurn + " gets a point");
				}else if (playersTurn ==2){
					point2++;
				}
				else {
					point3++;
				}
				//System.out.println("out of board");
				removing.setFill(Color.PINK);
			}
			else {
				pushed = true;
			}
		}
		
		moving.setLocationKey(moveTo);
		moving.updateLocation(board);
	}
	
	//resets the move
	public static void resetMove() {
		first = null;
		second = null;
		third = null;
		moveTo = null;
		selected = false;
		nrSelected = 0;
		selectedMarbles.clear();
		pushed = false;
	}
	
	//test if it is possible to move one, else it resets the move
	public static boolean validMoveOne(Hashtable<String, Hexagon> board, String first, String moveTo) {
		if(board.containsKey(first) && board.containsKey(moveTo)) {
			//System.out.println(board.get(moveTo).empty);
			//System.out.println("check");
			if (board.get(first).adjacent(moveTo) && board.get(moveTo).empty){
				//System.out.println("check2");
				return true;
			}
			else {
				//System.out.println("not empty");
				resetMove();
				return false;
			}
		}
		else {
			//System.out.println("does not exist");
			resetMove();
			return false;
		}
	}
	
	//should be done
	public static boolean validMoveTwo(Hashtable<String, Hexagon> board, String first, String second, String moveTo) {
		if (board.containsKey(first) && board.containsKey(second) && board.containsKey(moveTo)) {
			if (board.get(first).adjacent(second) && board.get(first).adjacent(moveTo)) {
			String newHex = GameData.rows.adjacentDirection(moveTo, GameData.rows.direction(first, moveTo));
			
			if(board.get(first).adjacent(second) && board.get(first).adjacent(moveTo)) {
			//if it needs to move sideways and if there are two free space where they are needed, the move is valid
				if(GameData.rows.sideways(first, second, moveTo)) {
					//System.out.println("sideways is true");
					if (GameData.rows.twoFree(first, second, moveTo, board)) {
						return true;
					}
					else {
						//System.out.println("did not move");
						//System.out.println("can't move sideways");
						resetMove();
						return false;
					}
				}
				else if (board.get(moveTo).empty && GameData.rows.direction(moveTo, first) == GameData.rows.direction(first, second)) {
					//System.out.println("move to is empty");
					return true;
				}
				else if (board.get(moveTo).marble.playerNumber != playersTurn && GameData.rows.direction(moveTo, first) == GameData.rows.direction(first, second)) {
					if (board.containsKey(newHex)) {
						//System.out.println("push 1 with 2");
						if (board.get(newHex).empty){
							return true;
						}
						else {
							resetMove();
							return false;
						}
					}	
					else {
						//System.out.println("push out of board");
						return true;
					}
			}
		
			//System.out.println("did not move");
			resetMove();
			return false;
			}
			else {
				resetMove();
				return false;
			}
		}
		else {
			resetMove();
			return false;
		}
		}
		resetMove();
		return false;
	}
	
	//done
	public static boolean validMoveThree(Hashtable<String, Hexagon> board, String first, String second, String third, String moveTo) {
		if (board.containsKey(first) && board.containsKey(second) && board.containsKey(moveTo) && board.containsKey(third)) {
			if (board.get(first).adjacent(second) && board.get(first).adjacent(moveTo) && board.get(second).adjacent(third)) {
			String newHex = GameData.rows.adjacentDirection(moveTo, GameData.rows.direction(first, moveTo));
			String newnewHex = GameData.rows.adjacentDirection(newHex, GameData.rows.direction(first, moveTo));
		
			if(GameData.rows.sideways(first, second, moveTo)) {
				if (GameData.rows.threeFree(first, second, third, moveTo, board)) {
					return true;
				}
				else {
					//System.out.println("did not move");
					resetMove();
					return false;
				}
			}
			else if (board.get(moveTo).empty && GameData.rows.direction(moveTo, first) == GameData.rows.direction(first, second) && GameData.rows.direction(first, second) == GameData.rows.direction(second, third)) {
				//System.out.println("move to is empty");
				return true;
			}
			//can push one
			else if (board.get(moveTo).marble.playerNumber != playersTurn  && GameData.rows.direction(moveTo, first) == GameData.rows.direction(first, second) && GameData.rows.direction(first, second) == GameData.rows.direction(second, third)){
				if (board.containsKey(newHex)){
					if (board.get(newHex).empty){
						return true; 
					}
					else if(board.get(newHex).marble.playerNumber != playersTurn) {
						
							if (board.containsKey(newnewHex)){
								if(board.get(newnewHex).empty) {
									return true; 
								}
							}
						else {
						return true;
						}
					}
				}
				else {
					return true;
				}
			}
			else {
				resetMove();
				return false;
			}
		}
	}
		//System.out.println("did not move");
		resetMove();
		return false;
	}
	
}
