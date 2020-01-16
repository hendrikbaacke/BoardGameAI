package AI.WeightOptimisation;

import AI.ReadMatrix;

import java.util.ArrayList;

public class Crossover {
    public static void crossover(ArrayList<double[][]> Best){
        ReadMatrix.FileNumber = 0;
        ReadMatrix.gen++;

        for(int i=0; i<Best.size(); i++){
            for(int j=i; j<Best.size(); j++){
                double[][] crossWeightMatrix = new double[EvolutionaryAlgo.modes][EvolutionaryAlgo.amountWeights];
                for (int k = 0; k<crossWeightMatrix.length; k++) {
                    for (int m = 0; m < crossWeightMatrix[0].length; m++) {
                        crossWeightMatrix[k][m] = Best.get(i)[k][m] + Best.get(j)[k][m];
                    }
                }
                //Normalisation:
                for (int l = 0; l<crossWeightMatrix.length; l++) {
                    double sum = 0;
                    for (int m = 0; m<crossWeightMatrix[0].length; m++) {

                        sum += crossWeightMatrix[l][m];
                    }
                    for (int n = 0; n<crossWeightMatrix[0].length; n++) {
                        crossWeightMatrix[l][n] = (crossWeightMatrix[l][n]) / sum;
                    }


                }
                ReadMatrix.ReadOut(crossWeightMatrix,"Matrices");
            }

        }
    }
}
