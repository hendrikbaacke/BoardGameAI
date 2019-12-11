package AI;

import src.Board;

public class ModeDetermination_3 {

//TODO: Assign weigths

    //1. closing Distance, 2. Cohesion, 3.breakGroup, 4. strengthenGroup, 5. Difference Opponent Marbles, 6. Difference Own Marbles

	public static int Counter = 1;
	private static double DistancePlayer = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("C4").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("C4").centerY, 2));


	private double[] mode1={  -0.05    ,  0  ,  0    ,  0  ,  0  ,  0 , 0}; //get to the middle, except we can kill                -7

	private double[] mode2={  0  ,  1 ,  1.2  ,  0.5  ,  30  ,  5 , 0.02}; //1.breaking groups 2.cohesion

	private double[] mode3={  0  ,  3  ,  5    ,  1  ,  30  ,  0 , 0 };

	private double[] mode4={  1    ,  0  ,  0    ,  0  ,  500  ,  -1000 , 0 };

	private double[] mode5={  0.2  ,  50 ,  200  ,  0  ,  0    ,  -1000 , 0 };

	private double[] mode6={  0.2  ,  0  ,  0    ,  0  ,  0    ,  -1000 , 0 };

	private double[] mode7={  1    ,  0  ,  0    ,  0  ,  500  ,  -1000 , 0 };

	private double[] mode8={  0.2  ,  50 ,  200  ,  0  ,  0    ,  -1000 , 0 };

	private double[] mode9={  0.2  ,  0  ,  0    ,  0  ,  0    ,  -1000 , 0 };


	private double[] defaultMode= {1,1,1,1,1,1};


	public double[] determineMode_3(double f1, double Opp){

		if(Opp<=11){
			return mode3;

		}else {

			if (f1 < DistancePlayer && Counter < 2)
				return mode1;
			else
				Counter = 2;
			return mode2;
		}
//        if(centerDistance<DistanceM1)
//            return mode2;
//        if(centerDistance<=DistanceM1 && cohesion>=0 && cohesion<4)
//            return mode3;
//        if(centerDistance<=DistanceM1 && cohesion>=4 && cohesion<10)
//            return mode4;
//        if(centerDistance<=DistanceM1 && cohesion>=10 && cohesion<16)
//            return mode5;
//        if(centerDistance<=DistanceM1 && cohesion>=16 && cohesion<22)
//            return mode6;
//        if(centerDistance<=DistanceM1 && cohesion>=22 && cohesion<28)
//            return mode7;
//        if(centerDistance<=DistanceM1 && cohesion>=28 && cohesion<34)
//            return mode8;
//        if(centerDistance<=DistanceM1 && cohesion>=34)
//            return mode9;
//
//        return defaultMode;



    }

}
