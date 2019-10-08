package src;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Move {
	//first, second, and third marble code that need to be moved (the hexagon in the hashboard contains the marbles of the same code- which can be removed and added quickly)
	public String first;
	public String second;
	public String third;
	
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
		System.out.println("select");
		//check whether a marble from the player is selected, then set that as a code and add it to the selection:
		if (first == null && !Board.hashBoard.get(code).empty) {
			if (Board.hashBoard.get(code).marble.playerNumber == playersTurn) {
				System.out.println("selected 1");
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
					System.out.println("total = 1");
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
					System.out.println("selected two");
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
					System.out.println("total = 2");
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
					System.out.println("selected three");
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
						System.out.println("moveto");
					}
					}
				else {
					moveTo = code;
					System.out.println("moveto");
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
	}
	
	public void move() {
		//check if valid -> if not, reset, else: perform movement, change player, resetmove
		System.out.println("move");
		if (nrSelected == 1) {
			if(validMoveOne()) {
				System.out.println(Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo)));
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
			playersTurn = 1;
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
		
		
		
		
	}
	
	public void performMovementThree() {
		//if it is moves sideways, then it can never push another marble
		if(Board.rows.sideways(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(moveTo))) {
			moveSideways();
		}
		
		
		
	}
	
	public void moveSideways() {
		int direction = Board.rows.direction(Board.hashBoard.get(first), Board.hashBoard.get(moveTo));
		System.out.println("direction is " + direction);
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
	
	public void doPushOne() {
		
		
		boolean offboard = false;
		if (offboard){
			Board.score[playersTurn-1]++;
		}
	}
	
	public void doPushTwo() {
		
		
		boolean offboard = false;
		if(offboard) {
			Board.score[playersTurn-1]++;
		}
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
	
	public boolean validMoveTwo() {
		//if it needs to move sideways and if there are two free space where they are needed, the move is valid
		if(Board.rows.sideways(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(moveTo))) {
			if (Board.rows.twoFree(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(moveTo))) {
				return true;
			}
		}
		//add moving in same direction to this later
		else {
			resetMove();
		}
		
		//TODO: enter rules
		return false;
	}
	
	public boolean validMoveThree() {
		if(Board.rows.sideways(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(moveTo))) {
			if (Board.rows.threeFree(Board.hashBoard.get(first), Board.hashBoard.get(second), Board.hashBoard.get(third), Board.hashBoard.get(moveTo))) {
				return true;
			}
		}
		//for now, add the moving in same direction to this later
		else {
			resetMove();
		}
		return false;
		//TODO: enter rules
	}
	
	public void gameFinished() {
		if (Board.score[0] == 6 || Board.score[1] ==6) {
			//go to ending screen
		}
	}
	
}
