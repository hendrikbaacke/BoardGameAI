package src;

import java.util.ArrayList;

public class BoardRows {
	//creates rows for the board -> one in every direction
	//NEEDS to be created after the board otherwise it won't work
	
	ArrayList<ArrayList<Hexagon>> horizontal = new ArrayList<ArrayList<Hexagon>>();
	ArrayList<ArrayList<Hexagon>> topLeft = new ArrayList<ArrayList<Hexagon>>();
	ArrayList<ArrayList<Hexagon>> topRight = new ArrayList<ArrayList<Hexagon>>();
	
	public BoardRows() {
		//create the rows
		
		//horizontal rows
		for (char ch='A'; ch <= 'I'; ch++) {
			String letterCode = Character.toString(ch);
			ArrayList<Hexagon> row = new ArrayList<Hexagon>();
			for (int j = 0; j < 10; j++) {
				if (Board.hashBoard.containsKey((letterCode+j))) {
					row.add(Board.hashBoard.get(letterCode+j));
				}
			}
			horizontal.add(row);
		}
		
		
		//starting from top left (going to bottom right)
		for (int j = 0; j < 10; j++) {
			ArrayList<Hexagon> row = new ArrayList<Hexagon>();
			for (char ch='A'; ch <= 'I'; ch++) {
				String letterCode = Character.toString(ch);
				if (Board.hashBoard.containsKey((letterCode+j))) {
					row.add(Board.hashBoard.get(letterCode+j));
				}
			}
			topLeft.add(row);
		}
		
		//starting from top right (going to bottom left)
		
		char start = 'I';
		for (int i = 13; i >= 5; i--) {
			ArrayList<Hexagon> row = new ArrayList<Hexagon>();
			for (int j = 0; j < 9; j++) {
				String lettercode = Character.toString(start);
				int temp = i-j;
				if (Board.hashBoard.containsKey((lettercode+temp))) {
					row.add(Board.hashBoard.get(lettercode+temp));
					System.out.println(lettercode+temp);
				}
				start = (char) (start -1);
			}
			start = 'I';
			topRight.add(row);
			System.out.println("end");
		}
		
		
		
	}
	
	public boolean sameRowThree(Hexagon one, Hexagon two, Hexagon three) {
		boolean sameRow = false;
		
		for (int i = 0; i < horizontal.size(); i++) {
			if (sameRow) {
				break;
			}
			if (horizontal.get(i).contains(one) && horizontal.get(i).contains(two) && horizontal.get(i).contains(three)) {
				sameRow = true;
				
			}
		}
		
		for (int i = 0; i < topLeft.size(); i++) {
			if (sameRow) {
				break;
			}
			if (topLeft.get(i).contains(one) && topLeft.get(i).contains(two) && topLeft.get(i).contains(three)) {
				sameRow = true;
				
			}
		}
		
		for(int i = 0; i < topRight.size(); i++) {
			if (sameRow) {
				break;
			}
			if (topRight.get(i).contains(one) && topRight.get(i).contains(two) && topRight.get(i).contains(three)) {
				sameRow = true;
			}
			System.out.println(sameRow);
			
		}
		return sameRow;
	}
	
	public int direction(Hexagon first, Hexagon moveTo) {
		boolean sameRow = false;
		int direction = 0;
		
		for (int i = 0; i < horizontal.size(); i++) {
			if (sameRow) {
				break;
			}
			if (horizontal.get(i).contains(first) && horizontal.get(i).contains(moveTo)) {
				String numberFirst = first.code.substring(1);
				String numberMoveTo = moveTo.code.substring(1);
				
				int numberOne = Integer.parseInt(numberFirst);
				int numberTwo = Integer.parseInt(numberMoveTo);
				
				if (numberOne>numberTwo) {
					direction = 1;
				}
				else {
					direction = 4;
				}
				
				sameRow = true;
			}
		}
		
		for (int i = 0; i < topLeft.size(); i++) {
			if (sameRow) {
				break;
			}
			if (topLeft.get(i).contains(first) && topLeft.get(i).contains(moveTo)) {
				
					char letterFirst = first.code.charAt(0);
					char letterMoveTo = moveTo.code.charAt(0);
					
					if (letterFirst>letterMoveTo) {
						direction = 5;
					}
					else {
						direction = 2;
					}
				
			}
		}
		
		for(int i = 0; i < topRight.size(); i++) {
			if (sameRow) {
				break;
			}
			if (topRight.get(i).contains(first) && topRight.get(i).contains(moveTo)) {
				String numberFirst = first.code.substring(1);
				String numberMoveTo = moveTo.code.substring(1);
				
				int numberOne = Integer.parseInt(numberFirst);
				int numberTwo = Integer.parseInt(numberMoveTo);
				
				if (numberOne>numberTwo) {
					direction = 3;
				}
				else {
					direction = 6;
				}
				
				sameRow = true;
			}
			
		}
		
		return direction;
	}
	
	public boolean sideways(Hexagon first, Hexagon second, Hexagon moveTo) {
		if (this.direction(first, second) != this.direction(first, moveTo)) {
			return true;
		}
		return false;
	}
	
	//if it's sideways with two!!
	public boolean twoFree(Hexagon first, Hexagon second, Hexagon moveTo) {
		int direction = direction(first, moveTo);
		char letterFirst = first.code.charAt(0);
		char letterSecond = second.code.charAt(0);
		
		String letterFirstSt = first.code.substring(0,1);
		String letterSecondSt = first.code.substring(0,1);
		
		String numberFirst = first.code.substring(1);
		String numberSecond = second.code.substring(1);
		
		int numberOne = Integer.parseInt(numberFirst);
		int numberTwo = Integer.parseInt(numberSecond);
		
		
		if (direction ==1) {
			int neededFreeOne = numberOne -1;
			int neededFreeTwo = numberTwo -1;
			//if(Board.hashBoard.get(letterFirstSt+neededFreeOne) && Board.hashBoard.get(letterSecondSt+neededFreeTwo) {
				
			//}
			
		}
		if (direction ==2) {
			
		}
		if(direction ==3) {
			
		}
		if (direction ==4) {
			
		}
		if (direction ==5) {
			
		}
		if (direction ==6) {
			
		}
		
		return false;
	}
	
	//if it's sideways with three
	public boolean threeFree(Hexagon first, Hexagon second, Hexagon third, Hexagon moveTo) {
		
		
		return true;
	}
	
	//if you need to push one marble - so with the two marbles
	public boolean pushOne(Hexagon first, Hexagon second, Hexagon moveTo) {
		
		return true;
	}
	
	
	//if you need to push either one or two - wih the three marbles
	public boolean pushOneOrTwo(Hexagon first, Hexagon second, Hexagon third, Hexagon moveTo) {
		
		return true;
	}
}
