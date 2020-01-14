package AI;

import src.Board;

public class ModeDetermination_3 {

	//Defensive agent

	// all weigths should be positive, the function values f1..f7 have been instead altered according to heuristic observations

	public static int Counter = 1;

	// { Distance , Cohesion , Break , strength , Won , Lost , DistanceOpp }

	private double [][] weightMatrix_AI_3 = {   {  1  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  },   //get to the middle

												{  1  ,  1  ,  0.5  ,  0.7  ,  2  ,  3  ,  0.3  },   //bit def

												{  0.5  ,  0.7  ,  0.8 ,  1  ,  3  ,  2  ,  0.7  },   //bit agg

												{  0  ,  0.5  ,  0.5  ,  0.5  ,  3  ,  1  ,  1  },   //Very aggressive

												{  1  ,  1  ,  0.4  ,  0.2  ,  1  ,  3  ,  0  }};  //Very defensive

	public double[] determineMode_3(double f1, double Opp, double Own){

		if(Own==9){
			return weightMatrix_AI_3[4];
		}else if(Opp==9){
			return weightMatrix_AI_3[3];
		}else if(Own > Opp){
			return weightMatrix_AI_3[2];

		}else {

			if (f1 < 0.75 && Counter < 2)

				return weightMatrix_AI_3[0];
			else
				Counter = 2;
			//return weightMatrix_AI_3[1];
			return weightMatrix_AI_3[0];
		}
    }

}
