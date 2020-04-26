package application;

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

public class EditData extends Application {
  public static final String APP_TITLE = "Edit Data";

  public void start(Stage primaryStage) {

    // BorderPane setup
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20, 70, 20, 70));
    BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
    root.setBackground(new Background(bgfill));

    // set up title
    Label titleLabel = new Label(APP_TITLE);
    titleLabel.setStyle("-fx-background-color: #ffcc99; -fx-border-color: #000000");
    titleLabel.setMaxWidth(Double.MAX_VALUE);
    titleLabel.setAlignment(Pos.CENTER);
    titleLabel.setMinSize(300, 30);
    root.setTop(titleLabel);

    // set up left pane (Displays what's going to be added and an add by file button)
    Label displayInfo = new Label("Data:\n" + "Farm ID:\n" + "Milk Weight:");
    displayInfo.setMinSize(100, 150);
    displayInfo.setStyle("-fx-border-color: #000000");
    displayInfo.setPadding(new Insets(0, 20, 0, 20));

    VBox vBox = new VBox();
    vBox.getChildren().addAll(displayInfo);
    vBox.setPadding(new Insets(50, 0, 0, 0));
    vBox.setSpacing(10);
    root.setLeft(vBox);

    // set up right pane (Textfields & Add button)
    TextField date = new TextField();
    date.setPromptText("Date");
    date.setAlignment(Pos.CENTER);

    TextField farmID = new TextField();
    farmID.setPromptText("Farm ID");
    farmID.setAlignment(Pos.CENTER);

    TextField milkWeight = new TextField();
    milkWeight.setPromptText("Milk Weight");
    milkWeight.setAlignment(Pos.CENTER);

    Button editData = new Button("Edit Data");
    editData.setMaxWidth(Double.MAX_VALUE);
    editData.setAlignment(Pos.CENTER);
    editData.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent arg0) {
          // edits manually-entered data
        }
      });

    VBox vBox2 = new VBox();
    vBox2.getChildren().addAll(date, farmID, milkWeight, editData);
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
