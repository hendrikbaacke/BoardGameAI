package AI.WeightOptimisation;

import AI.ReadMatrix;

import java.util.ArrayList;

public class GeneticLoop {
    private int Gensize = 10;
    public void start() {
        TrainingInstances.RandInstanceCreation();
        EvolutionaryAlgo.initialGeneration();
        EvolutionaryAlgo.Selection();

        for(int i=1; i<Gensize; i++){
            ArrayList ResultList = new ArrayList<double[][]>();
            //TODO fill ResultList
            Crossover.crossover(ResultList);
            TrainingInstances.RandInstanceCreation();
            EvolutionaryAlgo.Selection();
        }
    }
}
