package AI;

import AI.WeightOptimisation.EvolutionaryAlgo;
import AI.WeightOptimisation.GameSimulation.GameEnvironment;
import src.Board;
import src.Hexagon;


import java.util.Hashtable;


public class EvaluationFunction {

	private double f1;
	private double f2;
	private double f3;
	private double f4;
	private double f5;
	private double f6;
	private double f7;
	private double f8;


	private double w1;
	private double w2;
	private double w3;
	private double w4;
	private double w5;
	private double w6;
	private double w7;
	private double w8;


	private GameState gameState;
	private Strategies old;

	public static boolean AITestingON = false;
	public  ModeDetermination modeDet;

	public EvaluationFunction(GameState gameState) {

		this.gameState = gameState;

		if (gameState.oldGameState != null) {
			old = new Strategies(gameState.oldGameState);
		}
		Hashtable<String, Hexagon> boardState = gameState.boardState;

		//System.out.println(gameState.turn);


		boolean isPlayer1AI = src.GameData.move.player1AI;
		boolean isPlayer2AI = src.GameData.move.player2AI;

		Strategies strategies = new Strategies(gameState);


		//VALUE NORMALIZATION -----------------------------------------------------------------


		double Max = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("A1").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("A1").centerY, 2));
		double Min = (Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("D4").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("D4").centerY, 2))*6+Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("C4").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("C4").centerY, 2))*2)/9;
		//scaling between 0-1
		f1 = (strategies.closingDistance(gameState)-Max)/(Min-Max);
		//Min and Max are swapped
		f7 = (strategies.closingDistanceOpp(gameState)-Min)/(Max-Min);

		//      Value Range cohesion : 64 - 0
		f2 = strategies.cohesion()/64;

		//       Value Range breakGroup : 10 - 0
		f3 = strategies.breakGroup()/10;

		//       Value Range strengthenGroup : 32 - 0
		f4 = strategies.strengthenGroup()/32;

		//       Value Range compareMarblesWon : 1 - 0
		f5 = strategies.compareMarblesWon();

		//Marbles in danger
		f8 = 1 - strategies.danger()/strategies.amountOwnMarbles();
		

        //Value Range compareMarblesLost : 0 - 1
		double marblesLostEvaluationValue = strategies.compareMarblesLost();
		if (marblesLostEvaluationValue == 0) {
			f6 = 1;
		} else {
			f6 = 0;
		}


		if(!AITestingON) {
			String Name = null;
			if (gameState.evaluateFrom == 1) {
				Name = "Neutral";
				//System.out.println("Player1 evaluation");
			}
			if (gameState.evaluateFrom == 2) {
				Name = "Aggressive";
				//System.out.println("Player2 evaluation");
			}
			if (gameState.evaluateFrom == 3) {
				Name = "Defensive";
				//System.out.println("Player3 evaluation");
			}

			//modeDet = new ModeDetermination(Name);
			modeDet = new ModeDetermination(ReadMatrix.ReadIn(System.getProperty("user.dir")+ReadMatrix.Slash+"AI"+ReadMatrix.Slash+"StartingAI"+ReadMatrix.Slash+"Final"+".txt"));
		}else{
			if (gameState.evaluateFrom == 1) {
				modeDet = new ModeDetermination(GameEnvironment.player1);
			}else{
				modeDet = new ModeDetermination(GameEnvironment.player2);
			}
		}
			normalization(modeDet.determineMode(f1, strategies.amountOppMarbles(), strategies.amountOwnMarbles()));

			double[] weightArray = modeDet.determineMode(f1, strategies.amountOppMarbles(), strategies.amountOwnMarbles());

			w1 = weightArray[0];
			w2 = weightArray[1];
			w3 = weightArray[2];
			w4 = weightArray[3];
			w5 = weightArray[4];
			w6 = weightArray[5];
			w7 = weightArray[6];
			w8 = weightArray[7];

	}


	/*assigns a numeric value to each GameState in the Tree, based on the linear equation
        w_11 * F_1 +.... +w_n* F_n
        */
	public double evaluate() {

		double finalValue = w1 * f1 + w2 * f2 + w3 * f3 + w4 * f4 + w5 * f5 + w6 * f6 + w7 * f7 + w8 *f8;
		//System.out.println(finalValue);

		//System.out.println("----------------------------" + "eval value: " + finalValue);

		return finalValue;
	}

	private void normalization(double[] weights){
		double sum = 0;
		for (int i=0; i<weights.length; i++){
			sum+=weights[i];
		}
		for (int j=0; j<weights.length; j++){
			weights[j] = weights[j]/sum;
		}
	}


}