package AI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Basic node structure.
 */

public class Node<T> {

	T data;
    Node<T> parent;
    List<Node<T>> children;
	public int nVisits;
	public double totValue;

    public Node(T data) {
        this.data = data;
        this.children = new ArrayList<Node<T>>();
    }

    public void addChild(T childData) {
        Node<T> child = new Node<T>(childData);
        child.parent = this;
        this.children.add(child);
    }
    
    public void removeChildOnIndex(int i) {
    	this.children.remove(i);
    }
    
    public void setData(T data) {
    	this.data = data;
    }
    
    public T returnData(){
    	return data;
    }

    //needs to be done
	public Node<GameState> select() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	public void expand() {
		// TODO Auto-generated method stub
		
	}
	
}
