package src;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Hashtable;

public class Move {
	//first, second, and third marble code that need to be moved (the hexagon in the hashboard contains the marbles of the same code- which can be removed and added quickly)
	public String first;
	public String second;
	public String third;
	int point= 0;
	int point2= 0;
	int point3 = 0;
	
	//following arraylist can be used to find the ones that need to be moved:
	public ArrayList<String> selectedMarbles = new ArrayList<String>();
	public int nrSelected = 0; //keep track of how many marbles are selected

	//code of the hexagon the marble needs to be moved to
	public String moveTo;
	
	//when the selection process is done, this will be true:
	private boolean selected = false;
	
	//keep track of the player that needs to move a marble
	public int playersTurn = 1;

	public Move() {
		first = null;
		second = null;
		third = null;
		moveTo = null;
	}
	
	//select marbles
	public void select(String code, Hashtable<String, Hexagon> board) {
		//System.out.println("select");
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
					//System.out.println("total = 1");
					board.get(code).marble.setFill(Color.ORANGE);
				}
				else if(!board.get(code).adjacent(board.get(first))) {
					selectedMarbles.clear();
					coloursBackToNormal(board);
					first = code;
					board.get(code).marble.setFill(Color.PURPLE);
					selectedMarbles.add(code);
				}
				else{
					second = code;
					board.get(code).marble.setFill(Color.AQUAMARINE);
					nrSelected++;
					selectedMarbles.add(code);
					//System.out.println("selected two");
				}
			}
		}
		//quite similar to the second selection - checks whether it is adjacent to the second one, if not, then it becomes the first in the selection
		//if  this is done, selected becomes true automatically
		else if(third == null && !selected && !board.get(code).empty) {
			if (Board.hashBoard.get(code).marble.playerNumber == playersTurn) {
				if(first.equals(code) || second.equals(code)) {
					board.get(first).marble.setFill(Color.ORANGE);
					board.get(second).marble.setFill(Color.YELLOW);
					//System.out.println("total = 2");
					selected = true;
				}
				else if(!board.get(code).adjacent(board.get(second)) || !Board.rows.sameRowThree(board.get(first), board.get(second), board.get(code))) {
					selectedMarbles.clear();
					coloursBackToNormal(board);
					first = code;
					board.get(first).marble.setFill(Color.PURPLE);
					second = null;
					nrSelected = 1;
					selectedMarbles.add(code);
					
				}
				else {
					third = code;
					nrSelected++;
					selectedMarbles.add(code);
					selected = true;
					board.get(first).marble.setFill(Color.ORANGE);
					board.get(second).marble.setFill(Color.YELLOW);
					board.get(third).marble.setFill(Color.YELLOW);
					//System.out.println("selected three");
				}
			}
		}
		//so if all of these are not possible, try if it is possible to move it to the place you want to move it to
		//if it's not, then it will automatically reset
		else{
			if (selected && board.get(code).adjacent(board.get(first))) {
				if (!board.get(code).empty) {
					if (board.get(code).marble.playerNumber == playersTurn) {
							selectedMarbles.clear();
							coloursBackToNormal(board);
							selected = false;
							first = code;
							board.get(first).marble.setFill(Color.PURPLE);
							second = null;
							third = null;
							nrSelected = 1;
							selectedMarbles.add(code);
						}
					else {
						moveTo = code;
						//System.out.println("moveto");
					}
					}
				else {
					moveTo = code;
					//System.out.println("moveto");
				}
			}	
			else if (!board.get(code).empty && board.get(code).marble.playerNumber ==playersTurn) {
				selectedMarbles.clear();
				coloursBackToNormal(board);
				selected = false;
				first = code;
				board.get(first).marble.setFill(Color.PURPLE);
				second = null;
				third = null;
				nrSelected = 1;
				selectedMarbles.add(code);
			}
		}
		
		if(moveTo != null && Board.hash.contains(moveTo)) {
			coloursBackToNormal(board);
			move(board);
		}
		if (board.equals(Board.hashBoard)) {
			GameGui.player_text.setText(String.valueOf(playersTurn));
		}

	}
	
	public void coloursBackToNormal(Hashtable<String, Hexagon> board) {
		if(board.get(first).marble.playerNumber == 1) {
			board.get(first).marble.setFill(Color.BLACK);
			if(second != null) {
				board.get(second).marble.setFill(Color.BLACK);
			}
			if(third != null) {
				board.get(third).marble.setFill(Color.BLACK);
			}
		}
		if(board.get(first).marble.playerNumber == 2) {
			board.get(first).marble.setFill(Color.GRAY);
			if(second != null) {
				board.get(second).marble.setFill(Color.GRAY);
			}
			if(third!=null) {
				board.get(third).marble.setFill(Color.GRAY);
			}
		}
		if (Board.numberPlayers ==3) {
			if(board.get(first).marble.playerNumber == 3) {
				board.get(first).marble.setFill(Color.DARKGREEN);
				if(second != null) {
					board.get(second).marble.setFill(Color.DARKGREEN);
				}
				if(third!=null) {
					board.get(third).marble.setFill(Color.DARKGREEN);
				}
			}
		}
	}
	
	public void move(Hashtable<String, Hexagon> board) {
		//check if valid -> if not, reset, else: perform movement, change player, resetmove
		//System.out.println("move");
		if (nrSelected == 1) {
			if(validMoveOne(board)) {
				//System.out.println(Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
				performMovementOne(board);
				if (board.equals(Board.hashBoard)) {
					changePlayer();
					Board.tb.add();
				}
				resetMove();
				
			}
		}
		else if(nrSelected ==2) {
			if(validMoveTwo(board)) {
				performMovementTwo(board);
				if (board.equals(Board.hashBoard)) {
					gameFinished();
					changePlayer();
					Board.tb.add();
				}
				resetMove();
			}
		}
		else if(nrSelected ==3) {
			if(validMoveThree(board)) {
				performMovementThree(board);
				if (board.equals(Board.hashBoard)) {
					gameFinished();
					changePlayer();
					Board.tb.add();
				}
				resetMove();
			}
		}
	}
	
	//changes who is playing
	public void changePlayer() {
		if (playersTurn ==1) {
			playersTurn = 2;
		}
		else {
			if (Board.numberPlayers == 2 || Board.numberPlayers == 3 && playersTurn ==3) {
				playersTurn = 1;
			}
			else if (Board.numberPlayers == 3 && playersTurn ==2){
				if (playersTurn == 2) {
					playersTurn = 3;
				}
				
			}
		}
	}
	
	//moves one single marble
	public void performMovementOne(Hashtable<String, Hexagon> board) {
		Marble moving = Board.hashBoard.get(first).marble;
		board.get(first).setEmpty();
		board.get(moveTo).setFull(moving);
		moving.setLocationKey(moveTo);
		moving.updateLocation(board);
	}
	
	public void performMovementTwo(Hashtable<String, Hexagon> board) {
		//if it is moves sideways, then it can never push another marble
		if(Board.rows.sideways(board.get(first), board.get(second), board.get(moveTo))) {
			moveSideways(board);
		}
		else if(board.get(moveTo).empty) {
			//System.out.println("moving");
			Marble moving = Board.hashBoard.get(second).marble;
			board.get(second).setEmpty();
			board.get(moveTo).setFull(moving);
			moving.setLocationKey(moveTo);
			moving.updateLocation(board);
		}
		else if (board.get(moveTo).marble.playerNumber != playersTurn){
			String newHex = Board.rows.adjacentDirection(board.get(moveTo), Board.rows.direction(board.get(first), board.get(moveTo)));
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
		String newHex = Board.rows.adjacentDirection(board.get(moveTo), Board.rows.direction(board.get(first), board.get(moveTo)));
		//if it is moves sideways, then it can never push another marble
		if(Board.rows.sideways(board.get(first), board.get(second), board.get(moveTo))) {
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
				String newnewHex = Board.rows.adjacentDirection(board.get(newHex), Board.rows.direction(board.get(first), board.get(moveTo)));
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
		int direction = Board.rows.direction(board.get(first), board.get(moveTo));
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
		
			String keyAdj = Board.rows.adjacentDirection(board.get(moveTo), Board.rows.direction(board.get(first), board.get(moveTo)));
		
			if(board.containsKey(keyAdj)) {
				board.get(keyAdj).setFull(removing);
				removing.setLocationKey(keyAdj);
				removing.updateLocation(board);
				//System.out.println("pushed");
			}
			else {
				if (board.equals(Board.hashBoard)) {
					Board.score[playersTurn-1]++;
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
					//add what should happen when we're working on the ai!! so update the gamestate
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
		
			String keyAdj = Board.rows.adjacentDirection(board.get(moveTo), Board.rows.direction(board.get(first), board.get(moveTo)));
		
			if(board.containsKey(keyAdj)) {
				board.get(keyAdj).setFull(removing);
				removing.setLocationKey(keyAdj);
				removing.updateLocation(board);
				//System.out.println("pushed");
			}
			else {
				if (board.equals(Board.hashBoard)) {
					Board.score[playersTurn-1]++;
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
		
		String keyAdj = Board.rows.adjacentDirection(board.get(moveTo), Board.rows.direction(board.get(first), board.get(moveTo)));
		String keyAdjTwo = Board.rows.adjacentDirection(board.get(keyAdj), Board.rows.direction(board.get(first), board.get(moveTo)));
		//System.out.println("keyAdjTwo is " + keyAdjTwo);
		
		
		//as it's already determined it is possible to move it, doesn't need to be checked
		if(board.containsKey(keyAdjTwo)) {
			board.get(keyAdjTwo).setFull(removing);
			removing.setLocationKey(keyAdjTwo);
			removing.updateLocation(board);	
			//System.out.println("pushed 2");
		}
		else {
			if (board.equals(Board.hashBoard)) {
				Board.score[playersTurn-1]++;
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
		}
		
		moving.setLocationKey(moveTo);
		moving.updateLocation(board);
	}
	
	//resets the move
	public void resetMove() {
		this.first = null;
		this.second = null;
		this.third = null;
		this.moveTo = null;
		this.selected = false;
		this.nrSelected = 0;
		this.selectedMarbles.clear();
	}
	
	//test if it is possible to move one, else it resets the move
	public boolean validMoveOne(Hashtable<String, Hexagon> board) {
		if (board.get(first).adjacent(board.get(moveTo)) && board.get(moveTo).empty){
			return true;
		}
		else {
			resetMove();
			return false;
		}
	}
	
	//should be done
	public boolean validMoveTwo(Hashtable<String, Hexagon> board) {
		String newHex = Board.rows.adjacentDirection(board.get(moveTo), Board.rows.direction(board.get(first), board.get(moveTo)));
		//System.out.println("the new hex is " + newHex);
		//if it needs to move sideways and if there are two free space where they are needed, the move is valid
		if(Board.rows.sideways(board.get(first), board.get(second), board.get(moveTo))) {
			//System.out.println("sideways is true");
			if (Board.rows.twoFree(board.get(first), board.get(second), board.get(moveTo), board)) {
				return true;
			}
			else {
				//System.out.println("did not move");
				resetMove();
			}
		}
		else if (board.get(moveTo).empty) {
			//System.out.println("move to is empty");
			return true;
		}
		else if (board.get(moveTo).marble.playerNumber != playersTurn) {
			if (board.containsKey(newHex)) {
				if (board.get(newHex).empty){
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return true;
			}
		}
		
		//System.out.println("did not move");
		resetMove();
		return false;
	}
	
	//done
	public boolean validMoveThree(Hashtable<String, Hexagon> board) {
		String newHex = Board.rows.adjacentDirection(board.get(moveTo), Board.rows.direction(board.get(first), board.get(moveTo)));
		
		if(Board.rows.sideways(board.get(first), board.get(second), board.get(moveTo))) {
			if (Board.rows.threeFree(board.get(first), board.get(second), board.get(third), board.get(moveTo), board)) {
				return true;
			}
			else {
				//System.out.println("did not move");
				resetMove();
			}
		}
		else if (board.get(moveTo).empty) {
			//System.out.println("move to is empty");
			return true;
		}
		//can push one
		else if (board.get(moveTo).marble.playerNumber != playersTurn){
			
			if (board.containsKey(newHex)){
				if (board.get(newHex).empty){
					return true; 
				}
				else if(board.get(newHex).marble.playerNumber != playersTurn) {
				String newnewHex = Board.rows.adjacentDirection(board.get(newHex), Board.rows.direction(board.get(first), board.get(moveTo)));
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
		//System.out.println("did not move");
		resetMove();
		return false;
	}
	public int getScore1() {
		return point;
	}
	public int getScore2() {
		return point2;
	}
	
	public int getScore3() {
		return point3;
	}
	
	public int player() {
		return playersTurn;
	}
	public void gameFinished() {
		GameGui.score_text1.setText(String.valueOf(point));
		GameGui.score_text2.setText(String.valueOf(point2));
		if (Board.numberPlayers == 3) {
			GameGui.score_text3.setText(String.valueOf(point3));
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText("Winner is:");
		if ( getScore1() == 6){
			String s ="Game over, Player 1 won!" ;
			alert.setContentText(s);
			alert.show();
			GameGui.winner_text.setText("Game over, Player 1 won!");
			//System.out.println("DONE");
		
		}
		if ( getScore2() == 6) {
			
			String s ="Game over, Player 2 won!" ;
			alert.setContentText(s);
			alert.show();
			GameGui.winner_text.setText("Game over, Player 2 won!");
			//System.out.println("DONE");
		}
		if (Board.numberPlayers ==3) {
			if (getScore3() == 6) {
				String s ="Game over, Player 3 won!" ;
				alert.setContentText(s);
				alert.show();
				GameGui.winner_text.setText("Game over, Player 3 won!");
			}
		}
		
		alert.setOnCloseRequest( event ->
	    {
	        //System.out.println("CLOSING");
	        System.exit(0);
	    });
	}
	
	
}
