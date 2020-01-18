package AI.WeightOptimisation.GameSimulation;

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


    private static int limit = 500;
    private static int Wsize = 8;
    public static double[][] player1;
    public static double[][] player2;

    public static int[] GameEnvironment(double[][] p1, double[][] p2, int Trainer){
        int[] result = new int[2];
        player1 = p1;
        player2 = p2;

        Move.player1AI = true;
        Move.player2AI = true;

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
        //can change the board now!!
        while(Move.point<6 && Move.point2<6 &&Traceback.totalMoves<limit) Move.checkAI(Board.hashBoard);

        result[0] = Traceback.totalMoves;
        result[1] = Trainer*(Move.point-Move.point2);

        return result;
    }
}
