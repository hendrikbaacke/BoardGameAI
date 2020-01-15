package AI.WeightOptimisation;

import src.Marble;

public class EvolutionaryAlgo {

    private int initialGenerationSize = 10;
    private int modes = 5;
    private int amountWeights = 8;

    public double[][] initialGeneration() {
        double sum = 0;
        double[][] randWeightMatrix = new double[modes][amountWeights];

        for (int i=0; i<=initialGenerationSize; i++) {


            for (int j=0; j<=modes; j++) {
                for (int k=0; k<=amountWeights; k++) {
                    randWeightMatrix[j][k] = Math.random();

                }
            }

            for (int l = 0; l<=modes; l++) {
                for (int m=0; m<=amountWeights; m++) {

                    sum += randWeightMatrix[l][m];

                    for (int n=0; n<=modes; n++) {
                        randWeightMatrix[l][n] = (randWeightMatrix[l][n]) / sum;
                    }
                }

            }

        }
        return randWeightMatrix;
    }
        
    private void normalization(double[] weights){
        double sum = 0;
            for (int i=0; i<weights.length; i++){
                sum+=weights[i];
            }
            for (int j=0; j<weights.length; j++){
                weights[j] = weights[j]/sum;
            }
        }



}
