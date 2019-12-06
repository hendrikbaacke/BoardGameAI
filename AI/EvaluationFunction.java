package AI;


import src.Board;

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


	private double w1;
	private double w2;
	private double w3;
	private double w4;
	private double w5;
	private double w6;

	public EvaluationFunction(GameState state) {

		boolean isPlayer1AI = src.Board.move.player1AI;

		boolean isPlayer2AI = src.Board.move.player2AI;

		boolean isPlayer3AI = src.Board.move.player3AI; 

		Strategies strategies = new Strategies(state);

		f1 = strategies.closingDistance(state);
		f2 = strategies.cohesion();
		f3 = strategies.breakGroup();
		f4 = strategies.strengthenGroup();

		//just returns the size
		f5 = strategies.compareMarblesWon();
		f6 = strategies.compareMarblesLost();


		if (isPlayer1AI == true) {

			ModeDetermination_1 modeDet = new ModeDetermination_1();

			double[] weightArray = modeDet.determineModeAggressive(f1, f2);
			w1 = weightArray[0];
			w2 = weightArray[1];
			w3 = weightArray[2];
			w4 = weightArray[3];
			w5 = weightArray[4];
			w6 = weightArray[5];
		}

		if (isPlayer2AI == true) {

			ModeDetermination_2 modeDet = new ModeDetermination_2();

			double[] weightArray = modeDet.determineMode_2(f1, f2);

			w1 = weightArray[0];
			w2 = weightArray[1];
			w3 = weightArray[2];
			w4 = weightArray[3];
			w5 = weightArray[4];
			w6 = weightArray[5];


		}

		if (isPlayer3AI == true) {

			ModeDetermination_3 modeDet = new ModeDetermination_3();

			double[] weightArray = modeDet.determineMode_3(f1, f2);

			w1 = weightArray[0];
			w2 = weightArray[1];
			w3 = weightArray[2];
			w4 = weightArray[3];
			w5 = weightArray[4];
			w6 = weightArray[5];


		}



	}


		public double evaluate(){

			double finalValue = w1 * f1 + w2 * f2 + w3 * f3 + w4 * f4 + w5 * f5 + w6 * f6;

			return finalValue;
		}



}
