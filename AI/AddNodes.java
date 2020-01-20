package AI;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import src.Board;
import src.GameData;
import src.Hexagon;
import src.Move;

/*
 * Can add layers to our game tree.
 * There is a method in which you can put in the node and it will add every possible node to it.
 */

public class AddNodes {	
	public static int nodeNR = 0;
	
	public static ArrayList<GameState> everyGameState(Node<GameState> node){
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
					if (current.marble.playerNumber == src.GameMethods.changePlayer(node.returnData().turn)) {
						//run this for every direction
						for (int j = 1; j < 7; j++) {
							String moveTo = GameData.rows.adjacentDirection(currentString, j);
							
							//add every move with one marble -- is okay!!
							if (Move.validMoveOne(board, currentString, moveTo) & Board.hash.contains(moveTo)) {
								GameState oneM = new GameState(currentString, null, null, moveTo, node.returnData(), true);
								if (oneM.valid) {
									nodeNR++;
									oneMarbleMoves.add(oneM);
								}
							}
	
							for (int k = 1; k < 7; k++) {
							
							String marble2= GameData.rows.adjacentDirection(currentString, k);
							if (Board.hash.contains(currentString) && Board.hash.contains(marble2) & Board.hash.contains(moveTo)) {
								if (!board.get(marble2).empty) {
									if (board.get(marble2).marble.playerNumber == src.GameMethods.changePlayer(node.returnData().turn)) {
										if (Move.validMoveTwo(board, currentString, marble2, moveTo)) {
											GameState twoM = new GameState(currentString, marble2, null, moveTo, node.returnData(), true);
											if (twoM.valid) {
												nodeNR++;
												twoMarblesMoves.add(twoM);
											}
										}
									}
								}
							}
							
							//add every move with three marbles
							String marble3 = GameData.rows.adjacentDirection(marble2, k);
							if (Board.hash.contains(currentString) && Board.hash.contains(marble2) && Board.hash.contains(marble3) & Board.hash.contains(moveTo)) {
								if (!board.get(marble2).empty && !board.get(marble3).empty) {
									if (board.get(marble2).marble.playerNumber == src.GameMethods.changePlayer(node.returnData().turn) && board.get(marble3).marble.playerNumber == src.GameMethods.changePlayer(node.returnData().turn)) {
										if(Move.validMoveThree(board, currentString, marble2, marble3, moveTo)) {
											GameState threeM = new GameState(currentString, marble2, marble3, moveTo, node.returnData(), true);
											if (threeM.valid) {
												nodeNR++;
												threeMarblesMoves.add(threeM);	
											}
										}
									}
								}
							}
							}
						}
					}	
				}
			}
			ArrayList<GameState> everyPossibleNode = new ArrayList<GameState>();
			
			for(int i = 0; i < threeMarblesMoves.size(); i++) {
				everyPossibleNode.add(threeMarblesMoves.get(i));
				//node.addChild(threeMarblesMoves.get(i));
			}
			for(int i = 0; i < twoMarblesMoves.size(); i++) {
				everyPossibleNode.add(twoMarblesMoves.get(i));
				//node.addChild(twoMarblesMoves.get(i));
			}
			for(int i = 0; i < oneMarbleMoves.size(); i++) {
				everyPossibleNode.add(oneMarbleMoves.get(i));
				//node.addChild(oneMarbleMoves.get(i));
			}
			
			return everyPossibleNode;
		
	}
	
	//add all the children of the node you pass as a parameter
	public static void addForOne(Node<GameState> node) {
		ArrayList<GameState> children = everyGameState(node);
		for (int i = 0; i < children.size(); i++) {
			node.addChild(children.get(i));
		}
	}
		
		//add children to all these children
		public static void addForMultiple(List<Node<GameState>> nodes) {
			for (int i = 0; i < nodes.size(); i++) {
				addForOne(nodes.get(i));
			}
		}
	
}
