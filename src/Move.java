package src;

import java.util.ArrayList;

public class Move {
	public String first;
	public String second;
	public String third;
	
	public ArrayList<String> selectedMarbles = new ArrayList<String>();
	
	public int nrSelected = 0;
	
	public String moveTo;
	
	private boolean selected = false;
	
	public int playersTurn = 1;
	
	public void select(String code) {
		//select the marbles for the movement
		if (first == null && Board.hashBoard.get(code).marble.playerNumber == playersTurn) {
			first = code;
			nrSelected++;
			selectedMarbles.add(code);
		}
		else if(second == null  && Board.hashBoard.get(code).marble.playerNumber == playersTurn) {
			if (first.equals(code)) {
				selected = true;
			}
			else{
				second = code;
				nrSelected++;
				selectedMarbles.add(code);
			}
		}
		else if(third == null && !selected && Board.hashBoard.get(code).marble.playerNumber == playersTurn) {
			if(first.equals(code) || second.equals(code)) {
				selected = true;
			}
			else {
				third = code;
				nrSelected++;
				selectedMarbles.add(code);
			}
		}
		else{
			if (first != null) {
				moveTo = code;
			}	
		}
		
		if(moveTo != null) {
			move();
		}
	}
	
	public void move() {
		if (nrSelected == 1) {
			if(validMoveOne()) {
				performMovementOne();
				changePlayer();
				
			}
		}
		else if(nrSelected ==2) {
			if(validMoveTwo()) {
				performMovementTwo();
				changePlayer();
			}
		}
		else if(nrSelected ==3) {
			if(validMoveThree()) {
				performMovementThree();
				changePlayer();
			}
		}
	}
	
	public void changePlayer() {
		if (playersTurn ==1) {
			playersTurn = 2;
		}
		else {
			playersTurn = 1;
		}
	}
	
	public void performMovementOne() {
		Marble moving = Board.hashBoard.get(first).marble;
		Board.hashBoard.get(first).setEmpty();;
		Board.hashBoard.get(moveTo).setFull(moving);
		moving.setLocationKey(moveTo);
		moving.updateLocation();
	}
	
	public void performMovementTwo() {
		
	}
	
	public void performMovementThree() {
		
	}
	
	public void resetMove() {
		this.first = null;
		this.second = null;
		this.third = null;
		this.moveTo = null;
		this.selected = false;
		this.nrSelected = 0;
	}
	
	//test if it is possible to move these
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
