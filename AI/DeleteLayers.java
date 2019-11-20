package AI;

public class DeleteLayers {
//in case the tree gets way too big - a way to delete the first couple of layers and its instances - for space complexity
	
	//delete everything attached to a certain piece of the tree, to keep the used space limited
	public void deleteEverythingAbove(Node<GameState> newRoot, Tree<GameState> tree) {
		//delete the parents and every parent's branch
		//maybe also usable: just a normal set new root statement???
	}
	
	//delete a branch of a certain node
	public void deleteBranch(Node<GameState> start, Tree<GameState> tree) {
		
	}
}
