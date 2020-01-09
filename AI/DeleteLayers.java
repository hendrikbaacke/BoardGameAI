package AI;

/*
 * Can delete layers or even whole branches of a tree.
 * Made so it's easy to delete parts of the tree when it gets too big.
 */

public class DeleteLayers {	
	//set new root and disconnect it from the parent
	public static void deleteEverythingAbove(Node<GameState> newRoot, Tree<GameState> tree) {
		tree.setRoot(newRoot);
		newRoot.parent = null;
	}
	
	//delete a branch of a certain node
	public static void deleteBranch(Node<GameState> start) {
		start.children.clear();
	}

}
