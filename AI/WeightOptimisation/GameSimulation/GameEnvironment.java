package AI.WeightOptimisation.GameSimulation;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import src.GameGui;
import src.Move;
import javafx.stage.Stage;

public class GameEnvironment {

    private static int Wsize = 8;


    public static void GameEnvironment(){
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

        while(Move.point<6 && Move.point2<6) Move.checkAI();

    }
}
