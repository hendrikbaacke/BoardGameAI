package AI;

import src.Board;

public class ModeDetermination_1 {


    /*In this class, the mode that the game is currently in, is determined. Given is the boardState. Look into paper as reference,
     the mapping into a specific mode is solved there by delimiting it by the center and cohesion strategies, the value of the weights in the evalFunction
     is determined by the mode in a static value association
    */


    // all weigths should be positive, the function values f1..f6 have been instead altered according to heurisitc observations

	private double DistanceM1 =  Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX- Board.hashBoard.get("G6").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("G6").centerY, 2));

//TODO: Assign weigths

	//1. closing Distance, 2. Cohesion, 3.breakGroup, 4. strengthenGroup, 5. Difference Opponent Marbles, 6. Difference Own Marbles


	private double[] mode1={  0    ,  0  ,  0    ,  0  ,  0  ,  0 }; //get to the middle, except we can kill

	private double[] mode2={  0.2  ,  50 ,  200  ,  0  ,  0    ,  1000 }; //1.breaking groups 2.cohesion

	private double[] mode3={  0.2  ,  0  ,  0    ,  0  ,  0    ,  1000 };

	private double[] mode4={  1    ,  0  ,  0    ,  0  ,  500  ,  1000 };

	private double[] mode5={  0.2  ,  50 ,  200  ,  0  ,  0    ,  1000 };

	private double[] mode6={  0.2  ,  0  ,  0    ,  0  ,  0    ,  1000 };

	private double[] mode7={  1    ,  0  ,  0    ,  0  ,  500  ,  1000 };

	private double[] mode8={  0.2  ,  50 ,  200  ,  0  ,  0    ,  1000 };

	private double[] mode9={  0.2  ,  0  ,  0    ,  0  ,  0    ,  1000 };



	private double[] defaultMode= {1,1,1,1,1,1};
	

    public double[] determineMode_1(double centerDistance, double cohesion){

		//    if(centerDistance> DistanceM1)

		return mode1;

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
