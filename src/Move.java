package src;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import java.util.ArrayList;

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
	public void select(String code) {
		//System.out.println("select");
		//check whether a marble from the player is selected, then set that as a code and add it to the selection:
		if (first == null && !Board.hashBoard.get(code).empty) {
			if (Board.hashBoard.get(code).marble.playerNumber == playersTurn) {
				//System.out.println("selected 1");
				Board.hashBoard.get(code).marble.setFill(Color.PURPLE);
				first = code;
				nrSelected++;
				selectedMarbles.add(code);
			}
		}
		//if the first and second are the same, end selection (you're done) it also checks whether they are adjacent
		//if not adjacent: the second selected marble becomes the first - else just add it to the selection
		else if(second == null  && !Board.hashBoard.get(code).empty && !selected) {
			if (Board.hashBoard.get(code).marble.playerNumber == playersTurn) {
				if (first.equals(code)) {
					selected = true;
					//System.out.println("total = 1");
					Board.hashBoard.get(code).marble.setFill(Color.ORANGE);
				}
				else if(!Board.hashBoard.get(code).adjacent(Board.hashBoard.get(first))) {
					selectedMarbles.clear();
					coloursBackToNormal();
					first = code;
					Board.hashBoard.get(code).marble.setFill(Color.PURPLE);
					selectedMarbles.add(code);
				}
				else{
					second = code;
					Board.hashBoard.get(code).marble.setFill(Color.AQUAMARINE);
					nrSelected++;
					selectedMarbles.add(code);
					//System.out.println("selected two");
				}
			}
		}
		//quite similar to the second selection - checks whether it is adjacent to the second one, if not, then it becomes the first in the selection
		//if  this is done, selected becomes true automatically
		else if(third == null && !selected && !Board.hashBoard.get(code).empty) {
			if (Board.hashBoard.get(code).marble.playerNumber == playersTurn) {
				if(first.equals(code) || second.equals(code)) {
					Board.hashBoard.get(first).marble.setFill(Color.ORANGE);
					Board.hashBoard.get(second).marble.setFill(Color.YELLOW);
					//System.out.println("total = 2");
					selected = true;
				}
				else if(!Board.hashBoard.get(code).adjacent(Board.hashBoard.get(second)) || !Board.rows.sameRowThree(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(code))) {
					selectedMarbles.clear();
					coloursBackToNormal();
					first = code;
					Board.hashBoard.get(first).marble.setFill(Color.PURPLE);
					second = null;
					nrSelected = 1;
					selectedMarbles.add(code);
					
				}
				else {
					third = code;
					nrSelected++;
					selectedMarbles.add(code);
					selected = true;
					Board.hashBoard.get(first).marble.setFill(Color.ORANGE);
					Board.hashBoard.get(second).marble.setFill(Color.YELLOW);
					Board.hashBoard.get(third).marble.setFill(Color.YELLOW);
					//System.out.println("selected three");
				}
			}
		}
		//so if all of these are not possible, try if it is possible to move it to the place you want to move it to
		//if it's not, then it will automatically reset
		else{
			if (selected && Board.hashBoard.get(code).adjacent(Board.hashBoard.get(first))) {
				if (!Board.hashBoard.get(code).empty) {
					if (Board.hashBoard.get(code).marble.playerNumber == playersTurn) {
							selectedMarbles.clear();
							coloursBackToNormal();
							selected = false;
							first = code;
							Board.hashBoard.get(first).marble.setFill(Color.PURPLE);
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
			else if (!Board.hashBoard.get(code).empty && Board.hashBoard.get(code).marble.playerNumber ==playersTurn) {
				selectedMarbles.clear();
				coloursBackToNormal();
				selected = false;
				first = code;
				Board.hashBoard.get(first).marble.setFill(Color.PURPLE);
				second = null;
				third = null;
				nrSelected = 1;
				selectedMarbles.add(code);
			}
		}
		
		if(moveTo != null) {
			coloursBackToNormal();
			move();
		}
		GameGui.player_text.setText(String.valueOf(playersTurn));

	}
	
	public void coloursBackToNormal() {
		if(Board.hashBoard.get(first).marble.playerNumber == 1) {
			Board.hashBoard.get(first).marble.setFill(Color.BLACK);
			if(second != null) {
				Board.hashBoard.get(second).marble.setFill(Color.BLACK);
			}
			if(third != null) {
				Board.hashBoard.get(third).marble.setFill(Color.BLACK);
			}
		}
		if(Board.hashBoard.get(first).marble.playerNumber == 2) {
			Board.hashBoard.get(first).marble.setFill(Color.GRAY);
			if(second != null) {
				Board.hashBoard.get(second).marble.setFill(Color.GRAY);
			}
			if(third!=null) {
				Board.hashBoard.get(third).marble.setFill(Color.GRAY);
			}
		}
		if (Board.numberPlayers ==3) {
			if(Board.hashBoard.get(first).marble.playerNumber == 3) {
				Board.hashBoard.get(first).marble.setFill(Color.DARKGREEN);
				if(second != null) {
					Board.hashBoard.get(second).marble.setFill(Color.DARKGREEN);
				}
				if(third!=null) {
					Board.hashBoard.get(third).marble.setFill(Color.DARKGREEN);
				}
			}
		}
	}
	
	public void move() {
		//check if valid -> if not, reset, else: perform movement, change player, resetmove
		//System.out.println("move");
		if (nrSelected == 1) {
			if(validMoveOne()) {
				//System.out.println(Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
				performMovementOne();
				changePlayer();
				resetMove();
			}
		}
		else if(nrSelected ==2) {
			if(validMoveTwo()) {
				performMovementTwo();
				gameFinished();
				changePlayer();
				resetMove();
			}
		}
		else if(nrSelected ==3) {
			if(validMoveThree()) {
				performMovementThree();
				gameFinished();
				changePlayer();
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
	public void performMovementOne() {
		Marble moving = Board.hashBoard.get(first).marble;
		Board.hashBoard.get(first).setEmpty();
		Board.hashBoard.get(moveTo).setFull(moving);
		moving.setLocationKey(moveTo);
		moving.updateLocation();
	}
	
	public void performMovementTwo() {
		//if it is moves sideways, then it can never push another marble
		if(Board.rows.sideways(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(moveTo))) {
			moveSideways();
		}
		else if(Board.hashBoard.get(moveTo).empty) {
			//System.out.println("moving");
			Marble moving = Board.hashBoard.get(second).marble;
			Board.hashBoard.get(second).setEmpty();
			Board.hashBoard.get(moveTo).setFull(moving);
			moving.setLocationKey(moveTo);
			moving.updateLocation();
		}
		else if (Board.hashBoard.get(moveTo).marble.playerNumber != playersTurn){
			String newHex = Board.rows.adjacentDirection(Board.hashBoard.get(moveTo), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
			if(Board.hashBoard.containsKey(newHex)) {
				if (Board.hashBoard.get(newHex).empty){
					doPushOne(); 
				}
			}
			else {
				doPushOne();
			}
			
		}
		
	}
	
	public void performMovementThree() {
		String newHex = Board.rows.adjacentDirection(Board.hashBoard.get(moveTo), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
		//if it is moves sideways, then it can never push another marble
		if(Board.rows.sideways(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(moveTo))) {
			moveSideways();
		}
		else if(Board.hashBoard.get(moveTo).empty) {
			//System.out.println("movement for 3");
			Marble moving=Board.hashBoard.get(third).marble;
			Board.hashBoard.get(third).setEmpty();
			Board.hashBoard.get(moveTo).setFull(moving);
			moving.setLocationKey(moveTo);
			moving.updateLocation();
		}
		else if (Board.hashBoard.get(moveTo).marble.playerNumber != playersTurn){
			
			if (Board.hashBoard.containsKey(newHex)){
				if (Board.hashBoard.get(newHex).empty){
					doPushOne();
				}
				else if(Board.hashBoard.get(newHex).marble.playerNumber != playersTurn) {
				String newnewHex = Board.rows.adjacentDirection(Board.hashBoard.get(newHex), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
					if (Board.hashBoard.containsKey(newnewHex)){
							if(Board.hashBoard.get(newnewHex).empty) {
								doPushTwo();
							}
					}
					else {
						doPushTwo();
					}
				}
			}
			else {
				doPushOne();
			}
	}
	}
	
	public void moveSideways() {
		int direction = Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo));
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
		Marble firstMar = Board.hashBoard.get(first).marble;
		Marble secondMar = Board.hashBoard.get(second).marble;
		
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
			
			Marble thirdMar = Board.hashBoard.get(third).marble;
			
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
			
			
			Board.hashBoard.get(third).setEmpty();
			Board.hashBoard.get(keyThree).setFull(thirdMar);
			thirdMar.setLocationKey(keyThree);
			thirdMar.updateLocation();
		}
		
		Board.hashBoard.get(first).setEmpty();
		Board.hashBoard.get(keyOne).setFull(firstMar);
		firstMar.setLocationKey(keyOne);
		firstMar.updateLocation();
		
		Board.hashBoard.get(second).setEmpty();
		Board.hashBoard.get(keyTwo).setFull(secondMar);
		secondMar.setLocationKey(keyTwo);
		secondMar.updateLocation();
		
	}
	
	//push one marble
	public void doPushOne() {
		if (third== null) {
			Marble moving=Board.hashBoard.get(second).marble;
			Marble removing = Board.hashBoard.get(moveTo).marble;
		
			Board.hashBoard.get(second).setEmpty();
			Board.hashBoard.get(moveTo).setEmpty();
			Board.hashBoard.get(moveTo).setFull(moving);
		
			String keyAdj = Board.rows.adjacentDirection(Board.hashBoard.get(moveTo), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
		
			if(Board.hashBoard.containsKey(keyAdj)) {
				Board.hashBoard.get(keyAdj).setFull(removing);
				removing.setLocationKey(keyAdj);
				removing.updateLocation();
				//System.out.println("pushed");
			}
			else {
				Board.score[playersTurn-1]++;
				GameGui.MainScene.getChildren().remove(removing);
				Board.boardMarbles.storage.remove(removing);
				GameGui.Screen.getChildren().remove(removing);
				GameGui.pp.getChildren().remove(removing);
				removing.setFill(Color.PINK);
				if(playersTurn==1) {
					point++;
					//System.out.println(playersTurn + " gets a point");
				}else if (playersTurn ==2) {
					point2++;
				}
				else {
					point3++;
				}
				
				//System.out.println("out of board");
			}
			moving.setLocationKey(moveTo);
			moving.updateLocation();
		}
		else {
			Marble moving=Board.hashBoard.get(third).marble;
			Marble removing = Board.hashBoard.get(moveTo).marble;
		
			Board.hashBoard.get(third).setEmpty();
			Board.hashBoard.get(moveTo).setEmpty();
			Board.hashBoard.get(moveTo).setFull(moving);
		
			String keyAdj = Board.rows.adjacentDirection(Board.hashBoard.get(moveTo), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
		
			if(Board.hashBoard.containsKey(keyAdj)) {
				Board.hashBoard.get(keyAdj).setFull(removing);
				removing.setLocationKey(keyAdj);
				removing.updateLocation();
				//System.out.println("pushed");
			}
			else {
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

			moving.setLocationKey(moveTo);
			moving.updateLocation();
			
		}

	}


	//push two marbles
	public void doPushTwo() {
		Marble moving=Board.hashBoard.get(third).marble;
		Marble removing = Board.hashBoard.get(moveTo).marble;
		
		Board.hashBoard.get(third).setEmpty();
		Board.hashBoard.get(moveTo).setEmpty();
		Board.hashBoard.get(moveTo).setFull(moving);
		
		String keyAdj = Board.rows.adjacentDirection(Board.hashBoard.get(moveTo), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
		String keyAdjTwo = Board.rows.adjacentDirection(Board.hashBoard.get(keyAdj), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
		//System.out.println("keyAdjTwo is " + keyAdjTwo);
		
		
		//as it's already determined it is possible to move it, doesn't need to be checked
		if(Board.hashBoard.containsKey(keyAdjTwo)) {
			Board.hashBoard.get(keyAdjTwo).setFull(removing);
			removing.setLocationKey(keyAdjTwo);
			removing.updateLocation();	
			//System.out.println("pushed 2");
		}
		else {
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
		
		moving.setLocationKey(moveTo);
		moving.updateLocation();
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
	public boolean validMoveOne() {
		if (Board.hashBoard.get(first).adjacent(Board.hashBoard.get(moveTo)) && Board.hashBoard.get(moveTo).empty){
			return true;
		}
		else {
			resetMove();
			return false;
		}
	}
	
	//should be done
	public boolean validMoveTwo() {
		String newHex = Board.rows.adjacentDirection(Board.hashBoard.get(moveTo), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
		//System.out.println("the new hex is " + newHex);
		//if it needs to move sideways and if there are two free space where they are needed, the move is valid
		if(Board.rows.sideways(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(moveTo))) {
			//System.out.println("sideways is true");
			if (Board.rows.twoFree(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(moveTo))) {
				return true;
			}
			else {
				//System.out.println("did not move");
				resetMove();
			}
		}
		else if (Board.hashBoard.get(moveTo).empty) {
			//System.out.println("move to is empty");
			return true;
		}
		else if (Board.hashBoard.get(moveTo).marble.playerNumber != playersTurn) {
			if (Board.hashBoard.containsKey(newHex)) {
				if (Board.hashBoard.get(newHex).empty){
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
	public boolean validMoveThree() {
		String newHex = Board.rows.adjacentDirection(Board.hashBoard.get(moveTo), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
		
		if(Board.rows.sideways(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(moveTo))) {
			if (Board.rows.threeFree(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(third), Board.hashBoard.get(moveTo))) {
				return true;
			}
			else {
				//System.out.println("did not move");
				resetMove();
			}
		}
		else if (Board.hashBoard.get(moveTo).empty) {
			//System.out.println("move to is empty");
			return true;
		}
		//can push one
		else if (Board.hashBoard.get(moveTo).marble.playerNumber != playersTurn){
			
			if (Board.hashBoard.containsKey(newHex)){
				if (Board.hashBoard.get(newHex).empty){
					return true; 
				}
				else if(Board.hashBoard.get(newHex).marble.playerNumber != playersTurn) {
				String newnewHex = Board.rows.adjacentDirection(Board.hashBoard.get(newHex), Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
					if (Board.hashBoard.containsKey(newnewHex)){
							if(Board.hashBoard.get(newnewHex).empty) {
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
