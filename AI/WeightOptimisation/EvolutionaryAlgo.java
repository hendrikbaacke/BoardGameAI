package AI.WeightOptimisation;

import AI.ReadMatrix;
import AI.WeightOptimisation.GameSimulation.GameEnvironment;

public class EvolutionaryAlgo {

    private static int initialGenerationSize = 1;          // should be >10
    public static int modes = 5;
    public static int amountWeights = 8;

    private static int amountGames = 4;

    private static int Trainer=1;

    public static void initialGeneration() {

        ReadMatrix.FileNumber = 0;

        double[][] randWeightMatrix = new double[modes][amountWeights];

        for (int i=0; i<initialGenerationSize; i++) {


            for (int j = 0; j<modes; j++) {
                for (int k = 0; k < amountWeights; k++) {
                    randWeightMatrix[j][k] = Math.random();

                }
            }
            //Normalisation:
            for (int l = 0; l<modes; l++) {
                double sum = 0;
                for (int m = 0; m<amountWeights; m++) {

                    sum += randWeightMatrix[l][m];
                }
                    for (int n = 0; n<amountWeights; n++) {
                        randWeightMatrix[l][n] = (randWeightMatrix[l][n]) / sum;
                    }


            }
            ReadMatrix.ReadOut(randWeightMatrix,"Matrices");

        }
    }


    public static void Selection(){

        for(int i=0; i<initialGenerationSize; i++) {
            for(int j=0; j<3; j++){

                double[][] player1 =   ReadMatrix.ReadIn(System.getProperty("user.dir") + "\\AI\\Trainers\\"+ "AInumber" + ReadMatrix.gen +"_" +j + ".txt");
                double[][] player2 =   ReadMatrix.ReadIn(System.getProperty("user.dir") + "\\AI\\Matrices\\"+ "AInumber" + ReadMatrix.gen +"_" +i + ".txt");

                for(int k=0; k<amountGames; k++){


                    if(k % 2 == 0){
                        double[][] placeholder = player1;
                        player1 = player2;
                        player2=placeholder;
                        Trainer = Trainer*(-1);
                    }
                    GameEnvironment.GameEnvironment(player1,player2,Trainer,i);
                }
            }


        }



    }
    private static void Crossover(double[][] strongBloodline){

    }
    private static void Mutation(double[][] strongBloodLine){

    }
}
