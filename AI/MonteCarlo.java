package AI;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import src.BoardMethods;
import src.GameMethods;
import src.Hexagon;

/*
 * Needs to be tested.
 */

public class MonteCarlo {
	
	//this tree stays the same during the whole game - although if it is really possible, it starts from scratch
	Tree<GameState> monteCarloTree; 
	Random random = new Random();
	private final static double explorationParam = Math.sqrt(2);
	private boolean chooseForAIMove;
	
	//need to cutoff when the game takes too long
	private final int cutoff = 200;
	private int currentNode = 1;
	
	//construct the tree, using the initial board state - automatically happens in move
	public MonteCarlo(Node<GameState> root) {
		monteCarloTree = new Tree<GameState>(root);
	}
	
	//do the whole monte-carlo search-> for a set number of seconds-> stop expanding after this is reached
	public Node<GameState> MCTS (int numberSeconds) {
		//for a certain amount of time, this is performed
		chooseForAIMove = false;
		
		//will still finish current simulation
		double curr = System.currentTimeMillis();
		while ((System.currentTimeMillis() - curr) < (numberSeconds*1000)) {
			System.out.println("cutoff " + currentNode);
			currentNode = 1;
			selection(monteCarloTree.root);
		}
		
		chooseForAIMove = true;
		Node<GameState> chosen = sucChild(monteCarloTree.root);
		//changeRoot(chosen);
		
		return chosen;
	}
	
	//start from the root
	//select successive child-nodes, until a lead node is reached
	//then, if it terminates the tree, it backpropagates or expands
	public void selection(Node<GameState> current){
		while (!current.isLeaf() && currentNode < cutoff) {
			currentNode++;
			current = sucChild(current);
		}
		if (!current.data.terminal && currentNode < cutoff) {
			currentNode++;
			simulation(current);
		}
	}
	
	//chooses successful child node - similar to the greedy algorithm
	public Node<GameState> sucChild(Node<GameState> parent){
		Node<GameState> bestChild = null;
		double maxEval = Double.NEGATIVE_INFINITY;
		ArrayList<Node<GameState>> multiple = new ArrayList<>();
		
		for (int i = 0; i < parent.children.size(); i++){
			if (Math.max(maxEval, parent.children.get(i).totValue) > maxEval && (!chooseForAIMove || maxEval < Double.POSITIVE_INFINITY )) {
				multiple.clear();
				maxEval = parent.children.get(i).totValue;
				bestChild = parent.children.get(i);
			}
			else if(Math.max(maxEval, parent.children.get(i).totValue) == maxEval && (!chooseForAIMove || maxEval < Double.POSITIVE_INFINITY )){
				multiple.add(parent.children.get(i));
			}
		}
		
		//fair coin flip!!
		if(multiple.size()> 1) {
			bestChild = multiple.get(random.nextInt(multiple.size()));
		}
		
		return bestChild;
	}
	
	//expands the current node
	public void expansion(Node<GameState> expand) {
		AddNodes.addForOne(expand);
		//we can also make one that adds less nodes?? if this one is too expensive
	}
	
	//simulation (also known as roll out)
	public void simulation(Node<GameState> current) {
		expansion(current);
		Node<GameState> newChoice = sucChild(current);
		
		if (newChoice.data.terminal || currentNode >= cutoff) {
			System.out.println("backpropagate");
			backpropagate(newChoice, newChoice.data.winner);
		}
		else {
			currentNode ++;
			simulation(newChoice);
		}
	}
	
	//backpropagation method - automatically calculate all the new values for it!!
	public void backpropagate(Node<GameState> toPropagate, int winningplayer) {
		if (!(toPropagate.parent == null)) {
			
			toPropagate.nVisits++;
			//if it wins, add the win as well
			if (winningplayer ==0) {
				toPropagate.wins =  (toPropagate.wins + 0.5);
			}
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
	public static double calculateUCB(double wins, int simulations, int parentSimulations) {
		return ((wins / simulations) + explorationParam * Math.sqrt(Math.log(parentSimulations)/simulations));
	}
	
	
	//change the current root node to the one that is needed
	public void changeRoot(Node<GameState> changed) {
		monteCarloTree.setRoot(changed);
		changed.parent = null;
	}
		
	//change the node from outside (so, the human player)
	public void changeRootOutside(Hashtable<String, Hexagon> board) {
		boolean isThere = false;
		int i = 0;
		while (!isThere && i < monteCarloTree.root.children.size()) {
			if (!monteCarloTree.root.isLeaf()) {
				if (BoardMethods.compareHashtables(monteCarloTree.getRoot().children.get(i).data.boardState, board)) {
					changeRoot(monteCarloTree.getRoot().children.get(i));
					isThere = true;
				}
			}
			i++;
		}
		if (!isThere) {
			GameState newRoot = new GameState(board,src.GameMethods.changePlayer(monteCarloTree.root.data.evaluateFrom));
			changeRoot(new Node<GameState>(newRoot));
		}
		System.out.println("changed root");
	}
}

