package AI;

import src.Board;

public class ModeDetermination{


    /*In this class, the mode that the game is currently in, is determined. Given is the boardState. Look into paper as reference,
     the mapping into a specific mode is solved there by delimiting it by the center and cohesion strategies, the value of the weights in the evalFunction
     is determined by the mode in a static value association
    */

    // all weigths should be positive, the function values f1..f8 have been instead altered according to heuristic observations

    public static int Counter = 1;

    // { Distance , Cohesion , Break , strength , Won , Lost , DistanceOpp , Danger }

    private double [][] weightMatrix_AI;

    public ModeDetermination(String name){
        weightMatrix_AI = ReadMatrix.ReadIn(System.getProperty("user.dir")+ReadMatrix.Slash+"AI"+ReadMatrix.Slash+"StartingAI"+ReadMatrix.Slash+name+".txt");
    }

    public ModeDetermination(double[][] AI){ weightMatrix_AI = AI; }

    public double[] determineMode(double f1, double Opp, double Own) {

        if(Own==9){
            return weightMatrix_AI[4];
        }else if(Opp==9){
            return weightMatrix_AI[3];
        }else if(Own > Opp){
            return weightMatrix_AI[2];
        } else {

            if (f1 < 0.75 && Counter < 2)
                return weightMatrix_AI[0];
            else
                Counter = 2;
            return weightMatrix_AI[1];
        }
    }
}

