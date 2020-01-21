package AI;

import java.util.Hashtable;
import src.GameData;
import src.Hexagon;
import src.Move;

public class PerformAIAction {

	public static GameTree tree;

	//get the best node and use this action
	public static void perform(boolean greedy, boolean alphabeta, Hashtable<String, Hexagon> board) {
		//build the tree and perform search - make a new node a root node?
		Node<GameState> needed = null;
		if (greedy) {
			needed = choose2();
		} else if (alphabeta) {
			needed = choose();
		} else {
			needed = chooseMCTS_Search();
		}

		if (needed.returnData().first != null) {
			GameData.move.select(needed.returnData().first, board);
			if (needed.returnData().second != null) {
				GameData.move.select(needed.returnData().second, board);
				if (needed.returnData().third != null) {
					GameData.move.select(needed.returnData().third, board);
				} else {
					GameData.move.select(needed.returnData().first, board);
				}
			} else {
				GameData.move.select(needed.returnData().first, board);
			}
			GameData.move.select(needed.returnData().moveTo, board);
		}
		
		if (alphabeta || greedy) {
			DeleteLayers.deleteBranch(tree.getRoot());
		}
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
		AlphaBeta AB = new AlphaBeta();
		AB.performAB(tree.getRoot(), 2, -Double.MAX_VALUE, Double.MAX_VALUE, true);
		Node<GameState> node = AB.node;
		return node;
	}
	
	public static void createGameTree(GameState state, int layers) {
		Node<GameState> current = new Node<GameState>(state);
		//always create a new tree
		tree = new GameTree(current);
		tree.buildFullTree(layers);
	}
	
	//accidentally changed some stuff in node, which is why it said it had errors.
	public static Node<GameState> chooseMCTS_Search(){
		return Move.monteCarlo.MCTS(10);
	}
}
