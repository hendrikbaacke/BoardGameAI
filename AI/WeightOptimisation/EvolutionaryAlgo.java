package AI.WeightOptimisation;

import AI.ReadMatrix;
import AI.WeightOptimisation.GameSimulation.GameEnvironment;

public class EvolutionaryAlgo {

    private static int initialGenerationSize = 3;          // should be >10
    private static int modes = 5;
    private static int amountWeights = 8;

    private static int amountGames = 4;

    public static void initialGeneration() {

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

        TrainingInstances.RandInstanceCreation();

        for(int i=0; i<initialGenerationSize; i++) {

            for(int j=0; j<3; j++){

                double[][] player1 =   ReadMatrix.ReadIn(System.getProperty("user.dir") + "\\AI\\Trainers\\"+ "AInumber" + ReadMatrix.gen +"_" +j + ".txt");
                double[][] player2 =   ReadMatrix.ReadIn(System.getProperty("user.dir") + "\\AI\\Matrices\\"+ "AInumber" + ReadMatrix.gen +"_" +i + ".txt");

                for(int k=0; k<amountGames; k++){


                    if(k % 2 == 0){
                        double[][] placeholder = player1;
                        player1 = player2;
                        player2=placeholder;
                    }
                    GameEnvironment(player1,player2);
                }
            }


        }



    }
    private static void Crossover(double[][] strongBloodline){

    }
    private static void Mutation(double[][] strongBloodLine){

    }
}
