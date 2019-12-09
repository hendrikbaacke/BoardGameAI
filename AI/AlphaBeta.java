package AI;

import java.util.List;
import AI.EvaluationFunction;
import javafx.scene.paint.Color;
import src.Board;
import src.MarbleStorage;
import src.Move;


//Returns optimal value for 
//current player (Initially called 
//for root and maximizer) 


public class AlphaBeta{
	
	Node<GameState> node = null;
	
	public AlphaBeta() {
		
	}
	
	public double performAB(Node<GameState> node, int depth, double alpha, double beta, boolean max) {
		double value = 0;
		if (depth == 0 /* node = terminal */) {
			return node.returnData().evaluatedValue;
		}
		
		if(max) {
			value = -Double.MAX_VALUE;
			
			for (Node<GameState> node2 : node.children) {
				value = Math.max(value, performAB(node2, depth-1, alpha, beta, false));
				alpha = Math.max(alpha, value);
				
				if (Math.max(alpha, value) == value) {
					this.node = node2;
					//System.out.println("new alpha");
				}
				if (alpha >= beta) {
					break;
				}
			}
			return value;
		}
		else {
			value = Double.MAX_VALUE;
			
			for (Node<GameState> node2 : node.children) {
				value = Math.min(value, performAB(node2, depth-1, alpha, beta, true));
				beta = Math.min(beta, value);
				
				if (alpha >= beta) {
					break;
				}
			}
			return value;
		}	
	}
	/*
	 * Pseudocode (thanks wikipedia)
	 function alphabeta(node, depth, α, β, maximizingPlayer) is
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
        value := −∞
        for each child of node do
            value := max(value, alphabeta(child, depth − 1, α, β, FALSE))
            α := max(α, value)
            if α ≥ β then
                break (* β cut-off *)
        return value
    else
        value := +∞
        for each child of node do
            value := min(value, alphabeta(child, depth − 1, α, β, TRUE))
            β := min(β, value)
            if α ≥ β then
                break (* α cut-off *)
        return value

	 * 
	 * 
	 */
}

/*
public class AlphaBeta {

	private static final Integer MAX_DEPTH = 5;
	private GameTree game;
	private int playerTurn;
	private static double alpha = Double.MIN_VALUE;
	private static double beta = Double.MAX_VALUE;


	public AlphaBeta(GameTree tree, int playerTurn) {
		this.game = tree;
		this.playerTurn = playerTurn;
	}

	public Node<GameState> getBestMove() {
		Node<GameState> bestMove = null;
		double best = Double.MIN_VALUE;
		List<Node<GameState>> moves = game.getRoot().children;
		
		for (Node<GameState> m : moves) {
			alpha = Double.MIN_VALUE;
			beta = Double.MAX_VALUE;
			
			double score = max(0, m, alpha, beta, otherPlayer());
			
			if (score > best) {
				best = score;
				bestMove = m;
			}
			
			
		}
		//System.out.println("alpha beta is working.");
		return bestMove;
	}

	private double otherPlayer() {
		if (playerTurn == 1)
			return 2;
		if (playerTurn == 2)
			return 1;
		return 0;
	}

	public double max(int depth, Node<GameState> state, double alpha, double beta, double playerTurn) {
		if (0 == depth) {
            return ((playerTurn == otherPlayer()) ? 1 : -1) * state.returnData().evaluatedValue;
		}

		if (depth >= MAX_DEPTH) {
			double heuristicvalue = state.returnData().evaluatedValue;
			return heuristicvalue;
		}
		double result = Double.MIN_VALUE;
		if (playerTurn != otherPlayer()) {

			List<Node<GameState>> moves = state.children;
			
			for (Node<GameState> move : moves) {
				double value = min(depth + 1, move, alpha, beta, otherPlayer());
				
				if (value > result) {
					result = value;
				}
				
				if (value >= beta) {
					return result;
				}
				
				if (value > alpha) {
					alpha = value;
				}
			}
		}
		return result;
	}

	private double min(int depth, Node<GameState> state, double alpha,
			double beta, double playerTurn) {
		
		if (depth >= MAX_DEPTH) {
			double heuristicvalue = state.returnData().evaluatedValue;
			return heuristicvalue;
		}
		
		double result = Double.MAX_VALUE;
		List<Node<GameState>> moves = state.children;
		
		for (Node<GameState> move : moves) {
			double value = max(depth + 1, move, alpha, beta, otherPlayer());
			if (value < result) {
				result = value;
			}
			if (value <= alpha) {
				return result;
			}
			if (value < beta) {
				beta = value;
			}
		}
		return result;
	}
}*/

