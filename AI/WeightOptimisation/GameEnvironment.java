package AI.WeightOptimisation;

import AI.AutomaticGamePlay;
import src.Move;

public class GameEnvironment {

    public static int limit = 500;
    public static double[][] player1;
    public static double[][] player2;

    public static int[] GameEnvironment(double[][] p1, double[][] p2, int Trainer) {
        int[] result = new int[2];
        player1 = p1;
        player2 = p2;

        //important note:
        //this works together with our visual representation
        //the boolean 'need' in src/Move.java should be set to true
        //can loop through them as well when you play multiple games, but the ai needs to be set BEFORE you run the automaticgame play
        //ALSO before you run this, you NEED to have pressed the "do ai move" button if you haven't done a move!!
        //which seems annoying but making this automatic does not work, i've tried it believe me
        //it will set the initial board, so it is possible to clone it
        //after this, when you run the playGame methods, everything should run automatically

        result = AutomaticGamePlay.playGame(Move.initialBoard);

        result[1] = result[1] * Trainer;
        return result;
    }
}
