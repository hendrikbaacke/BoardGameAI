package AI.WeightOptimisation;

import AI.ReadMatrix;
import java.io.File;

import java.util.ArrayList;

public class GeneticLoop {
    private int Gensize = 10;

    public void start() {
        //emptying the folders (so we get no confusion and wrong results)
        deleteDirectory(System.getProperty("user.dir") + ReadMatrix.Slash + "AI" + ReadMatrix.Slash + "Trainers");
        deleteDirectory(System.getProperty("user.dir") + ReadMatrix.Slash + "AI" + ReadMatrix.Slash + "Matrices");
        deleteDirectory(System.getProperty("user.dir") + ReadMatrix.Slash + "AI" + ReadMatrix.Slash + "Results");

        //creating first trainers
        TrainingInstances.RandInstanceCreation();
        //creating first random generation
        EvolutionaryAlgo.initialGeneration();
        //training generation
        EvolutionaryAlgo.Selection();

        for (int i = 1; i < Gensize; i++) {
            ArrayList ResultList = new ArrayList<double[][]>();
            //TODO fill ResultList
            Crossover.crossover(ResultList);
            TrainingInstances.RandInstanceCreation();
            EvolutionaryAlgo.Selection();
        }
    }

    static public void deleteDirectory(String directoryName) {
        File directory = new File(directoryName);

        // Get all files in directory

        File[] files = directory.listFiles();
        for (File file : files) {
            // Delete each file

            if (!file.delete()) {
                // Failed to delete file

                System.out.println("Failed to delete " + file);
            }
        }
    }
}
