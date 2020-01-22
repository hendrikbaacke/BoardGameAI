package src;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/*
 * A class for the GUI.
 */

public class SettingsPane {
    public GridPane create(){
        GridPane MainCont = new GridPane();
        MainCont.setStyle("-fx-font-size: 18px;");
        MainCont.setPadding(new Insets(50, 50, 50, 50));
        //Setting the vertical and horizontal gaps between the columns
        MainCont.setVgap(10);
        MainCont.setHgap(50);

        Text OneContent = new Text("Important: To be able to play the game first select the mode with which you prefer to play; human vs human or human vs computer. Computer vs computer can run a game of AI's.");
        TextFlow OneCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list1 = OneCont.getChildren();
        list1.addAll(OneContent);


        Text FourContent = new Text("1. Firstly, you need to understand the rules of the game. See the rules. In PvP or PvC mode you need to play yourself.");
        TextFlow FourCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list4 = FourCont.getChildren();
        list4.addAll(FourContent);

        Text FiveContent = new Text(" 2. If you click on one of the marbles then it will become purple. This means that this one is selected to be the 'head' node now. ");
        TextFlow FiveCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list5 = FiveCont.getChildren();
        list5.addAll(FiveContent);

        Text SixContent = new Text(" 3. If you only want to select the one marble you chose before, click the marble again. Once it's orange,you know the selection step ended.");
        TextFlow SixCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list6 = SixCont.getChildren();
        list6.addAll(SixContent);

        Text SevenContent = new Text(" 4. If you want to select more marbles, do not click on the first one twice, but click on another marble adjacent to this one. It is possible to click on the third adjacent one now as well, as long as it's adjacent to the second one.");
        TextFlow SevenCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list7 = SevenCont.getChildren();
        list7.addAll(SevenContent);

        Text EightContent = new Text(" 5. If the selection needs to be ended at two, click on one of the two marbles again. If you choose three, the selection will be ended automatically. If you are done with selection then the marbles will be highlighted with yellow and orange colors.");
        TextFlow EightCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list8 = EightCont.getChildren();
        list8.addAll(EightContent);

        Text NineContent = new Text(" 6. Now, you can click on another hexagon, that needs to be adjacent to the 'head'. If it's possible, it will move, otherwise, the move will be reset.");
        TextFlow NineCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list9 = NineCont.getChildren();
        list9.addAll(NineContent);

        Text TenContent = new Text(" 7. Once you performed a move, you need you have to wait for your opponent to move.");
        TextFlow TenCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list10 = TenCont.getChildren();
        list10.addAll(TenContent);

        Text ElevenContent = new Text("8. To push the opponent marbles (if possible) then click on the marble that you want to push after the selection step is done. Then you gain a point.");
        TextFlow ElevenCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list11 = ElevenCont.getChildren();
        list11.addAll(ElevenContent);



        OneCont.setPadding(new Insets(10, 10, 10, 10));
        FourCont.setPadding(new Insets(10, 10, 10, 10));
        FiveCont.setPadding(new Insets(10, 10, 10, 10));
        SixCont.setPadding(new Insets(10, 10, 10, 10));
        SevenCont.setPadding(new Insets(10, 10, 10, 10));
        EightCont.setPadding(new Insets(10, 10, 10, 10));
        NineCont.setPadding(new Insets(10, 10, 10, 10));
        TenCont.setPadding(new Insets(10, 10, 10, 10));
        ElevenCont.setPadding(new Insets(10, 10, 10, 10));

        OneCont.setStyle("-fx-background-color: #dae7f3;");
        FourCont.setStyle("-fx-background-color: #dae7f3;");
        FiveCont.setStyle("-fx-background-color: #dae7f3;");
        SixCont.setStyle("-fx-background-color: #dae7f3;");
        SevenCont.setStyle("-fx-background-color: #dae7f3;");
        EightCont.setStyle("-fx-background-color: #dae7f3;");
        NineCont.setStyle("-fx-background-color: #dae7f3;");
        TenCont.setStyle("-fx-background-color: #dae7f3;");
        ElevenCont.setStyle("-fx-background-color: #dae7f3;");

        MainCont.add(OneCont, 1, 0);
        MainCont.add(FourCont, 1, 3);
        MainCont.add(FiveCont, 1, 4);
        MainCont.add(SixCont, 1, 5);
        MainCont.add(SevenCont, 1, 6);
        MainCont.add(EightCont, 1, 7);
        MainCont.add(NineCont, 1, 8);
        MainCont.add(TenCont, 1, 9);
        MainCont.add(ElevenCont, 1, 10);


        return MainCont;
    }
}
