package AI;

import src.Board;
import src.Move;

public class AlphaBeta {
	
	private static final Integer MAX_DEPTH = 3;
	private static final Integer INF = 1000;

	private static AlphaBeta instance;
	private Game game;
	private Color selfColor;

	private AlphaBeta(Game game, Color selfColor) {
		      this.game = game;
		      this.selfColor = selfColor;
		   }

	public static void init(Game game, Color AIColor) {
		if (AlphaBeta.instance == null)
			AlphaBeta.instance = new AlphaBeta(game, AIColor);
	}

	public static AlphaBeta getInstance() {
		return AlphaBeta.instance;
	}

	public Move getBestMove(Color current) {
		Board board = new Board(this.game.getBoard());
		Move bestMove = null;
		Integer best = -100;
		Set<Move> moves = board.getPossibleMoves(current);
		for (Move m : moves) {
			board.apply(m);
			Integer score = negaScout(board, current.other(), MAX_DEPTH - 1,
					-INF, +INF);
			if (score > best) {
				best = score;
				bestMove = m;
			}
			board.revert(m);
		}
		return bestMove;
	}

	private Integer negaScout(Board board, Color current, Integer depth,
			Integer alpha, Integer beta) {
		if (depth > 0) {
			Integer best = beta;
			for (Move m : board.getPossibleMoves(current)) {
				board.apply(m);
				Integer score = -negaScout(board, current.other(), depth - 1,
						-best, -alpha);
				if (alpha < score && score < beta)
					score = -negaScout(board, current.other(), depth - 1, -beta,
							-alpha);
				alpha = Math.max(alpha, score);
				if (alpha >= beta)
					return alpha;
				best = alpha + 1;
				board.revert(m);
			}
			return best;
		} else {
			return ((current == selfColor) ? 1 : -1)
					* this.evaluateBoard(board, current);
		}
	}

	private Integer evaluateBoard(Board board, Color player) {
		Integer good = board.ballsCount(player);
		Integer bad = board.ballsCount(player.other());
		return good - bad;
	}

	public Color getColor() {
		return selfColor;
	}

}
