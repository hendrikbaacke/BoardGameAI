package AI.WeightOptimisation;

import AI.ReadMatrix;
import java.util.ArrayList;

public class SortResults {
    public static int BestMatrices = 4;

    public static ArrayList<double[][]> sorting(){
        ArrayList list = new ArrayList<double[][]>();
        int[][] moves = new int[EvolutionaryAlgo.initialGenerationSize][2];
        int[][] marbles = new int[EvolutionaryAlgo.initialGenerationSize][2];
        int[] compare;
        int[][] positions = new int[EvolutionaryAlgo.initialGenerationSize][2];
        for(int m=1; m<=EvolutionaryAlgo.initialGenerationSize; m++) positions[m-1][1]=m;

        for(int j=1; j<=3; j++){
            for(int i=1; i<=EvolutionaryAlgo.initialGenerationSize; i++){
                compare = ReadResult.ReadIn(System.getProperty("user.dir") + ReadMatrix.Slash +"AI"+ReadMatrix.Slash+"Results"+ReadMatrix.Slash+ "AIResult" + ReadMatrix.gen +"_" +i+"_" +j + ".txt");
                moves[i-1][0] = compare[0];
                moves[i-1][1] = i;
                marbles[i-1][0] = compare[1];
                marbles[i-1][1] = i;
            }
            quickSort(moves,0,moves.length-1);
            quickSort(marbles,0,marbles.length-1);
            for(int i=0; i<moves.length; i++){
                int x = moves[i][1]-1;
                positions[x][0] += i+1;
                int y = marbles[moves.length-i-1][1]-1;
                positions[y][0] += i+1;
            }
        }
        quickSort(positions,0,positions.length-1);
        for(int l=0; l<BestMatrices; l++) {
            list.add(ReadMatrix.ReadIn(System.getProperty("user.dir") + ReadMatrix.Slash + "AI" + ReadMatrix.Slash + "Matrices" + ReadMatrix.Slash + "AInumber" + ReadMatrix.gen + "_" + positions[l][1] + ".txt"));
        }
        return list;
    }

    private static void quickSort(int arr[][], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }
    private static int partition(int arr[][], int begin, int end) {
        int pivot = arr[end][0];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j][0] <= pivot) {
                i++;
                int swapTemp = arr[i][0];
                int swapTemp2 = arr[i][1];
                arr[i][0] = arr[j][0];
                arr[i][1] = arr[j][1];
                arr[j][0] = swapTemp;
                arr[j][1] = swapTemp2;
            }
        }

        int swapTemp = arr[i+1][0];
        int swapTemp2 = arr[i+1][1];
        arr[i+1][0] = arr[end][0];
        arr[i+1][1] = arr[end][1];
        arr[end][0] = swapTemp;
        arr[end][1] = swapTemp2;

        return i+1;
    }
}
