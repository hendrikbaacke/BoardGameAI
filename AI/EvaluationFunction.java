package AI;


import src.Board;

public class EvaluationFunction {

	private static double DistancePlayer2_early =  Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX- Board.hashBoard.get("H7").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("H7").centerY, 2));
	// = 200.94 on MacBookPro 15 inch
	private static double DistancePlayer2_mid =  Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX- Board.hashBoard.get("G6").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("G6").centerY, 2));
	// = 131.55 "
	private static double DistancePlayer2_late =  Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX- Board.hashBoard.get("F5").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("F5").centerY, 2));
	// = 76 "

	private static double DistancePlayer1_early =  Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX- Board.hashBoard.get("B3").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("B3").centerY, 2));
	// = 200.94 on MacBookPro 15 inch
	private static double DistancePlayer1_mid =  Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX- Board.hashBoard.get("C4").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("C4").centerY, 2));
	// = 131.55 "
	private static double DistancePlayer1_late =  Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX- Board.hashBoard.get("D5").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("D5").centerY, 2));
	// = 76 "

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



		//VALUE NORMALIZATION -----------------------------------------------------------------

		//		Value Range ClosingDist. : 232 max - 111 min



			double closingDistanceEvaluationValue = strategies.closingDistance(state);


			if (isPlayer2AI == true) {

				if (closingDistanceEvaluationValue >= DistancePlayer2_early) {

					f1 = -10;

				}

				if (DistancePlayer2_early > closingDistanceEvaluationValue || closingDistanceEvaluationValue > DistancePlayer2_mid) {

					f1 = -8;
				}

				if (DistancePlayer2_mid >= closingDistanceEvaluationValue || closingDistanceEvaluationValue > (DistancePlayer2_late + 40)) {

					f1 = -2;
				}

				if ((DistancePlayer2_late + 40) >= closingDistanceEvaluationValue) {

					f1 = 0;
				}


			}

			if (isPlayer1AI == true) {

				if (closingDistanceEvaluationValue >= DistancePlayer1_early) {

					f1 = -10;
				}

				if (DistancePlayer1_early > closingDistanceEvaluationValue || closingDistanceEvaluationValue > DistancePlayer1_mid) {

					f1 = -8;
				}

				if (DistancePlayer1_mid >= closingDistanceEvaluationValue || closingDistanceEvaluationValue > (DistancePlayer1_late + 40)) {

					f1 = -2;
				}

				if ((DistancePlayer1_late + 40) >= closingDistanceEvaluationValue) {

					f1 = 0;
				}

			}


		//      Value Range Cohesion : 64 - 0


		double cohesionEvaluationValue = strategies.cohesion();

			if (cohesionEvaluationValue>= 64){

				f2 = 10;
			}

			if (64 > cohesionEvaluationValue || cohesionEvaluationValue >= 60){

				f2 = 9;
			}

			if (60 > cohesionEvaluationValue || cohesionEvaluationValue >= 54){

				f2 = 8;

			}

			if (54 > cohesionEvaluationValue || cohesionEvaluationValue >= 48){

				f2 = 7;
			}

			if (48 > cohesionEvaluationValue || cohesionEvaluationValue >= 42){

				f2 = 6;

			}

			if (42 > cohesionEvaluationValue || cohesionEvaluationValue >= 34){

				f2 = 5;

			}

			if (34 > cohesionEvaluationValue || cohesionEvaluationValue >= 26){

				f2 = 4;

			}

			if (26 > cohesionEvaluationValue || cohesionEvaluationValue >= 18){

				f2 = 3;

			}

			if (18 > cohesionEvaluationValue || cohesionEvaluationValue >= 10){

				f2 = 2;

			}

			if (10 > cohesionEvaluationValue || cohesionEvaluationValue >= 2){

				f2 = 1;

			}

			if (cohesionEvaluationValue < 2){

				f2 = 0;

			}


		//       Value Range BreakStrong : 15 - 0

		double breakGroupEvaluationValue = strategies.breakGroup();

			if (breakGroupEvaluationValue >= 15){

				f3 = 10;
			}

			if (15 > breakGroupEvaluationValue || breakGroupEvaluationValue >= 13){

				f3 = 9;
			}

			if (13 > breakGroupEvaluationValue || breakGroupEvaluationValue >= 11){

				f3 = 8;
			}

			if (11 > breakGroupEvaluationValue || breakGroupEvaluationValue >= 9){

				f3 = 8;
			}

			if (9 > breakGroupEvaluationValue || breakGroupEvaluationValue >= 7){

				f3 = 7;
			}

			if (7 > breakGroupEvaluationValue || breakGroupEvaluationValue >= 5){

				f3 = 6;
			}

			if (5 > breakGroupEvaluationValue || breakGroupEvaluationValue >= 4){

				f3 = 5;
			}

			if (4 > breakGroupEvaluationValue || breakGroupEvaluationValue >= 3){

				f3 = 4;
			}

			if (3 > breakGroupEvaluationValue || breakGroupEvaluationValue >= 2){

				f3 = 3;
			}

			if (2 > breakGroupEvaluationValue || breakGroupEvaluationValue >= 1){

				f3 = 2;
			}
			if (breakGroupEvaluationValue <= 1){

				f3 = 1;
			}

		//       Value Range Strengthen Group : 10 - 0


		double strengthenGroupEvaluationValue = strategies.strengthenGroup();

			if (strengthenGroupEvaluationValue >= 10) {

				f4 = 10;
			}

			if (10 > strengthenGroupEvaluationValue || strengthenGroupEvaluationValue >= 9) {

				f4 = 9;
			}

			if (9 > strengthenGroupEvaluationValue || strengthenGroupEvaluationValue >= 8) {

				f4 = 8;
			}

			if (8 > strengthenGroupEvaluationValue || strengthenGroupEvaluationValue >= 7) {

				f4 = 7;
			}

			if (7 > strengthenGroupEvaluationValue || strengthenGroupEvaluationValue >= 6) {

				f4 = 6;
			}

			if (6 > strengthenGroupEvaluationValue || strengthenGroupEvaluationValue >= 5) {

				f4 = 5;
			}

			if (5 > strengthenGroupEvaluationValue || strengthenGroupEvaluationValue >= 4) {

				f4 = 4;
			}

			if (4 > strengthenGroupEvaluationValue || strengthenGroupEvaluationValue >= 3) {

				f4 = 3;
			}

			if (3 > strengthenGroupEvaluationValue || strengthenGroupEvaluationValue >= 2) {

				f4 = 2;
			}

			if (2 > strengthenGroupEvaluationValue || strengthenGroupEvaluationValue >= 1) {

				f4 = 1;
			}

			if (strengthenGroupEvaluationValue <= 1) {

				f4 = 0;
			}



		//       Value Range compareMarblesWon : 0 - 1

		double marblesWonEvaluationValue = strategies.compareMarblesWon();

			if (marblesWonEvaluationValue == 0){

				f5 = -10;                                 // I argue, that setting the negative aspect in both counting methods to -10 rather than 0 makes more sense, so that we can steer with the weights (with 0 we cannot do anything anymore)
			}

			else {

				f5 = 10;
			}


		//       Value Range compareMarblesLost : 0 - 1

		double marblesLostEvaluationValue = strategies.compareMarblesLost();

			if (marblesLostEvaluationValue == 0){

				f6 = 10;
			}

			else {

				f6 = -10;
			}



		if (isPlayer1AI == true) {

			ModeDetermination_1 modeDet = new ModeDetermination_1();

			double[] weightArray = modeDet.determineMode_1(f1, f2);
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




	/*assigns a numeric value to each GameState in the Tree, based on the linear equation

        w_11 * F_1 +.... +w_n* F_n

        */
		public double evaluate(){

			double finalValue = w1 * f1 + w2 * f2 + w3 * f3 + w4 * f4 + w5 * f5 + w6 * f6;

			return finalValue;
		}



}
