package AI;


import src.Board;

public class ModeDetermination_2 {

    //Aggressive agent

    // all weigths should be positive, the function values f1..f7 have been instead altered according to heuristic observations

    public static int Counter = 1;

    // { Distance , Cohesion , Break , strength , Won , Lost , DistanceOpp }

    private double [][] weightMatrix_AI_2 = {   {  1  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0 , 0 },   //get to the middle

                                                {  0.1  ,  0.5  ,  1  ,  1.2  ,  5  ,  0.5  ,  0.5 ,  0.5  },   //bit def

                                                {  0  ,  0.7  ,  0.5  ,  1  ,  3  ,  0.1  ,  0.7 , 0.1 },   //bit agg

                                                {  0  ,  0  ,  0.8  ,  1  ,  5  ,  0  ,  1  , 0},   //Very aggressive

                                                {  0.05  ,  1  ,  0  ,  0  ,  3  ,  5  ,  0.2 , 5  }};  //Very defensive

    public double[] determineMode_2(double f1, double Opp, double Own){

        if(Own==9){
            return weightMatrix_AI_2[4];
        }else if(Opp==9){
            return weightMatrix_AI_2[3];
        }else if(Own > Opp){
            return weightMatrix_AI_2[2];
        }else {

            if (f1 < 0.75  && Counter < 2)
                return weightMatrix_AI_2[0];
            else
                Counter = 2;
            return weightMatrix_AI_2[1];
        }
    }
}
