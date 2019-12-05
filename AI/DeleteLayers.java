package AI;

//should be fine

public class DeleteLayers {
//in case the tree gets way too big - a way to delete the first couple of layers and its instances - for space complexity
	
	public static void deleteEverythingAbove(Node<GameState> newRoot, Tree<GameState> tree) {
		//set new root and disconnect it from the parent
		tree.setRoot(newRoot);
		newRoot.parent = null;
	}
	
	//delete a branch of a certain node
	public static void deleteBranch(Node<GameState> start) {
		//delete a node and cascade - if parent removes this node, there will be no access anymore - so it's deleted
		start.children.clear();
	}

}
