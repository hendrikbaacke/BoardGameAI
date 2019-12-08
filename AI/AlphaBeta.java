package AI;

import java.util.List;
import AI.EvaluationFunction;
import javafx.scene.paint.Color;
import src.Board;
import src.MarbleStorage;
import src.Move;

public class AlphaBeta {

	private static final Integer MAX_DEPTH = 5;
	private GameTree game;
	private int playerTurn;
	private static double alpha = Double.MIN_VALUE;
	private static double beta = Double.MAX_VALUE;
	private Move move = new Move();

	private int PlayerTurn;

	public AlphaBeta(GameTree tree, int playerTurn) {
		this.game = tree;
		this.playerTurn = PlayerTurn;
	}

	public Node<GameState> getBestMove() {
		GameState T = null;
		Node<GameState> bestMove = new Node<GameState>(T);
		double best = Double.MIN_VALUE;
		List<Node<GameState>> moves = game.findAtDepth(1);
		for (Node<GameState> m : moves) {
			double score = max(0, m, alpha, beta, otherPlayer());

			if (score > best) {
				best = score;
				bestMove = m;
			}
		}
		System.out.print("alpha beta is working, ");

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
        //WHEN THE DEPTH REACHES THE MAXDEPTH
		if (0 == depth)
            return ((playerTurn == otherPlayer()) ? 1 : -1) * state.returnData().evaluatedValue;

		// WHEN THE DEPTH REACHES THE MAXDEPTH
		if (depth >= MAX_DEPTH) {
			// HERE ADD THE EVALUATION FUNCTION
			// here find the heursitic value of the state
			// EvaluationFunction ef = new EvaluationFunction(state);
			// double heuristicvalue = ef.evaluate(state);
			double heuristicvalue = state.returnData().evaluatedValue;
			return heuristicvalue;
		}
		double result = Double.MIN_VALUE;
		if (playerTurn != otherPlayer()) {

			// here set alpha to the negative infinity
			// then we find the list of possible moves of the state
			List<Node<GameState>> moves = state.children;
			// here for each move we do a recursive function to the min function
			// because this is the minimizing player
			for (Node<GameState> move : moves) {
				double value = min(depth + 1, move, alpha, beta, otherPlayer());
				// if the value we found is bigger than the alpha then we update
				// the alpha from -infinity to the bigger value
				if (value > result) {
					result = value;
				}
				// if value we get is bigger than beta then we return result
				// because it is equal to alpha
				// so here we do not change alpha because the value was bigger
				// than beta
				if (value >= beta) {
					return result;
				}
				// if value is bigger than the alpha -infinity //so yes we
				// update alpha and it will be equal to the value
				if (value > alpha) {
					alpha = value;
				}
			}
		}
		return result;
	}

	private double min(int depth, Node<GameState> state, double alpha,
			double beta, double playerTurn) {
		// here we get the winner of the game so we are at the end of the game
		// and the depth also
		// exceedes the max depth
		if (depth >= MAX_DEPTH) {
			// here find the heursitic value od the state
			double heuristicvalue = state.returnData().evaluatedValue;
			return heuristicvalue;
		}
		// here result is equal to beta and we are trying to get the value of
		// beta
		double result = Double.MAX_VALUE;
		// then we find the list of possible moves of the state
		List<Node<GameState>> moves = state.children;
		// for all the maximum player we have
		for (Node<GameState> move : moves) {
			// because the childern of the minimum node are the maximizing
			// player node we do a recursive call to
			// max function
			double value = max(depth + 1, move, alpha, beta, otherPlayer());
			// when we go to the maximum we will get the value then we compare
			// this value with beta
			// if value is less than the beta then we update the result(the
			// beta)
			if (value < result) {
				result = value;
			}
			// here we check if the value is less then we return
			if (value <= alpha) {
				return result;
			}
			if (value < beta) {
				beta = value;
			}
		}
		return result;
	}
}
