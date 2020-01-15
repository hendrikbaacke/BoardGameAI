package AI.WeightOptimisation;

import AI.ReadMatrix;

public class TrainingInstances {

    static double[][] AggressiveInstance = ReadMatrix.ReadIn(System.getProperty("user.dir") + "\\AI\\StartingAI\\" + "Aggressive" + ".txt");
    static double[][] NeutralInstance = ReadMatrix.ReadIn(System.getProperty("user.dir") + "\\AI\\StartingAI\\" + "Neutral" + ".txt");
    static double[][] DefensiveInstance = ReadMatrix.ReadIn(System.getProperty("user.dir") + "\\AI\\StartingAI\\" + "Defensive" + ".txt");

    public static void RandInstanceCreation() {

        double[][] RandAggressiveInstance = AggressiveInstance;
        double[][] RandNeutralInstance = NeutralInstance;
        double[][] RandDefensiveInstance = DefensiveInstance;
        double randomInterval = 0.15;

        for (int i = 0; i < RandAggressiveInstance.length; i++) {
            for (int j = 0; j < RandAggressiveInstance[0].length; j++) {

                RandAggressiveInstance[i][j] = AggressiveInstance[i][j] - randomInterval + Math.random() * 2 * randomInterval;       // midpoint of [a,b] is value of AggressiveInstance[i][j]

                if (RandAggressiveInstance[i][j] < 0) {
                    RandAggressiveInstance[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < RandNeutralInstance.length; i++) {
            for (int j = 0; j < RandNeutralInstance[0].length; j++) {

                RandNeutralInstance[i][j] = NeutralInstance[i][j] - randomInterval + Math.random() * 2 * randomInterval;       // midpoint of [a,b] is value of NeutralInstance[i][j]

                if (RandNeutralInstance[i][j] < 0) {
                    RandNeutralInstance[i][j] = 0;
                }
            }
        }


        for (int i = 0; i < RandDefensiveInstance.length; i++) {
            for (int j = 0; j < RandDefensiveInstance[0].length; j++) {

                RandAggressiveInstance[i][j] = DefensiveInstance[i][j] - randomInterval + Math.random() * 2 * randomInterval;       // midpoint of [a,b] is value of DefensiveInstance[i][j]

                if (RandDefensiveInstance[i][j] < 0) {
                    RandDefensiveInstance[i][j] = 0;
                }
            }
        }

        Normalize(RandAggressiveInstance);
        Normalize(RandNeutralInstance);
        Normalize(RandDefensiveInstance);


        ReadMatrix.ReadOut(RandAggressiveInstance);
        ReadMatrix.ReadOut(RandNeutralInstance);
        ReadMatrix.ReadOut(RandDefensiveInstance);


    }
    //Normalisation:
    private static void Normalize(double[][] inputMatrix) {


        for (int l = 0; l < inputMatrix.length; l++) {

            double sum = 0;

            for (int m = 0; m < inputMatrix[0].length; m++) {

                sum += inputMatrix[l][m];
            }
            for (int n = 0; n < inputMatrix.length; n++) {
                inputMatrix[l][n] = (inputMatrix[l][n]) / sum;

            }

        }

    }

}