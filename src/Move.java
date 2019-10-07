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
				Board.hashBoard.get(code).marble.setFill(Color.AQUAMARINE);
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
				}
				else if(!Board.hashBoard.get(code).adjacent(Board.hashBoard.get(first))) {
					selectedMarbles.clear();
					if(Board.hashBoard.get(first).marble.playerNumber == 1) {
						Board.hashBoard.get(first).marble.setFill(Color.BLACK);
					}
					if(Board.hashBoard.get(first).marble.playerNumber == 2) {
						Board.hashBoard.get(first).marble.setFill(Color.GRAY);
					}
					first = code;
					Board.hashBoard.get(code).marble.setFill(Color.AQUAMARINE);
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
					System.out.println("total = 2");
					selected = true;
				}
				else if(!Board.hashBoard.get(code).adjacent(Board.hashBoard.get(second))) {
					selectedMarbles.clear();
					if(Board.hashBoard.get(first).marble.playerNumber == 1) {
						Board.hashBoard.get(first).marble.setFill(Color.BLACK);
						Board.hashBoard.get(second).marble.setFill(Color.BLACK);
					}
					if(Board.hashBoard.get(first).marble.playerNumber == 2) {
						Board.hashBoard.get(first).marble.setFill(Color.GRAY);
						Board.hashBoard.get(second).marble.setFill(Color.GRAY);
					}
					first = code;
					second = null;
					nrSelected = 1;
					selectedMarbles.add(code);
					
				}
				else {
					third = code;
					nrSelected++;
					selectedMarbles.add(code);
					selected = true;
					Board.hashBoard.get(third).marble.setFill(Color.AQUAMARINE);
					System.out.println("selected three");
				}
			}
		}
		//so if all of these are not possible, try if it is possible to move it to the place you want to move it to
		//if it's not, then it will automatically reset
		else{
			if (selected && Board.hashBoard.get(code).adjacent(Board.hashBoard.get(first))) {
				moveTo = code;
				System.out.println("moveto");
			}	
		}
		
		if(moveTo != null) {
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
			move();
		}
	}
	
	public void move() {
		//check if valid -> if not, reset, else: perform movement, change player, resetmove
		System.out.println("move");
		if (nrSelected == 1) {
			if(validMoveOne()) {
				performMovementOne();
				changePlayer();
				resetMove();
			}
		}
		else if(nrSelected ==2) {
			if(validMoveTwo()) {
				performMovementTwo();
				changePlayer();
				resetMove();
			}
		}
		else if(nrSelected ==3) {
			if(validMoveThree()) {
				performMovementThree();
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
		
	}
	
	public void performMovementThree() {
		
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
		
		//TODO: enter rules
		return true;
	}
	
	public boolean validMoveThree() {
		
		//TODO: enter rules
		return true;
	}
	
}
