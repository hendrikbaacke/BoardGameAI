package src;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import java.io.FileInputStream;
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;
import javafx.stage.Window;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Main extends Application {

    DropShadow shadow = new DropShadow();
    public GameGui Game;

    @Override
    public void start(Stage primaryStage) throws Exception {
  
        //Define Window title
        primaryStage.setTitle("Team 1 -  Project 2.1");

        //Image pic = new Image("file:backgroundGame.jpg");
        //ImageView mv = new ImageView(pic);

        VBox sliderBox = new VBox();
        sliderBox.setAlignment(Pos.CENTER);
        sliderBox.setPrefHeight(500);
        sliderBox.setSpacing(50);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

        Text title = new Text();
        title.setEffect(dropShadow);
        title.setCache(true);
        title.setX(10.0);
        title.setY(70.0);
        title.setFill(Color.web("0x3b596d"));
        title.setText("Abalone Game-IT");
        title.setFont(Font.font(null, FontWeight.BOLD, 70));


        Button settings = new Button("SETTINGS");
        settings.getStyleClass().add("menu_items");
        Button rules = new Button("RULES");
        rules.getStyleClass().add("menu_items");
        Button credits = new Button("CREDITS");
        credits.getStyleClass().add("menu_items");

        HBox menuBox = new HBox();
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPrefWidth(400);
        menuBox.setSpacing(20);
        menuBox.getChildren().addAll(settings, rules, credits);

        sliderBox.getChildren().addAll(title, menuBox);



        //Add game mode images
        ImageView iv_1 = new ImageView();
        iv_1.setImage(new Image(new FileInputStream("PvP.png")));
        ImageView iv_2 = new ImageView();
        iv_2.setImage(new Image(new FileInputStream("PvC.png")));

        //Add game mode labels
        Label modeOneLabel = new Label("Player vs. Player");
        //modeOneLabel.setStyle("-fx-font: 20 arial;");
        Label modeTwoLabel = new Label("Player vs. Computer");
        //modeTwoLabel.setStyle("-fx-font: 20 arial;");

        //Defining layouts
        VBox mainContainer = new VBox();
        VBox modeOneContainer = new VBox();
        VBox modeTwoContainer = new VBox();


        HBox modeBox = new HBox();


        modeOneContainer.getStyleClass().add("modeButton" );
        modeOneContainer.setPadding(new Insets(10,10,10,10));
        modeTwoContainer.getStyleClass().add("modeButton");
        modeTwoContainer.setPadding(new Insets(10,10,10,10));

        //some fancy shadow effects
        modeOneContainer.setOnMouseEntered(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                iv_1.setEffect(shadow);
            }
        });
        modeOneContainer.setOnMouseExited(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                iv_1.setEffect(null);
            }
        });
        modeTwoContainer.setOnMouseEntered(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                iv_2.setEffect(shadow);
            }
        });
        modeTwoContainer.setOnMouseExited(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                iv_2.setEffect(null);
            }
        });



        modeOneContainer.getChildren().addAll(iv_1,modeOneLabel );
        modeOneContainer.setAlignment(Pos.CENTER);
        modeTwoContainer.getChildren().addAll(iv_2,modeTwoLabel);
        modeTwoContainer.setAlignment(Pos.CENTER);

        modeBox.getChildren().addAll(createSpacer(),modeOneContainer,createSpacer(), modeTwoContainer,createSpacer());
        modeBox.setPadding(new Insets(0,0,0,0));

        StackPane pane = new StackPane();
        mainContainer.getChildren().addAll(sliderBox, modeBox );
        pane.getChildren().addAll(mainContainer);
        String style = "-fx-background-color: lightblue;;";
        pane.setStyle(style);


        Scene scene = new Scene(pane,3000,2000);

        scene.getStylesheets().add("application/Main.css");
        primaryStage.setScene(scene);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //set Stage boundaries to visible bounds of the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());


        //Show stage
        primaryStage.show();

        Button back = new Button("BACK");
        HBox backCont = new HBox();
        backCont.getChildren().add(back);
        backCont.setAlignment(Pos.CENTER_RIGHT);
        backCont.setPadding(new Insets(50, 50, 50, 50));

        //Credits:
        VBox creditsMainCont = new VBox();

        HBox credTitleCont = new HBox();
        credTitleCont.setAlignment(Pos.CENTER);
        Text credTitle = new Text("CREDITS");
        credTitle.setStyle("-fx-font-size: 30px;");
        credTitleCont.getChildren().add(credTitle);
        credTitleCont.setPrefHeight(100);

        HBox credSubTitleCont = new HBox();
        credSubTitleCont.setAlignment(Pos.CENTER);
        Text credSubTitle = new Text("This is a sub credit title for showcasing the work of team 1.");
        credSubTitleCont.getChildren().add(credSubTitle);
        credSubTitleCont.setPrefHeight(50);
        credSubTitle.setStyle("-fx-font-size: 20px;");
        GridPane credMainCont = new GridPane();
        credMainCont.setStyle("-fx-font-size: 18px;");
        credMainCont.setPadding(new Insets(50, 50, 50, 50));
        //Setting the vertical and horizontal gaps between the columns
        credMainCont.setVgap(50);
        credMainCont.setHgap(50);

        Text SouzanTitle = new Text("Souzan Abboud");
        Text WafaaTitle = new Text("Wafaa Aljbawi");
        Text HendrikTitle = new Text("Hendrik Baacke");
        Text JannekeTitle = new Text("Janneke van Baden");
        Text NickTitle = new Text("Nick Bast");
        Text FredTitle = new Text("Fred Bedetse");

        StackPane souzanTitleBG = new StackPane();
        StackPane wafaaTitleBG = new StackPane();
        StackPane hendrikTitleBG = new StackPane();
        StackPane jannekeTitleBG = new StackPane();
        StackPane nickTitleBG = new StackPane();
        StackPane fredTitleBG = new StackPane();

        souzanTitleBG.getChildren().add(SouzanTitle);
        wafaaTitleBG.getChildren().add(WafaaTitle);
        hendrikTitleBG.getChildren().add(HendrikTitle);
        jannekeTitleBG.getChildren().add(JannekeTitle);
        nickTitleBG.getChildren().add(NickTitle);
        fredTitleBG.getChildren().add(FredTitle);

        souzanTitleBG.setPadding(new Insets(10, 10, 10, 10));
        wafaaTitleBG.setPadding(new Insets(10, 10, 10, 10));
        hendrikTitleBG.setPadding(new Insets(10, 10, 10, 10));
        jannekeTitleBG.setPadding(new Insets(10, 10, 10, 10));
        nickTitleBG.setPadding(new Insets(10, 10, 10, 10));
        fredTitleBG.setPadding(new Insets(10, 10, 10, 10));

        souzanTitleBG.setStyle("-fx-background-color: #dae7f3;");
        wafaaTitleBG.setStyle("-fx-background-color: #dae7f3;");
        hendrikTitleBG.setStyle("-fx-background-color: #dae7f3;");
        jannekeTitleBG.setStyle("-fx-background-color: #dae7f3;");
        nickTitleBG.setStyle("-fx-background-color: #dae7f3;");
        fredTitleBG.setStyle("-fx-background-color: #dae7f3;");


        credMainCont.add(souzanTitleBG, 0, 0);
        credMainCont.add(wafaaTitleBG, 0, 1);
        credMainCont.add(jannekeTitleBG, 0, 2);
        credMainCont.add(nickTitleBG, 0, 3);
        credMainCont.add(hendrikTitleBG, 0, 4);
        credMainCont.add(fredTitleBG, 0, 5);

        creditsMainCont.getChildren().addAll(credTitleCont, credSubTitleCont, credMainCont, backCont);
        Scene creditScene = new Scene(creditsMainCont);

        Button back2 = new Button("BACK");
        HBox backCont2 = new HBox();
        backCont2.getChildren().add(back2);
        backCont2.setAlignment(Pos.CENTER_RIGHT);
        backCont2.setPadding(new Insets(50, 50, 50, 50));

        //Rules:
        VBox rulesMainCont = new VBox();
        HBox rulesTitleCont = new HBox();
        rulesTitleCont.setAlignment(Pos.CENTER);
        Text rulesTitle = new Text("RULES");
        rulesTitle.setStyle("-fx-font-size: 30px;");
        rulesTitleCont.getChildren().add(rulesTitle);
        rulesTitleCont.setPrefHeight(100);

        HBox rulSubTitleCont = new HBox();
        rulSubTitleCont.setAlignment(Pos.CENTER);
        Text ruleSubTitle = new Text("These are the rules for Abalone.");
        rulSubTitleCont.getChildren().add(ruleSubTitle);
        rulSubTitleCont.setPrefHeight(50);
        ruleSubTitle.setStyle("-fx-font-size: 20px;");
        GridPane ruleMainCont = new GridPane();
        ruleMainCont.setStyle("-fx-font-size: 18px;");
        ruleMainCont.setPadding(new Insets(50, 50, 50, 50));
        //Setting the vertical and horizontal gaps between the columns
        ruleMainCont.setVgap(50);
        ruleMainCont.setHgap(50);


        Text ruleOneContent = new Text("To be the first player to push six of the opponent's marbles out of play, into the board's outer rim.");
        TextFlow ruleOneCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list7 = ruleOneCont.getChildren();
        list7.addAll(ruleOneContent);

        Text ruleTwoContent = new Text("Black makes the first move");
        TextFlow ruleTwoCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list8 = ruleTwoCont.getChildren();
        list8.addAll(ruleTwoContent);

        Text ruleThreeContent = new Text("You can push one, two or three marbles at a time");
        TextFlow ruleThreeCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list9 = ruleThreeCont.getChildren();
        list9.addAll(ruleThreeContent);

        Text ruleFourContent = new Text("Your marbles can move one space, in a straight line or laterally.");
        TextFlow ruleFourCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list10 = ruleFourCont.getChildren();
        list10.addAll(ruleFourContent);

        Text ruleFiveContent = new Text(" Two marbles can push one and three marbles can push one or two of your opponent's marbles. However, an equal number of marbles cannot push each other (ie: 1 cannot push 1, 2 cannot push 2, etc).");
        TextFlow ruleFiveCont = new TextFlow();
        //Retrieving the observable list of the TextFlow Pane
        ObservableList list11 = ruleFiveCont.getChildren();
        list11.addAll(ruleFiveContent);

        ruleOneCont.setPadding(new Insets(10, 10, 10, 10));
        ruleTwoCont.setPadding(new Insets(10, 10, 10, 10));
        ruleThreeCont.setPadding(new Insets(10, 10, 10, 10));
        ruleFourCont.setPadding(new Insets(10, 10, 10, 10));
        ruleFiveCont.setPadding(new Insets(10, 10, 10, 10));


        ruleOneCont.setStyle("-fx-background-color: #dae7f3;");
        ruleTwoCont.setStyle("-fx-background-color: #dae7f3;");
        ruleThreeCont.setStyle("-fx-background-color: #dae7f3;");
        ruleFourCont.setStyle("-fx-background-color: #dae7f3;");
        ruleFiveCont.setStyle("-fx-background-color: #dae7f3;");


        ruleMainCont.add(ruleOneCont, 1, 0);
        ruleMainCont.add(ruleTwoCont, 1, 1);
        ruleMainCont.add(ruleThreeCont, 1, 2);
        ruleMainCont.add(ruleFourCont, 1, 3);
        ruleMainCont.add(ruleFiveCont, 1, 3);



        rulesMainCont.getChildren().addAll(rulesTitleCont, rulSubTitleCont, ruleMainCont,backCont2);
        Scene ruleScene = new Scene(rulesMainCont);


        Button back3 = new Button("BACK");
        HBox backCont3 = new HBox();
        backCont3.getChildren().add(back3);
        backCont3.setAlignment(Pos.CENTER_RIGHT);
        backCont3.setPadding(new Insets(50, 50, 50, 50));


        //Settings:
        VBox setMainCont = new VBox();
        setMainCont .setPadding(new Insets(10));
        setMainCont .setSpacing(8);
        setMainCont .setAlignment(Pos.TOP_LEFT);
        Text setting1 = new Text("1. To be able to play the game first select the mode on which you prefer to play.");
        setting1.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text setting2 = new Text("2.You can play the game with a human player or with a computer.");
        setting2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text setting3 = new Text("3. If you have choosen already what mode you want to play.");
        setting3.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text setting4 = new Text("4. Then you have first to understand the rules of the game.");
        setting4.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        Text setting5 = new Text("5. If you click on one of the marbels then it will be highlited in Purple this means that you are in the selection step");
        setting5.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text setting6 = new Text("6. If you click on the second marble it will be highlited in blue, You are not done yet.");
        setting6.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        Text setting7 = new Text("7. click again in the second marble to confirm that you are done with selection.");
        setting7.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        Text setting8 = new Text("8. If you are done with selection then the marbels will be highletd with yellow and orange colors.");
        setting8.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        Text setting9 = new Text("9. Then you can perform your movement in any direction you prefer.");
        setting9.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text setting10 = new Text("10. Once you are done you need you have to wait because it is your oppoent's turn .");
        setting10.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text setting11 = new Text("11. To push the opponent marbles then click on the marble itself that you want to push. ");
        setting11.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text setting12 = new Text("12. Once you push one marble out of the board then your score will increase .");
        setting12.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text setting13 = new Text("13. That is all enjoy your game ");
        setting13.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        setMainCont.getChildren().add(setting1);
        setMainCont.getChildren().add(setting2);
        setMainCont.getChildren().add(setting3);
        setMainCont.getChildren().add(setting4);
        setMainCont.getChildren().add(setting5);
        setMainCont.getChildren().add(setting6);
        setMainCont.getChildren().add(setting7);
        setMainCont.getChildren().add(setting8);
        setMainCont.getChildren().add(setting9);
        setMainCont.getChildren().add(setting10);
        setMainCont.getChildren().add(setting11);
        setMainCont.getChildren().add(setting12);
        setMainCont.getChildren().add(setting13);

     
        Scene setScene = new Scene(setMainCont);



        credits.setOnAction(e ->{
            primaryStage.setScene(creditScene);
            back.setOnAction(f-> {
                primaryStage.setScene(scene);
            });
        });

        rules.setOnAction(e ->{
            primaryStage.setScene(ruleScene);
            back2.setOnAction(f-> {
                primaryStage.setScene(scene);
            });
        });

        settings.setOnAction(e ->{
            primaryStage.setScene(setScene);
            back3.setOnAction(f-> {
                primaryStage.setScene(scene);
            });
        });

        VBox  box2  = new VBox();
        Scene AIScene = new Scene(box2);



        //changing to the board scene
        modeOneContainer.setPickOnBounds(true); // allows click on transparent areas
        modeOneContainer.setOnMouseClicked((MouseEvent e) -> {
            Game = new GameGui();
            Game.start(primaryStage);
            
        });

        modeTwoContainer.setPickOnBounds(true); // allows click on transparent areas
        modeTwoContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(AIScene);
            }
        });
    

    }
    private Node createSpacer() {
        final Region spacer = new Region();
        // Make it always grow or shrink according to the available space
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}
