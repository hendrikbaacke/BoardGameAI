package AI;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MCTS {
		public Node<GameState> cur;
		public void MCTsearch(Node<GameState> cur) {
			cur= null;
		}
	    static Random r = new Random();
	    //static int nActions = 5;
	    static double epsilon = 1e-6;

	    List<Node<GameState>> childlist;
	    double nVisits, totValue;
	    //so here we want to select all possible moves 
	    public void selectAction() {
	        List<Node<GameState>> visited = new LinkedList<Node<GameState>>();
	        //Node<GameState> cur = this;
	        cur = new Node(visited);
	        visited.add(cur);
	        while (!cur.isLeaf()) {
	            cur = cur.select();
	            visited.add(cur);
	            System.out.println(cur +"added to visited");
	        }
	        cur.expand();
	        Node<GameState> newNode = cur.select();
	        visited.add(newNode);
	        double value = rollOut(newNode);
	        for (Node<GameState> node : visited) {
	            // would need extra logic for n-player game
	            //?? here how can we update the value of the node 
	            //>node.updateStats(value);
	            updateStats(value,node);

	        }	            

	    }

	    public void expand(Node<GameState> current) {
	        //how to get the childrens of a state ?
	        //so here i want to get all possible moves there is a method for that 
	        //because all possible moves are the childrens or the actions
	    	AddNodes.addForOne(current); //creates all the children nodes of a certain node
	        List<Node<GameState>> childrensOfCurrent = current.children;
	        for(Node<GameState> g :childrensOfCurrent)
	        {childlist.add(g);}
	        System.out.println( "current node is expanded");
	        }
	    

	    private Node<GameState> select() {
	        Node<GameState> selected = null;
	        double bestValue = Double.MIN_VALUE;
	        for (Node<GameState> c : childlist) {
	            double uctValue = c.totValue / (c.nVisits + epsilon) +
	                       Math.sqrt(Math.log(nVisits+1) / (c.nVisits + epsilon)) +
	                           r.nextDouble() * epsilon;
	            // small random number to break ties randomly in unexpanded nodes
	            if (uctValue > bestValue) {
	                selected = c;
	                bestValue = uctValue;
	            }
	        }
	        return selected;
	    }

	    public boolean isLeaf() {
	        return childlist == null;
	    }

	    public double rollOut(Node<GameState> tn) {
	        // ultimately a roll out will end in some value
	        // assume for now that it ends in a win or a loss
	        // and just return this at random
	        return r.nextInt(2);
	    }

	    public void updateStats(double value,Node<GameState> node) {
	        //add these two variables to the node class so they can be always calcualted
	        //?? ask if this can be correct
	        node.nVisits++;
	        node.totValue =  (node.totValue +  value);
	    }

	    public int arity() {
	        return childlist == null ? 0 : childlist.size();
	    }

		public Node<GameState> findBestMove() {
			// TODO Auto-generated method stub
			return null;
		}
}

