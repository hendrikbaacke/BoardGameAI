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
		Node<GameState> needed = null;
		if (greedy) {
		needed = choose2();
		}
		else {
			needed = choose();
		}
//		Node<GameState> needed = choose();

		if (needed.returnData().first != null) {

			Board.move.select(needed.returnData().first, Board.hashBoard);
			System.out.println("select 11111 " + needed.returnData().first);
			if (needed.returnData().second != null) {
				Board.move.select(needed.returnData().second, Board.hashBoard);
				System.out.println("select " + needed.returnData().second);
				if (needed.returnData().third != null) {
					Board.move.select(needed.returnData().third, Board.hashBoard);
					System.out.println("select " + needed.returnData().third);
				} 
				else {
					Board.move.select(needed.returnData().first, Board.hashBoard);
					System.out.println("select " + needed.returnData().first);
				}
			}
			else {
				Board.move.select(needed.returnData().first, Board.hashBoard);
				System.out.println("select " + needed.returnData().first);
			}
			Board.move.select(needed.returnData().moveTo, Board.hashBoard);
			System.out.println("select " + needed.returnData().moveTo);
		}
		Move.ai = false;
		DeleteLayers.deleteBranch(tree.getRoot());
	}
	
	public static Node<GameState> choose2(){
		Node<GameState> bestMove = null;
		double maxEval = Double.NEGATIVE_INFINITY;
		
		for (int i = 0; i <tree.getRoot().children.size(); i++){
			if (Math.max(maxEval, tree.getRoot().children.get(i).returnData().evaluatedValue) > maxEval) {
				maxEval = tree.getRoot().children.get(i).returnData().evaluatedValue;
				bestMove = tree.getRoot().children.get(i);
			}
		}
		return bestMove;
	}
	
	public static Node<GameState> choose(){
		//List<Node<GameState>> depth = tree.findAtDepth(1);
		//return depth.get(0);
		AlphaBeta  A  =new AlphaBeta(tree,2);
		return A.getBestMove();
	}
	
	public static void createGameTree(GameState state, int layers) {
		Node current = new Node(state);
		//always create a new tree
		tree = new GameTree(current);
		System.out.println("creating");
		tree.buildFullTree(layers);
		
	}
	
}
