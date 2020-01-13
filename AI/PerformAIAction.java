package AI;

import java.util.ArrayList;
import java.util.List;

import src.Board;
import src.GameData;
import src.Move;

public class PerformAIAction {

	//for now, this is done!!
	public static GameTree tree;

	//get the best node and use this action
	public static void perform(boolean greedy) {
		//build the tree and perform search - make a new node a root node?
		Node<GameState> needed = null;
		if (greedy) {
		needed = choose2();
		}
		else {
			//needed = chooseMCTS_Search();
			needed = choose();
			
		}

		if (needed.returnData().first != null) {

			GameData.move.select(needed.returnData().first, Board.hashBoard);
			System.out.println("select " + needed.returnData().first);
			if (needed.returnData().second != null) {
				GameData.move.select(needed.returnData().second, Board.hashBoard);
				System.out.println("select " + needed.returnData().second);
				if (needed.returnData().third != null) {
					GameData.move.select(needed.returnData().third, Board.hashBoard);
					System.out.println("select " + needed.returnData().third);
				} 
				else {
					GameData.move.select(needed.returnData().first, Board.hashBoard);
					System.out.println("select " + needed.returnData().first);
				}
			}
			else {
				GameData.move.select(needed.returnData().first, Board.hashBoard);
				System.out.println("select " + needed.returnData().first);
			}
			System.out.println("Move to:  " + needed.returnData().moveTo);
			GameData.move.select(needed.returnData().moveTo, Board.hashBoard);
			
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
		AlphaBeta AB = new AlphaBeta();
		AB.performAB(tree.getRoot(), 2, -Double.MAX_VALUE, Double.MAX_VALUE, true);
		Node<GameState> node = AB.node;
		return node;
	}
	
	public static void createGameTree(GameState state, int layers) {
		Node current = new Node(state);
		//always create a new tree
		tree = new GameTree(current);
		System.out.println("creating");
		tree.buildFullTree(layers);
	}
	
	public static Node<GameState> chooseMCTS_Search(){
		Node<GameState> node = tree.getRoot();
		MCTS mc = new MCTS();
		Node<GameState> result = mc.findBestMove();
		return result;
	}
	
}
