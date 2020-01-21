package AI;

/*
 * Contains an alpha beta method.
 * Stores the best node (the move the current player does).
 */ 

public class AlphaBeta{
	
	Node<GameState> node = null;

	public AlphaBeta() { }
	
	public double performAB(Node<GameState> node, int depth, double alpha, double beta, boolean max) {
		double value = 0;
		if (depth == 0) {
			return node.returnData().evaluatedValue;
		}
		
		if(max) {
			value = -Double.MAX_VALUE;
			
			for (Node<GameState> node2 : node.children) {
				value = Math.max(value, performAB(node2, depth-1, alpha, beta, false));
				System.out.println("value " + value);
				double oldAlpha = alpha;
				alpha = Math.max(alpha, value);
				
				if (Math.max(alpha, value) != oldAlpha || this.node == null) {
					this.node = node2;
				}
				if (alpha >= beta) {
					break;
				}
			}
			return value;
		} else {
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
}