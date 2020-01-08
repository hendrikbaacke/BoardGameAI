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

        Text OneContent = new Text("1. To be able to play the game first select the mode on which you prefer to play.");
        TextFlow OneCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list1 = OneCont.getChildren();
        list1.addAll(OneContent);

        Text TwoContent = new Text("2.You can play the game with a human player or with a computer.");
        TextFlow TwoCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list2 = TwoCont.getChildren();
        list2.addAll(TwoContent);

        Text ThreeContent = new Text("3. If you have chosen already what mode you want to play.");
        TextFlow ThreeCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list3 = ThreeCont.getChildren();
        list3.addAll(ThreeContent);

        Text FourContent = new Text("4. Then you have first to understand the rules of the game.");
        TextFlow FourCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list4 = FourCont.getChildren();
        list4.addAll(FourContent);

        Text FiveContent = new Text(" 5. If you click on one of the marbles then it will be highlighted in Purple this means that you are in the selection step. ");
        TextFlow FiveCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list5 = FiveCont.getChildren();
        list5.addAll(FiveContent);

        Text SixContent = new Text(" 6. If you click on the second marble it will be highlighted in blue, You are not done yet.");
        TextFlow SixCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list6 = SixCont.getChildren();
        list6.addAll(SixContent);

        Text SevenContent = new Text(" 7. Click again in the second marble to confirm that you are done with selection.");
        TextFlow SevenCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list7 = SevenCont.getChildren();
        list7.addAll(SevenContent);

        Text EightContent = new Text(" 8. If you are done with selection then the marbles will be highlighted with yellow and orange colors.");
        TextFlow EightCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list8 = EightCont.getChildren();
        list8.addAll(EightContent);

        Text NineContent = new Text(" 9. Then you can perform your movement in any direction you prefer.");
        TextFlow NineCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list9 = NineCont.getChildren();
        list9.addAll(NineContent);

        Text TenContent = new Text(" 10. Once you are done you need you have to wait because it is your opponent's turn.");
        TextFlow TenCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list10 = TenCont.getChildren();
        list10.addAll(TenContent);

        Text ElevenContent = new Text(" 11. To push the opponent marbles then click on the marble itself that you want to push. ");
        TextFlow ElevenCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list11 = ElevenCont.getChildren();
        list11.addAll(ElevenContent);

        Text TwelveContent = new Text(" 12. Once you push one marble out of the board then your score will increase.");
        TextFlow TwelveCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list12 = TwelveCont.getChildren();
        list12.addAll(TwelveContent);

        Text TteenContent = new Text(" 13. That is all enjoy your game. ");
        TextFlow TteenCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list13 = TteenCont.getChildren();
        list13.addAll(TteenContent);

        OneCont.setPadding(new Insets(10, 10, 10, 10));
        TwoCont.setPadding(new Insets(10, 10, 10, 10));
        ThreeCont.setPadding(new Insets(10, 10, 10, 10));
        FourCont.setPadding(new Insets(10, 10, 10, 10));
        FiveCont.setPadding(new Insets(10, 10, 10, 10));
        SixCont.setPadding(new Insets(10, 10, 10, 10));
        SevenCont.setPadding(new Insets(10, 10, 10, 10));
        EightCont.setPadding(new Insets(10, 10, 10, 10));
        NineCont.setPadding(new Insets(10, 10, 10, 10));
        TenCont.setPadding(new Insets(10, 10, 10, 10));
        ElevenCont.setPadding(new Insets(10, 10, 10, 10));
        TwelveCont.setPadding(new Insets(10, 10, 10, 10));
        TteenCont.setPadding(new Insets(10, 10, 10, 10));

        OneCont.setStyle("-fx-background-color: #dae7f3;");
        TwoCont.setStyle("-fx-background-color: #dae7f3;");
        ThreeCont.setStyle("-fx-background-color: #dae7f3;");
        FourCont.setStyle("-fx-background-color: #dae7f3;");
        FiveCont.setStyle("-fx-background-color: #dae7f3;");
        SixCont.setStyle("-fx-background-color: #dae7f3;");
        SevenCont.setStyle("-fx-background-color: #dae7f3;");
        EightCont.setStyle("-fx-background-color: #dae7f3;");
        NineCont.setStyle("-fx-background-color: #dae7f3;");
        TenCont.setStyle("-fx-background-color: #dae7f3;");
        ElevenCont.setStyle("-fx-background-color: #dae7f3;");
        TwelveCont.setStyle("-fx-background-color: #dae7f3;");
        TteenCont.setStyle("-fx-background-color: #dae7f3;");

        MainCont.add(OneCont, 1, 0);
        MainCont.add(TwoCont, 1, 1);
        MainCont.add(ThreeCont, 1, 2);
        MainCont.add(FourCont, 1, 3);
        MainCont.add(FiveCont, 1, 4);
        MainCont.add(SixCont, 1, 5);
        MainCont.add(SevenCont, 1, 6);
        MainCont.add(EightCont, 1, 7);
        MainCont.add(NineCont, 1, 8);
        MainCont.add(TenCont, 1, 9);
        MainCont.add(ElevenCont, 1, 10);
        MainCont.add(TwelveCont, 1, 11);
        MainCont.add(TteenCont, 1, 12);


        return MainCont;
    }
}
