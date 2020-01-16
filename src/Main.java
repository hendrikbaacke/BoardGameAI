package src;

import java.io.FileInputStream;

import AI.WeightOptimisation.EvolutionaryAlgo;
import AI.WeightOptimisation.GameSimulation.GameEnvironment;
import AI.WeightOptimisation.GameSimulation.Simulation;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
 * Sets up the whole game. Creates the start screen and links everything.
 */

public class Main extends Application {

    DropShadow shadow = new DropShadow();
    public GameGui Game;

    
    public void start(Stage primaryStage) throws Exception {
        //Define Window title
        primaryStage.setTitle("Team 1 -  Project 2.1");
        
        VBox sliderBox = new VBox();
        sliderBox.setAlignment(Pos.CENTER);
        sliderBox.setPrefHeight(500);
        sliderBox.setSpacing(50);
        
        HBox chooseP = new HBox();
        chooseP.setAlignment(Pos.CENTER);
        chooseP.setSpacing(20);
        
        Button twoP = new Button("Two players");
        Button threeP = new Button("Three players");
        twoP.setStyle("-fx-background-color: darkgray");
        threeP.setStyle("-fx-background-color: white");
        
        twoP.setOnAction(e ->{
        	threeP.setStyle("-fx-background-color: white");
            GameData.numberPlayers = 2;
            twoP.setStyle("-fx-background-color: darkgray");
        });
        threeP.setOnAction(e ->{
        	twoP.setStyle("-fx-background-color: white");
            GameData.numberPlayers =3;
            threeP.setStyle("-fx-background-color: darkgray");
        }); 
        chooseP.getChildren().addAll(twoP, threeP);

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
        title.setText("Abalone: Play it!");
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

        sliderBox.getChildren().addAll(title, menuBox, chooseP);

        //Add game mode images
        ImageView iv_1 = new ImageView();
        iv_1.setImage(new Image(new FileInputStream("PvP.png")));
        ImageView iv_2 = new ImageView();
        iv_2.setImage(new Image(new FileInputStream("PvC.png")));
        ImageView iv_3 = new ImageView();
        iv_3.setImage(new Image(new FileInputStream("CvC.png")));

        //Add game mode labels
        Label modeOneLabel = new Label("Player vs. Player");
        //modeOneLabel.setStyle("-fx-font: 20 arial;");
        Label modeTwoLabel = new Label("Player vs. Computer");
        //modeTwoLabel.setStyle("-fx-font: 20 arial;");
        Label modeThreeLabel = new Label("Computer vs. Computer");

        //Defining layouts
        VBox mainContainer = new VBox();
        VBox modeOneContainer = new VBox();
        VBox modeTwoContainer = new VBox();
        VBox modeThreeContainer = new VBox();

        HBox modeBox = new HBox();

        modeOneContainer.getStyleClass().add("modeButton" );
        modeOneContainer.setPadding(new Insets(10,10,10,10));
        modeTwoContainer.getStyleClass().add("modeButton");
        modeTwoContainer.setPadding(new Insets(10,10,10,10));
        modeThreeContainer.getStyleClass().add("modeButton");
        modeThreeContainer.setPadding(new Insets(10,10,10,10));

        //some fancy shadow effects
        modeOneContainer.setOnMouseEntered(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent t) {
                iv_1.setEffect(shadow);
            }
        });
        modeOneContainer.setOnMouseExited(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent t) {
                iv_1.setEffect(null);
            }
        });
        modeTwoContainer.setOnMouseEntered(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent t) { 
                iv_2.setEffect(shadow);
            }
        });
        modeTwoContainer.setOnMouseExited(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent t) {
                iv_2.setEffect(null);
            }
        });
        modeThreeContainer.setOnMouseEntered(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent t) {
                iv_3.setEffect(shadow);
            }
        });
        modeThreeContainer.setOnMouseExited(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent t) {
                iv_3.setEffect(null);
            }
        });

        modeOneContainer.getChildren().addAll(iv_1,modeOneLabel );
        modeOneContainer.setAlignment(Pos.CENTER);
        modeTwoContainer.getChildren().addAll(iv_2,modeTwoLabel);
        modeTwoContainer.setAlignment(Pos.CENTER);
        modeThreeContainer.getChildren().addAll(iv_3,modeThreeLabel);
        modeThreeContainer.setAlignment(Pos.CENTER);

        modeBox.getChildren().addAll(createSpacer(),modeOneContainer,createSpacer(), modeTwoContainer,createSpacer(), modeThreeContainer,createSpacer());
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
        GridPane ruleMainCont = new RulesPane().create();

        rulesMainCont.getChildren().addAll(rulesTitleCont, rulSubTitleCont, ruleMainCont,backCont2);
        Scene ruleScene = new Scene(rulesMainCont);


        Button back3 = new Button("BACK");
        HBox backCont3 = new HBox();
        backCont3.getChildren().add(back3);
        backCont3.setAlignment(Pos.CENTER_RIGHT);
        backCont3.setPadding(new Insets(50, 50, 50, 50));


        //Settings:
        VBox setMainCont = new VBox();
        HBox settingsTitleCont = new HBox();
        settingsTitleCont.setAlignment(Pos.CENTER);
        Text settingsTitle = new Text("SETTINGS");
        settingsTitle.setStyle("-fx-font-size: 30px;");
        settingsTitleCont.getChildren().add(settingsTitle);
        settingsTitleCont.setPrefHeight(100);
        HBox setSubTitleCont = new HBox();
        setSubTitleCont.setAlignment(Pos.CENTER);
        Text setSubTitle = new Text("Explaining how to play the game");
        setSubTitleCont.getChildren().add(setSubTitle);
        setSubTitleCont.setPrefHeight(50);
        setSubTitle.setStyle("-fx-font-size: 20px;");
        GridPane SetCont = new SettingsPane().create();

        setMainCont.getChildren().addAll(settingsTitleCont, setSubTitleCont,SetCont, backCont3);

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
            	Move.player1AI = false;
            	if (GameData.numberPlayers ==2) {
            		Move.player2AI = true;
            	}
            	else {
            		Move.player2AI = true;
            		Move.player3AI = true;
            	}
            	
            	Game = new GameGui();
                Game.start(primaryStage);
            }
        });

        modeThreeContainer.setPickOnBounds(true); // allows click on transparent areas
        modeThreeContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Move.player1AI = true;
                Move.player2AI = true;

                if (GameData.numberPlayers ==3) {
                    Move.player3AI = true;
                }

                Game = new GameGui();
                Game.start(primaryStage);

                //AI.WeightOptimisation.GameSimulation.GameEnvironment.GameEnvironment();
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
