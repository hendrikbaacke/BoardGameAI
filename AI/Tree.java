package AI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//basic tree data structure

public class Tree<T> {
	private Node<T> root;
	
	public Tree (Node<T> root) {
		this.root = root;
	}
	
	public Node<T> getRoot() {
		return root;
	}
	
	public void setRoot(Node<T> newRoot) {
		this.root = newRoot;
	}
	
}
