package AI;


public class EvaluationFunction {

    /*assigns a numeric value to each GameState in the Tree, based on the linear equation

    w_11 * F_1 +.... +w_n* F_n

    */
	private double f1;
	private double f2; 
	private double f3;
	private double f4;
	private double f5;
	private double f6;
	//private double f7;
	
	private double w1;
	private double w2;
	private double w3;
	private double w4;
	private double w5;
	private double w6;
	//private double w7;
	
	public EvaluationFunction(Node<GameState> node)
	{
		boolean isPlayer1AI=src.Board.move.player1AI;
		GameState gameState=(GameState) node.returnData();
		
		Strategies strategies=new Strategies(node.returnData().boardState, isPlayer1AI);
		
		 f1=strategies.closingDistance(gameState);
		 f2=strategies.cohesion(gameState);
		 f3=strategies.breakGroup(gameState);
		 f4=strategies.strengthenGroup(gameState);

		 f5=strategies.amountOppMarbles(gameState,isPlayer1AI);
		 f6=strategies.amountOwnMarbles(gameState,isPlayer1AI);

		 //f7 =strategies.checkKillMove(gameState);
		 
		 ModeDetermination modeDet=new ModeDetermination();
		 double[] weightArray=modeDet.determineMode(f1, f2);
		 w1=weightArray[0];
		 w2=weightArray[1];
		 w3=weightArray[2];
		 w4=weightArray[3];
		 w5=weightArray[4];
		 w6=weightArray[5];
		 //w7 weightArray[6];
				 
			
	}
	
	public double evaluate() {
		double finalValue= w1*f1 + w2*f2 + w3*f3 + w4*f4 + w5*f5 + w6*f6;
		return finalValue;
	}


}
