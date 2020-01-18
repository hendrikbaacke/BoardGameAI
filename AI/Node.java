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
	public int nVisits = 0;
	public double wins = 0;
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

	public boolean isLeaf() {
		if (children.size() == 0) {
			return true;
		}
		return false;
	}
	
	public void calcMCTSvalue() {
		this.totValue =  MonteCarlo.calculateUCB(wins, nVisits, parent.nVisits);
	}
	
}
