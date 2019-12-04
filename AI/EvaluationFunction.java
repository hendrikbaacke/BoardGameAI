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
	
	public EvaluationFunction(GameState state)
	{
		boolean isPlayer1AI=src.Board.move.player1AI;
		
		Strategies strategies=new Strategies(state.boardState, isPlayer1AI);
		
		 f1=strategies.closingDistance(state);
		 f2=strategies.cohesion(state);
		 f3=strategies.breakGroup(state);
		 f4=strategies.strengthenGroup(state);

		 f5=strategies.amountOppMarbles(state,isPlayer1AI);
		 f6=strategies.amountOwnMarbles(state,isPlayer1AI);

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
