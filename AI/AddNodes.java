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
	
	for (int k = 0; k < hexagons.size(); k++) {
		if (!board.get(hexagons.get(k)).empty) {
			
		}
	}
	
		//each marble
		for (int i = 0; i < hexagons.size(); i++) {
			
			//why does this throw an error????
			String currentString = hexagons.get(i);
			Hexagon current = board.get(currentString);
			//System.out.println(currentString);
			
			if (!current.empty){
				//System.out.println("isn't empty");
				if (current.marble.playerNumber == changePlayer(node.returnData().turn)) {
					//run this for every direction
					for (int j = 1; j < 7; j++) {
						System.out.println("code " + currentString + " dir " + j);
						/*
						//for 1 marble
						GameState oneM = new GameState(currentString, null, null, Board.rows.adjacentDirection(currentString, j), node.returnData(), changePlayer(node.returnData().turn));
						if (oneM.valid) {
							node.addChild(oneM);
							nodeNR++;
							System.out.println(nodeNR);
						}
						//System.out.println("added one");
						
						
						//for 2 marbles
						int opposite = Board.rows.oppositeDirection(j);
						String needed = Board.rows.adjacentDirection(currentString, opposite);
						GameState twoM = new GameState(currentString, needed, null, Board.rows.adjacentDirection(currentString, j), node.returnData(), changePlayer(node.returnData().turn));
						if (twoM.valid) {
							node.addChild(twoM);
							nodeNR++;
							System.out.println(nodeNR);
						}
						//System.out.println("added two");
						
						//for 3 marbles
						String secondM = Board.rows.adjacentDirection(needed, opposite);
						GameState threeM = new GameState(currentString, needed, secondM, Board.rows.adjacentDirection(currentString, j), node.returnData(), changePlayer(node.returnData().turn));
						if (threeM.valid) {
							node.addChild(threeM);
							nodeNR++;
							System.out.println(nodeNR);
						}
						//System.out.println("added three");
						 * */
			
						if (Move.validMoveOne(board, currentString, Board.rows.adjacentDirection(currentString, j))) {
							GameState oneM = new GameState(currentString, null, null, Board.rows.adjacentDirection(currentString, j), node.returnData(), changePlayer(node.returnData().turn));
							nodeNR++;
							System.out.println(nodeNR);
						}
						
					}
				}
			}
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
