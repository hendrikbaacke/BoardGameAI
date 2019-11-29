package AI;

//should be correct

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import src.Board;
import src.Hexagon;
import src.Move;

public class AddNodes {
//automatically add a layer to the tree
//put in a node and it automatically gives you every game state that will be possible after this and will add this as a child!!
	
	//add every gamestate to a node as children
	//check for every nr marbles, direction and node
	public static int nodeNR = 0;
	
	public static void addForOne(Node<GameState> node) {

		
	Hashtable<String, Hexagon> board = node.returnData().boardState;
	ArrayList<String> hexagons = Board.hash;
	
	ArrayList<GameState> oneMarbleMoves = new ArrayList();
	ArrayList<GameState> twoMarblesMoves = new ArrayList();
	ArrayList<GameState> threeMarblesMoves = new ArrayList();
	
		//each marble
		for (int i = 0; i < hexagons.size(); i++) {
			String currentString = hexagons.get(i);
			Hexagon current = board.get(currentString);
			
			if (!current.empty){
				if (current.marble.playerNumber == changePlayer(node.returnData().turn)) {
					//run this for every direction
					for (int j = 1; j < 7; j++) {
						//System.out.println("code " + currentString + " dir " + j);

						String moveTo = Board.rows.adjacentDirection(currentString, j);
						
						//add every move with one marble -- is okay!!
						if (Move.validMoveOne(board, currentString, moveTo)) {
							GameState oneM = new GameState(currentString, null, null, moveTo, node.returnData());
							if (oneM.valid) {
								nodeNR++;
								System.out.println(nodeNR);
								oneMarbleMoves.add(oneM);
								System.out.println("first is " + currentString + " moveto is " + moveTo);
							}
							//node.addChild(oneM);
						}

						//add every move with two marbles - in just one direction
						int direction = Board.rows.oppositeDirection(j);
						String marble2= Board.rows.adjacentDirection(currentString, direction);
						if (Move.validMoveTwo(board, currentString, marble2, moveTo)) {
							GameState twoM = new GameState(currentString, marble2, null, moveTo, node.returnData());
							if (twoM.valid) {
								nodeNR++;
								System.out.println(nodeNR);
								twoMarblesMoves.add(twoM);
								System.out.println("first is " + currentString + " second is  " + marble2 +  " moveto is " + moveTo);
								//node.addChild(twoM);	
							}
						}
						
						//add every move with three marbles
						String marble3 = Board.rows.adjacentDirection(marble2, direction);
						if(Move.validMoveThree(board, currentString, marble2, marble3, moveTo)) {
							GameState threeM = new GameState(currentString, marble2, marble3, moveTo, node.returnData());
							if (threeM.valid) {
								nodeNR++;
								System.out.println(nodeNR);
								//node.addChild(threeM);
								threeMarblesMoves.add(threeM);	
								System.out.println("first is " + currentString + " second is  " + marble2 + " third is " + marble3 +  " moveto is " + moveTo);
								}
							}
						}
					}	
						//sideways directions!!! - add to try for this as well
						
				}
			}
	
		for(int i = 0; i < threeMarblesMoves.size(); i++) {
			node.addChild(threeMarblesMoves.get(i));
		}
		for(int i = 0; i < twoMarblesMoves.size(); i++) {
			node.addChild(twoMarblesMoves.get(i));
		}
		for(int i = 0; i < oneMarbleMoves.size(); i++) {
			node.addChild(oneMarbleMoves.get(i));
		}
		
	}
	
	//add children to all these children
	public static void addForMultiple(List<Node<GameState>> nodes) {
		for (int i = 0; i < nodes.size(); i++) {
			addForOne(nodes.get(i));
		}
	}
	
	
	//copy and paste from the move class
	public static int changePlayer(int playersTurn) {
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
		return playersTurn;
	}
	
}
