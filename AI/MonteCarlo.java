package AI;

import src.BoardMethods;

/*
 * Janneke's home-made version :D
 */

public class MonteCarlo {
	
	//this tree stays the same during the whole game - although if it is really possible, it starts from scratch
	Tree<GameState> monteCarloTree; 
	private final double explorationParam = Math.sqrt(2);
	
	//construct the tree, using the initial board state - automatically happens in move
	public MonteCarlo(Node<GameState> root) {
		monteCarloTree = new Tree<GameState>(root);
	}
	
	//do the whole monte-carlo search-> for a set number of seconds-> stop expanding after this is reached
	public Node<GameState> MCTS (int numberSeconds) {
		//set a timer!!!
		boolean cont = true;
		while (cont) {
			selection(monteCarloTree.root);
		}
		
		Node<GameState> chosen = sucChild(monteCarloTree.root);
		changeRoot(chosen);
		return chosen;
	}
	
	//change the current root node to the one that is needed
	public void changeRoot(Node<GameState> changed) {
		monteCarloTree.setRoot(changed);
		changed.parent = null;
	}
	
	//change the node from outside (so, the human player)
	public void changeRootOutside(GameState state) {
		boolean isThere = false;
		int i = 0;
		while (!isThere && i < monteCarloTree.root.children.size()) {
			if (!monteCarloTree.root.isLeaf()) {
				if (BoardMethods.compareHashtables(monteCarloTree.getRoot().children.get(i).data.boardState, state.boardState)) {
					changeRoot(monteCarloTree.getRoot().children.get(i));
					isThere = true;
				}
			}
			i++;
		}
		if (!isThere) {
			GameState newRoot = new GameState(state.boardState,src.GameMethods.changePlayer(monteCarloTree.root.data.evaluateFrom));
			changeRoot(new Node<GameState>(newRoot));
		}
	}
	
	//chooses the node that will be played
	public Node<GameState> selection(Node<GameState> current){
		
		while (!current.isLeaf()) {
			current = sucChild(current);
		}
		//there needs to be more in this method, but i'll do this later
		expansion(current);
		
		return null;
		
	}
	
	//chooses successful child node - similar to the greedy algorithm
	public Node<GameState> sucChild(Node<GameState> parent){
		Node<GameState> bestChild = null;
		double maxEval = Double.NEGATIVE_INFINITY;
		
		for (int i = 0; i < parent.children.size(); i++){
			if (Math.max(maxEval, parent.children.get(i).totValue) > maxEval) {
				maxEval = parent.children.get(i).totValue;
				bestChild = parent.children.get(i);
			}
		}
		return bestChild;
	}
	
	//expands the current node
	public void expansion(Node<GameState> expand) {
		
	}
	
	
	//simulation (also known as roll out)
	public void simulation() {
		
	}
	
	//backpropagation method
	public void backpropagate(Node<GameState> toPropagate) {
		if (!(toPropagate.parent == null)) {
			//these values need to be set to the right ones - there is a formula for this, so that needs to be implemented later... or not??? idk
			toPropagate.nVisits++;
			toPropagate.totValue++; 
			backpropagate(toPropagate.parent);
		}
	}
	
	//wins = number of wins for the node considered after the i-th move
	//simulations = number of simulations for the node considered after the i-th move
	//parentSimulations = total number of simulations after the i-th move run by the parent node of the one considered
	public double calculateUCB(int wins, int simulations, int parentSimulations) {
		return ((wins / simulations) + explorationParam * Math.sqrt(Math.log(parentSimulations)/simulations));
	}
	
}
