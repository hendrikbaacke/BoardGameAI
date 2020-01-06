package AI;

//should be correct

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import src.Board;
import src.GameData;
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
	
							String moveTo = GameData.rows.adjacentDirection(currentString, j);
							
							//add every move with one marble -- is okay!!
							if (Move.validMoveOne(board, currentString, moveTo) & Board.hash.contains(moveTo)) {
								GameState oneM = new GameState(currentString, null, null, moveTo, node.returnData());
								if (oneM.valid) {
									nodeNR++;
									//System.out.println(nodeNR);
									oneMarbleMoves.add(oneM);
									//System.out.println("first is " + currentString + " moveto is " + moveTo);
								}
								//node.addChild(oneM);
							}
	
							for (int k = 1; k < 7; k++) {
							
							String marble2= GameData.rows.adjacentDirection(currentString, k);
							if (Board.hash.contains(currentString) && Board.hash.contains(marble2) & Board.hash.contains(moveTo)) {
								if (!board.get(marble2).empty) {
									if (board.get(marble2).marble.playerNumber == changePlayer(node.returnData().turn)) {
										if (Move.validMoveTwo(board, currentString, marble2, moveTo)) {
											GameState twoM = new GameState(currentString, marble2, null, moveTo, node.returnData());
											if (twoM.valid) {
												nodeNR++;
												//System.out.println(nodeNR);
												twoMarblesMoves.add(twoM);
												//System.out.println("first is " + currentString + " second is  " + marble2 +  " moveto is " + moveTo);
												//node.addChild(twoM);
											}
										}
									}
								}
							}
							
							//add every move with three marbles
							String marble3 = GameData.rows.adjacentDirection(marble2, k);
							if (Board.hash.contains(currentString) && Board.hash.contains(marble2) && Board.hash.contains(marble3) & Board.hash.contains(moveTo)) {
								if (!board.get(marble2).empty && !board.get(marble3).empty) {
									if (board.get(marble2).marble.playerNumber == changePlayer(node.returnData().turn) && board.get(marble3).marble.playerNumber == changePlayer(node.returnData().turn)) {
										if(Move.validMoveThree(board, currentString, marble2, marble3, moveTo)) {
											GameState threeM = new GameState(currentString, marble2, marble3, moveTo, node.returnData());
											if (threeM.valid) {
												nodeNR++;
												//System.out.println(nodeNR);
												//node.addChild(threeM);
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
				if (GameData.numberPlayers == 2 || GameData.numberPlayers == 3 && playersTurn ==3) {
					playersTurn = 1;
				}
				else if (GameData.numberPlayers == 3 && playersTurn ==2){
					if (playersTurn == 2) {
						playersTurn = 3;
					}
					
				}
			}
			return playersTurn;
		}
	
}
