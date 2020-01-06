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

		//		Value Range ClosingDist. : 232 max - 111 min

		System.out.println(DistancePlayer1+"  "+DistancePlayer2+"  "+DistancePlayer3+"  "+DistancePlayer4+"  "+DistancePlayer5+"  "+"  "+DistancePlayer6+"  "+"  "+DistancePlayer7+"  ");
/*
			double closingDistanceEvaluationValue = strategies.closingDistance(state);
		System.out.println(DistancePlayer1+"  "+DistancePlayer2+"  "+DistancePlayer3+"  "+DistancePlayer4+"  "+DistancePlayer5+"  "+"  "+DistancePlayer6+"  "+"  "+DistancePlayer7+"  "+closingDistanceEvaluationValue);

				if (closingDistanceEvaluationValue > DistancePlayer1) {
					f1 = 1;
				} else if (closingDistanceEvaluationValue > DistancePlayer2) {
					f1 = 2;
				} else if (closingDistanceEvaluationValue > DistancePlayer3) {
					f1 = 3;
				} else if (closingDistanceEvaluationValue > DistancePlayer4) {
					f1 = 4;
				} else if (closingDistanceEvaluationValue > DistancePlayer5) {
					f1 = 5;
				} else if (closingDistanceEvaluationValue > DistancePlayer6) {
					f1 = 6;
				} else if (closingDistanceEvaluationValue > DistancePlayer7) {
					f1 = 7;
				}else if (closingDistanceEvaluationValue > 0) {
					f1 = 8;
				}else {
					f1=9;
				}
*/
		f1 = strategies.closingDistance(gameState);
		System.out.println("f1= " + f1);


		//      Value Range Cohesion : 64 - 0

		double cohesionEvaluationValue = strategies.cohesion();

		if (cohesionEvaluationValue >= 64) {

			f2 = 10;
			System.out.println("f2= " + f2);
		}

		if (64 > cohesionEvaluationValue && cohesionEvaluationValue >= 60) {

			f2 = 9;
			System.out.println("f2= " + f2);
		}

		if (60 > cohesionEvaluationValue && cohesionEvaluationValue >= 54) {

			f2 = 8;
			System.out.println("f2= " + f2);
		}

		if (54 > cohesionEvaluationValue && cohesionEvaluationValue >= 48) {

			f2 = 7;
			System.out.println("f2= " + f2);
		}

		if (48 > cohesionEvaluationValue && cohesionEvaluationValue >= 42) {

			f2 = 6;
			System.out.println("f2= " + f2);
		}

		if (42 > cohesionEvaluationValue && cohesionEvaluationValue >= 34) {

			f2 = 5;
			System.out.println("f2= " + f2);
		}

		if (34 > cohesionEvaluationValue && cohesionEvaluationValue >= 26) {

			f2 = 4;
			System.out.println("f2= " + f2);
		}

		if (26 > cohesionEvaluationValue && cohesionEvaluationValue >= 18) {

			f2 = 3;
			System.out.println("f2= " + f2);
		}

		if (18 > cohesionEvaluationValue && cohesionEvaluationValue >= 10) {

			f2 = 2;
			System.out.println("f2= " + f2);

		}

		if (10 > cohesionEvaluationValue && cohesionEvaluationValue >= 2) {

			f2 = 1;
			System.out.println("f2= " + f2);

		}

		if (cohesionEvaluationValue < 2) {

			f2 = 0;
			System.out.println("f2= " + f2);

		}


		//       Value Range BreakStrong : 15 - 0

		double breakGroupEvaluationValue = strategies.breakGroup();

		if (breakGroupEvaluationValue >= 15) {

			f3 = 10;
			System.out.println("f3= " + f3);
		}

		if (15 > breakGroupEvaluationValue && breakGroupEvaluationValue >= 13) {

			f3 = 9;
			System.out.println("f3= " + f3);
		}

		if (13 > breakGroupEvaluationValue && breakGroupEvaluationValue >= 11) {

			f3 = 8;
			System.out.println("f3= " + f3);
		}

		if (11 > breakGroupEvaluationValue && breakGroupEvaluationValue >= 9) {

			f3 = 8;
			System.out.println("f3= " + f3);
		}

		if (9 > breakGroupEvaluationValue && breakGroupEvaluationValue >= 7) {

			f3 = 7;
			System.out.println("f3= " + f3);
		}

		if (7 > breakGroupEvaluationValue && breakGroupEvaluationValue >= 5) {

			f3 = 6;
			System.out.println("f3= " + f3);
		}

		if (5 > breakGroupEvaluationValue && breakGroupEvaluationValue >= 4) {

			f3 = 5;
			System.out.println("f3= " + f3);
		}

		if (4 > breakGroupEvaluationValue && breakGroupEvaluationValue >= 3) {

			f3 = 4;
			System.out.println("f3= " + f3);
		}

		if (3 > breakGroupEvaluationValue && breakGroupEvaluationValue >= 2) {

			f3 = 3;
			System.out.println("f3= " + f3);
		}

		if (2 > breakGroupEvaluationValue && breakGroupEvaluationValue >= 1) {

			f3 = 2;
			System.out.println("f3= " + f3);
		}
		if (breakGroupEvaluationValue <= 1) {

			f3 = 1;
			System.out.println("f3= " + f3);
		}

		//       Value Range Strengthen Group : 10 - 0


		double strengthenGroupEvaluationValue = strategies.strengthenGroup();

		if (strengthenGroupEvaluationValue >= 10) {

			f4 = 10;
			System.out.println("f4= " + f4);
		}

		if (10 > strengthenGroupEvaluationValue && strengthenGroupEvaluationValue >= 9) {

			f4 = 9;
			System.out.println("f4= " + f4);
		}

		if (9 > strengthenGroupEvaluationValue && strengthenGroupEvaluationValue >= 8) {

			f4 = 8;
			System.out.println("f4= " + f4);
		}

		if (8 > strengthenGroupEvaluationValue && strengthenGroupEvaluationValue >= 7) {

			f4 = 7;
			System.out.println("f4= " + f4);
		}

		if (7 > strengthenGroupEvaluationValue && strengthenGroupEvaluationValue >= 6) {

			f4 = 6;
			System.out.println("f4= " + f4);
		}

		if (6 > strengthenGroupEvaluationValue && strengthenGroupEvaluationValue >= 5) {

			f4 = 5;
			System.out.println("f4= " + f4);
		}

		if (5 > strengthenGroupEvaluationValue && strengthenGroupEvaluationValue >= 4) {

			f4 = 4;
			System.out.println("f4= " + f4);
		}

		if (4 > strengthenGroupEvaluationValue && strengthenGroupEvaluationValue >= 3) {

			f4 = 3;
			System.out.println("f4= " + f4);
		}

		if (3 > strengthenGroupEvaluationValue && strengthenGroupEvaluationValue >= 2) {

			f4 = 2;
			System.out.println("f4= " + f4);
		}

		if (2 > strengthenGroupEvaluationValue && strengthenGroupEvaluationValue >= 1) {

			f4 = 1;
			System.out.println("f4= " + f4);
		}

		if (strengthenGroupEvaluationValue <= 1) {

			f4 = 0;
			System.out.println("f4= " + f4);
		}


		//       Value Range compareMarblesWon : 0 - 1

		double marblesWonEvaluationValue = strategies.compareMarblesWon();

		if (marblesWonEvaluationValue == 0) {

			f5 = 0;
			System.out.println("f5= " + f5);

		} else {

			f5 = 10;
			System.out.println("f5= " + f5);
		}


		//       Value Range compareMarblesLost : 0 - 1

		double marblesLostEvaluationValue = strategies.compareMarblesLost();

		if (marblesLostEvaluationValue == 0) {

			f6 = 0;

			System.out.println("f6= " + f6);
		} else {

			f6 = -10;
			System.out.println("f6= " + f6);
		}


		f7 = strategies.closingDistanceOpp(gameState);
		System.out.println("f7= " + f7);

		if (gameState.evaluateFrom == 1) {

			ModeDetermination_1 modeDet = new ModeDetermination_1();

			double[] weightArray = modeDet.determineMode_1(f1, strategies.amountOppMarbles());
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

			double[] weightArray = modeDet.determineMode_2(f1, strategies.amountOppMarbles());

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

			double[] weightArray = modeDet.determineMode_3(f1, f2);

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




