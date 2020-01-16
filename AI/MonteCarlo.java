package AI;

import java.util.ArrayList;
import java.util.Random;

import src.BoardMethods;
import src.GameMethods;

/*
 * Janneke's home-made version :D - needs to be further implemented and then probably fixed.
 * Testing is currently not possible due to some errors in the evolution thingy
 */

public class MonteCarlo {
	
	//this tree stays the same during the whole game - although if it is really possible, it starts from scratch
	Tree<GameState> monteCarloTree; 
	Random random = new Random();
	private final static double explorationParam = Math.sqrt(2);
	
	//construct the tree, using the initial board state - automatically happens in move
	public MonteCarlo(Node<GameState> root) {
		monteCarloTree = new Tree<GameState>(root);
	}
	
	//do the whole monte-carlo search-> for a set number of seconds-> stop expanding after this is reached
	public Node<GameState> MCTS (int numberSeconds) {
		//for a certain amount of time, this is performed
		
		double curr = System.currentTimeMillis();
		while ((System.currentTimeMillis() - curr) < (numberSeconds*1000)) {
			selection(monteCarloTree.root);
		}
		
		Node<GameState> chosen = sucChild(monteCarloTree.root);
		changeRoot(chosen);
		return chosen;
	}
	
	//start from the root
	//select successive child-nodes, until a lead node is reached
	//then, if it terminates the tree, it backpropagates or expands
	public void selection(Node<GameState> current){
		while (!current.isLeaf()) {
			current = sucChild(current);
		}
		if (!current.data.terminal) {
			expansion(current);
		}
	}
	
	//chooses successful child node - similar to the greedy algorithm
	public Node<GameState> sucChild(Node<GameState> parent){
		Node<GameState> bestChild = null;
		double maxEval = Double.NEGATIVE_INFINITY;
		ArrayList<Node<GameState>> multiple = new ArrayList<>();
		
		for (int i = 0; i < parent.children.size(); i++){
			if (Math.max(maxEval, parent.children.get(i).totValue) > maxEval) {
				multiple.clear();
				maxEval = parent.children.get(i).totValue;
				bestChild = parent.children.get(i);
			}
			else if(Math.max(maxEval, parent.children.get(i).totValue) == maxEval){
				multiple.add(parent.children.get(i));
			}
		}
		
		//fair coin flip!!
		if(multiple.size()> 1) {
			bestChild = multiple.get(random.nextInt(multiple.size()));
		}
		
		return bestChild;
	}
	
	/*
	 * Next two methods still need to be finished - expansion and simulation. (idk what the difference is tbh)
	 */
	
	//expands the current node
	public void expansion(Node<GameState> expand) {
		AddNodes.addForOne(expand);
		//randomly choose one of those children and expand that one
		
	}
	
	
	//simulation (also known as roll out)
	public void simulation() {
		
	}
	
	//backpropagation method - automatically calculate all the new values for it!!
	public void backpropagate(Node<GameState> toPropagate, int winningplayer) {
		if (!(toPropagate.parent == null)) {
			
			toPropagate.nVisits++;
			//if it wins, add the win as well
			if (GameMethods.changePlayer(toPropagate.returnData().turn) == winningplayer) {
				toPropagate.wins++;
			}
			toPropagate.calcMCTSvalue();
			backpropagate(toPropagate.parent, winningplayer);
		}
	}
	
	//wins = number of wins for the node considered after the i-th move
	//simulations = number of simulations for the node considered after the i-th move
	//parentSimulations = total number of simulations after the i-th move run by the parent node of the one considered
	public static double calculateUCB(int wins, int simulations, int parentSimulations) {
		return ((wins / simulations) + explorationParam * Math.sqrt(Math.log(parentSimulations)/simulations));
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
}