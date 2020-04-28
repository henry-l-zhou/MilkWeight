package application;

import backend.InputReader;
import backend.MilkWeight;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RemoveData extends Application {
  public static final String APP_TITLE = "Remove Data";

  public void start(Stage primaryStage) {

    // BorderPane setup
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20, 70, 20, 70));
    BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
    root.setBackground(new Background(bgfill));

    // set up title
    Label titleLabel = new Label(APP_TITLE);
    titleLabel.setStyle("-fx-background-color: #ccccff; -fx-border-color: #000000");
    titleLabel.setMaxWidth(Double.MAX_VALUE);
    titleLabel.setAlignment(Pos.CENTER);
    titleLabel.setMinSize(300, 30);
    root.setTop(titleLabel);

    // set up left pane (Displays what's going to be added and an add by file button)
    Label clickText = new Label("If you wish to view\n" + "data, please click the\n" + "button below");
    clickText.setAlignment(Pos.CENTER);
    Button displayButton = new Button("Display Data");
    displayButton.setAlignment(Pos.CENTER);
    displayButton.setMaxWidth(Double.MAX_VALUE);
    displayButton.setOnAction(e -> {
      try {
        DisplayDataPage ddp = new DisplayDataPage();
        ddp.start(primaryStage);
        Main.addHistory(ddp);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });

    VBox vBox = new VBox();
    vBox.getChildren().addAll(clickText, displayButton);
    vBox.setPadding(new Insets(50, 0, 0, 0));
    vBox.setSpacing(83);
    root.setLeft(vBox);

    // set up right pane (Textfields & Add button)
    TextField date = new TextField();
    date.setPromptText("Date (Ex: 2019-1-1)");
    date.setAlignment(Pos.CENTER);

    TextField farmID = new TextField();
    farmID.setPromptText("Farm ID");
    farmID.setAlignment(Pos.CENTER);

    TextField milkWeight = new TextField();
    milkWeight.setPromptText("Milk Weight");
    milkWeight.setAlignment(Pos.CENTER);

    Button removeData = new Button("Remove Data");
    removeData.setMaxWidth(Double.MAX_VALUE);
    removeData.setAlignment(Pos.CENTER);
    removeData.setOnAction(e -> {
      InformationDialog info = new InformationDialog();
      try {
        MilkWeight mw = InputReader.parseLine(farmID.getText(), date.getText(), milkWeight.getText());
        if (Main.ds.removeEntry(mw)) {
          info.remove(primaryStage);
        } else {
          
          info.removeError(primaryStage, new Exception("Entry could not be found"));
        }
      } catch (Exception ex) {
        info.removeError(primaryStage, ex);
      }
    });

    VBox vBox2 = new VBox();
    vBox2.getChildren().addAll(date, farmID, milkWeight, removeData);
    vBox2.setPadding(new Insets(50, 0, 0, 0));
    vBox2.setSpacing(15.0);
    root.setRight(vBox2);

    // set up bottom pane
    HBox hBox = new HBox();
    Button backButton = new Button("Back");
    backButton.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #000000");
    backButton.setMinSize(100, 35);
    backButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        try {
          Main.lastUsedStage().start(primaryStage);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    Button homeButton = new Button("Home");
    homeButton.setStyle("-fx-background-color: #FFB6C1; -fx-border-color: #000000");
    homeButton.setMinSize(100, 35);
    homeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main main = new Main();
        main.start(primaryStage);
        Main.addHistory(main);
      }
    });

    hBox.getChildren().addAll(backButton, homeButton);
    hBox.setSpacing(250.0);
    root.setBottom(hBox);

    // make font size all the same
    DoubleProperty fontSize = new SimpleDoubleProperty(13); // font size in pt
    root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", fontSize));

    Scene scene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
