package AI;

import java.util.List;

import javafx.scene.paint.Color;
import src.Board;
import src.MarbleStorage;
import src.Move;

public class AlphaBeta {
	
	private static final Integer MAX_DEPTH = 2;
	private static final Integer INF = Integer.MAX_VALUE;
	private static final Integer negINF = Integer.MIN_VALUE;

	private Move move = new Move();
	private static AlphaBeta instance;
	private int PlayerTurn;
	private Node<GameState> root;
	private GameTree game= new GameTree(root);


	public AlphaBeta(GameTree game, int PlayerTurn) {
		      this.game = game;
		      this.PlayerTurn = PlayerTurn;
		   }

	public static void init(GameTree game, int AIColor) {
		if (AlphaBeta.instance == null)
			AlphaBeta.instance = new AlphaBeta(game, AIColor);
	}

	public static AlphaBeta getInstance() {
		return AlphaBeta.instance;
	}

	public Node<GameState> getBestMove(int currentPlayer) {
		Node<GameState> bestMove = null;
		Integer best = -100;
		List<Node<GameState>> moves = game.findAtDepth(1);
		for (Node<GameState> m : moves) {
			Integer score = alphabeta(game, other(), MAX_DEPTH - 1,
					negINF, INF);
			if (score > best) {
				best = score;
				bestMove = m;
			}
		}
		System.out.print("alpha beta is working, ");

		return bestMove;
	}

	private Integer alphabeta(GameTree game2, int currentPlayer, Integer depth,
			Integer alpha, Integer beta) {
		if (depth > 0) {
			Integer best = beta;
			List<Node<GameState>> moves2 = game2.findAtDepth(currentPlayer);
			for (Node<GameState> m : moves2) {
				Integer score = -alphabeta(game2, other(), depth - 1,
						-best, -alpha);
				if (alpha < score && score < beta)
					score = -alphabeta(game2, other(), depth - 1, -beta,
							-alpha);
				alpha = Math.max(alpha, score);
				if (alpha >= beta)
					return alpha;
				best = alpha + 1;
				
			}
			return best;
			
		} else {
			return ((currentPlayer == PlayerTurn) ? 1 : -1)
					* this.evaluateBoard(game2, currentPlayer);
		}
	}

	private Integer evaluateBoard(GameTree game2, int player) {
		Integer good = MarbleStorage.ballsCount(player);
		Integer bad = MarbleStorage.ballsCount(other());
		return good - bad;
	}

	public int getColor() {
		return PlayerTurn;
	}
	public int other() {
		if(PlayerTurn ==1) {
			return 2;
		}
		else if(PlayerTurn ==2) {
			return 1;
		}
		else return PlayerTurn;
		
	}

}
