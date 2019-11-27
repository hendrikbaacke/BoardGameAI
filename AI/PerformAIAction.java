package AI;

import java.util.ArrayList;
import java.util.List;

import src.Board;
import src.Move;

public class PerformAIAction {

	//for now, this is done!!
	
	public static GameTree tree;
	
	//get the best node and use this action
	public static void perform() {
		//build the tree and perform search - make a new node a root node?
		Node<GameState> needed = choose();
		if (needed.returnData().first != null) {
			Board.move.select(needed.returnData().first, Board.hashBoard);
			if (needed.returnData().second != null) {
				Board.move.select(needed.returnData().second, Board.hashBoard);
				if (needed.returnData().third != null) {
					Board.move.select(needed.returnData().third, Board.hashBoard);
				}
				else {
					Board.move.select(needed.returnData().first, Board.hashBoard);
				}
			}
			else {
				Board.move.select(needed.returnData().first, Board.hashBoard);
			}
			Board.move.select(needed.returnData().moveTo, Board.hashBoard);
		}
		Move.ai = false;
	}
	
	public static Node<GameState> choose(){
		List<Node<GameState>> depth = tree.findAtDepth(0);
		return depth.get(0);
	}
	
	public static void createGameTree(GameState state, int layers) {
		Node current = new Node(state);
		//always create a new tree
		tree = new GameTree(current);
		System.out.println("creating");
		tree.buildFullTree(1);
	}
	
}
