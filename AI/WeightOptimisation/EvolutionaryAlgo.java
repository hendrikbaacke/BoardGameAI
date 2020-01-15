package AI.WeightOptimisation;

import AI.ReadMatrix;
import src.Marble;

public class EvolutionaryAlgo {

    private static int initialGenerationSize = 3;
    private static int modes = 5;
    private static int amountWeights = 8;

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

}
