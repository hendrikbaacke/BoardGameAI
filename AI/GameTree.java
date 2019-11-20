package AI;

public class GameTree {
	Tree<GameState> gameTree;
	
	public GameTree(Node<GameState> root) {
		gameTree = new Tree<GameState>(root);
	}
	
	public void buildFullTree(int layers) {
		//builds the entire tree we need (can be a LOT of nodes)
	}
	
	//can add more methods especially for our tree
}
