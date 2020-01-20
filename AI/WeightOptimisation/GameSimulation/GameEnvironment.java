package AI.WeightOptimisation.GameSimulation;

import AI.AutomaticGamePlay;
import AI.EvaluationFunction;
import AI.ModeDetermination;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import src.Board;
import src.GameGui;
import src.Move;
import javafx.stage.Stage;
import src.Traceback;

public class GameEnvironment {


    public static int limit = 500;
    private static int Wsize = 8;
    public static double[][] player1;
    public static double[][] player2;

    public static int[] GameEnvironment(double[][] p1, double[][] p2, int Trainer){
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

        result[1] = result[1] * Trainer;//*(Move.point-Move.point2);

        System.out.println("-----------------------------RESULTS: "+result[0]+"     "+result[1]);
        return result;
    }
    
    /*
    Stage stage = new Stage();

    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    stage.setX(primaryScreenBounds.getMinX());
    stage.setY(primaryScreenBounds.getMinY());
    stage.setWidth(primaryScreenBounds.getWidth()/Wsize);
    stage.setHeight(primaryScreenBounds.getHeight()/Wsize);


    GameGui Game = new GameGui();
    Game.start(stage);
    src.GameMethods.AlertON = false;
    EvaluationFunction.AITestingON = true;
    */
    
    //can change the board now!!
    //while(Move.point<6 && Move.point2<6 &&Traceback.totalMoves<limit) Move.checkAI(Board.hashBoard);

    //result[0] = Traceback.totalMoves;
}
