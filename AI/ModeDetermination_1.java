package AI;

import src.Board;

public class ModeDetermination_1 {


    /*In this class, the mode that the game is currently in, is determined. Given is the boardState. Look into paper as reference,
     the mapping into a specific mode is solved there by delimiting it by the center and cohesion strategies, the value of the weights in the evalFunction
     is determined by the mode in a static value association
    */

    //Neutral agent

    // all weigths should be positive, the function values f1..f7 have been instead altered according to heuristic observations

    public static int Counter = 1;

    // { Distance , Cohesion , Break , strength , Won , Lost , DistanceOpp }

    private double [][] weightMatrix_AI_1 = {   {  1  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  },       //get to the middle

                                                {  0.000001  ,  0.138889 ,  0.111111  ,  0.111111  ,  0.222222  ,  0.333333 , 0.083333   },    //bit def

                                                {  0  ,  0.038889 ,  0.1611111  ,  0.1211111  ,  0.3333333  ,  0.2222222 , 0.123333   },   //bit agg

                                                {  0  ,  0.046512 ,  0.116279  ,  0.116279  ,  0.581395  ,  0  ,  0.139535 },   //Very aggressive

                                                {  0.051020  ,  0.102040  ,  0.102040  ,  0.020408  ,  0.204081  ,  0.510207 , 0.010204 }};  //Very defensive

    public double[] determineMode_1(double f1, double Opp, double Own) {

        if(Own==9){
            return weightMatrix_AI_1[4];
        }else if(Opp==9){
            return weightMatrix_AI_1[3];
        }else if(Own > Opp){
            return weightMatrix_AI_1[2];
        } else {

            if (f1 < 0.75 && Counter < 2)
                return weightMatrix_AI_1[0];
            else
                Counter = 2;
            return weightMatrix_AI_1[1];
        }
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

