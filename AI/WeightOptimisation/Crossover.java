package AI.WeightOptimisation;

import AI.ReadMatrix;

import java.util.ArrayList;

public class Crossover {
    public static double mutationRate = 0.1;
    public static double randomInterval = 0.15;

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
                normalise(crossWeightMatrix);
                mutation(crossWeightMatrix);
                normalise(crossWeightMatrix);
                ReadMatrix.ReadOut(crossWeightMatrix,"Matrices");
            }
        }
    }
    public static double[][] normalise(double[][] crossWeightMatrix){
        for (int l = 0; l<crossWeightMatrix.length; l++) {
            double sum = 0;
            for (int m = 0; m<crossWeightMatrix[0].length; m++) {

                sum += crossWeightMatrix[l][m];
            }
            for (int n = 0; n<crossWeightMatrix[0].length; n++) {
                crossWeightMatrix[l][n] = (crossWeightMatrix[l][n]) / sum;
            }
        }
        return  crossWeightMatrix;
    }
    public static double[][] mutation(double[][] Matrix){
        for(int i=0; i<Matrix.length; i++) {
            for (int j = i; j < Matrix[0].length; j++) {
                double x = Math.random();
                if (x < mutationRate) {
                    Matrix[i][j] = Matrix[i][j] - randomInterval + Math.random() * 2 * randomInterval;

                    if (Matrix[i][j] < 0) {
                        Matrix[i][j] = 0;
                    }
                }
            }
        }
        return  Matrix;
    }

}
