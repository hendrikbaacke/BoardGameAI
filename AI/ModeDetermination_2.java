package AI;


import src.Board;

public class ModeDetermination_2 {


//TODO: Assign weigths

    // all weigths should be positive, the function values f1..f6 have been instead altered according to heurisitc observations

    //1. closing Distance, 2. Cohesion, 3.breakGroup, 4. strengthenGroup, 5. Difference Opponent Marbles, 6. Difference Own Marbles

    public static int Counter = 1;
    private static double DistancePlayer = Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX - Board.hashBoard.get("C4").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("C4").centerY, 2));

    // here the weights are defined in a [9][7] matrix, reason: easier to optimise by an evolutionary algorithm


    private double [][] weightMatrix_AI_2 = {{  1    ,  0  ,  0    ,  0  ,  0  ,  0 , 0   }, //get to the middle, except we can kill                -7

                                              {  0  ,  1 ,  1.2  ,  0.5  ,  30  ,  5 , 0.02   },   //1.breaking groups 2.cohesion

                                              {  0  ,  3  ,  5    ,  1  ,  30  ,  0 , 0       },

                                              {  1    ,  0  ,  0    ,  0  ,  500  ,  -1000 , 0 },

                                              {  0.2  ,  50 ,  200  ,  0  ,  0    ,  -1000 , 0 },

                                              {  0.2  ,  0  ,  0    ,  0  ,  0    ,  -1000 , 0 },

                                              {  1    ,  0  ,  0    ,  0  ,  500  ,  -1000 , 0 },

                                              {  0.2  ,  50 ,  200  ,  0  ,  0    ,  -1000 , 0 },

                                              {  0.2  ,  0  ,  0    ,  0  ,  0    ,  -1000 , 0 }};


    private double[] defaultMode= {1,1,1,1,1,1,1};


    public double[] determineMode_2(double f1, double Opp, double Own){

        if(Opp<=11){
            return weightMatrix_AI_2[2];

        }else {

            if (f1 < 0.75  && Counter < 2)
                return weightMatrix_AI_2[0];
            else
                Counter = 2;
            return weightMatrix_AI_2[1];
        }


//        return defaultMode;


    }
}
