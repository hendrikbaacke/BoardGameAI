package AI;

import src.Board;
import src.Hexagon;

import java.util.Hashtable;


public class EvaluationFunction {

	private static double DistancePlayer1 = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("A1").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("A1").centerY, 2));
	private static double DistancePlayer2 = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("A3").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("A3").centerY, 2));
	private static double DistancePlayer3 = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("B2").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("B2").centerY, 2));
	private static double DistancePlayer4 = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("B3").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("B3").centerY, 2));
	private static double DistancePlayer5 = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("C3").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("C3").centerY, 2));
	private static double DistancePlayer6 = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("C4").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("C4").centerY, 2));
	private static double DistancePlayer7 = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("D4").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("D4").centerY, 2));


	private double f1;
	private double f2;
	private double f3;
	private double f4;
	private double f5;
	private double f6;
	private double f7;


	private double w1;
	private double w2;
	private double w3;
	private double w4;
	private double w5;
	private double w6;
	private double w7;


	private GameState gameState;
	private Strategies old;

	public EvaluationFunction(GameState gameState) {

		this.gameState = gameState;

		if (gameState.oldGameState != null) {
			old = new Strategies(gameState.oldGameState);
		}
		Hashtable<String, Hexagon> boardState = gameState.boardState;

		System.out.println(gameState.turn);


		boolean isPlayer1AI = src.GameData.move.player1AI;
		boolean isPlayer2AI = src.GameData.move.player2AI;
		boolean isPlayer3AI = src.GameData.move.player3AI;


		Strategies strategies = new Strategies(gameState);


		//VALUE NORMALIZATION -----------------------------------------------------------------


		//System.out.println(DistancePlayer1+"  "+DistancePlayer2+"  "+DistancePlayer3+"  "+DistancePlayer4+"  "+DistancePlayer5+"  "+"  "+DistancePlayer6+"  "+"  "+DistancePlayer7+"  ");

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

        //Value Range compareMarblesLost : 0 - 1
		double marblesLostEvaluationValue = strategies.compareMarblesLost();
		if (marblesLostEvaluationValue == 0) {
			f6 = 1;
		} else {
			f6 = 0;
		}



		if (gameState.evaluateFrom == 1) {

			ModeDetermination_1 modeDet = new ModeDetermination_1();

			double[] weightArray = modeDet.determineMode_1(f1, strategies.amountOppMarbles(), strategies.amountOwnMarbles());

			w1 = weightArray[0];
			w2 = weightArray[1];
			w3 = weightArray[2];
			w4 = weightArray[3];
			w5 = weightArray[4];
			w6 = weightArray[5];
			w7 = weightArray[6];

			System.out.println("Player1 evaluation");

		}

		if (gameState.evaluateFrom ==2) {

			ModeDetermination_2 modeDet = new ModeDetermination_2();

			double[] weightArray = modeDet.determineMode_2(f1, strategies.amountOppMarbles(), strategies.amountOwnMarbles());

			w1 = weightArray[0];
			w2 = weightArray[1];
			w3 = weightArray[2];
			w4 = weightArray[3];
			w5 = weightArray[4];
			w6 = weightArray[5];
			w7 = weightArray[6];
			System.out.println("Player2 evaluation");

		}

		if (gameState.evaluateFrom == 3) {

			ModeDetermination_3 modeDet = new ModeDetermination_3();

			double[] weightArray = modeDet.determineMode_3(f1, strategies.amountOppMarbles(), strategies.amountOwnMarbles());

			w1 = weightArray[0];
			w2 = weightArray[1];
			w3 = weightArray[2];
			w4 = weightArray[3];
			w5 = weightArray[4];
			w6 = weightArray[5];
			w7 = weightArray[6];
			
			System.out.println("Player3 evaluation");

		}



	}


	/*assigns a numeric value to each GameState in the Tree, based on the linear equation

        w_11 * F_1 +.... +w_n* F_n

        */
	public double evaluate() {

		double finalValue = w1 * f1 + w2 * f2 + w3 * f3 + w4 * f4 + w5 * f5 + w6 * f6 + w7 * f7;
		System.out.println(finalValue);

		System.out.println("----------------------------" + "eval value: " + finalValue);

		return finalValue;
	}
}




