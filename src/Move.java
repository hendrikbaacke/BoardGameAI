package src;

public class Move {
	public String first;
	public String second;
	public String third;
	
	public String moveTo;
	
	private boolean selected = true;
	
	public int playersTurn;
	
	
	public void move() {
		
		
	}
	
	public void resetMove() {
		this.first = null;
		this.second = null;
		this.third = null;
		this.moveTo = null;
		this.selected = false;
	}
	
	public boolean validMoveOne() {
		//TODO: enter rules to check whether it is possible to move it here
		//so check whether adjacent: it is possible to use the codes of the first (as it will be moved) and the moveTo -> use the adjacent method in hexagon
		
		return true;
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
