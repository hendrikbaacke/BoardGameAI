package src;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/*
 * A class for the GUI.
 */

public class RulesPane {
    public GridPane create(){
        GridPane MainCont = new GridPane();
        MainCont.setStyle("-fx-font-size: 18px;");
        MainCont.setPadding(new Insets(50, 50, 50, 50));
        //Setting the vertical and horizontal gaps between the columns
        MainCont.setVgap(50);
        MainCont.setHgap(50);


        Text OneContent = new Text("Goal: to be the first player to push 6 of the opponent's marbles out of the board.");
        TextFlow OneCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list1 = OneCont.getChildren();
        list1.addAll(OneContent);

        Text TwoContent = new Text("Black makes the first move.");
        TextFlow TwoCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list2 = TwoCont.getChildren();
        list2.addAll(TwoContent);

        Text ThreeContent = new Text("You can move one, two or three marbles at a time.");
        TextFlow ThreeCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list3 = ThreeCont.getChildren();
        list3.addAll(ThreeContent);

        Text FourContent = new Text("Your marbles can move one space: in a straight line or sideways. You can only move if the spots you want to move to are free, or if you can push the opponent away.");
        TextFlow FourCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list4 = FourCont.getChildren();
        list4.addAll(FourContent);

        Text FiveContent = new Text(" Two marbles can push one of the opponent's marbles and three marbles can push one or two of the opponent's marbles if they are in the same row and the spot the opponent's marbles need to be pushed to is either free or not in the board. However, an equal number of marbles cannot push each other (ie: 1 cannot push 1, 2 cannot push 2, etc).");
        TextFlow FiveCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list5 = FiveCont.getChildren();
        list5.addAll(FiveContent);

        OneCont.setPadding(new Insets(10, 10, 10, 10));
        TwoCont.setPadding(new Insets(10, 10, 10, 10));
        ThreeCont.setPadding(new Insets(10, 10, 10, 10));
        FourCont.setPadding(new Insets(10, 10, 10, 10));
        FiveCont.setPadding(new Insets(10, 10, 10, 10));


        OneCont.setStyle("-fx-background-color: #dae7f3;");
        TwoCont.setStyle("-fx-background-color: #dae7f3;");
        ThreeCont.setStyle("-fx-background-color: #dae7f3;");
        FourCont.setStyle("-fx-background-color: #dae7f3;");
        FiveCont.setStyle("-fx-background-color: #dae7f3;");


        MainCont.add(OneCont, 1, 0);
        MainCont.add(TwoCont, 1, 1);
        MainCont.add(ThreeCont, 1, 2);
        MainCont.add(FourCont, 1, 3);
        MainCont.add(FiveCont, 1, 4);

        return MainCont;
    }
}
