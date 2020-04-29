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
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class EditData extends Application {
  public static final String APP_TITLE = "Edit";

  public void start(Stage primaryStage) {

    // BorderPane setup
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20, 50, 20, 50));
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
    Label clickText = new Label("If you wish to view\n" + "data, please click the\n" + "button below");
    clickText.setAlignment(Pos.CENTER);
    Button displayButton = new Button("Display Data");
    displayButton.setAlignment(Pos.CENTER);
    displayButton.setMaxSize(200, 30);
    displayButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        try {
          DisplayDataPage ddp = new DisplayDataPage();
          ddp.start(primaryStage);
          Main.addHistory(ddp);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    VBox vBox = new VBox();
    vBox.getChildren().addAll(clickText, displayButton);
    vBox.setPadding(new Insets(50, 0, 0, 0));
    vBox.setSpacing(86);
    root.setLeft(vBox);


    // set up center pane for columns that contain old data
    TextField oldDate = new TextField();
    oldDate.setPromptText("Old Date");
    oldDate.setMaxSize(150, 30);
    oldDate.setAlignment(Pos.CENTER);

    TextField oldFarmID = new TextField();
    oldFarmID.setPromptText("Old Farm ID");
    oldFarmID.setMaxSize(150, 30);
    oldFarmID.setAlignment(Pos.CENTER);

    TextField oldMilkWeight = new TextField();
    oldMilkWeight.setPromptText("Old Milk Weight");
    oldMilkWeight.setMaxSize(150, 30);
    oldMilkWeight.setAlignment(Pos.CENTER);

    VBox vBox2 = new VBox();
    vBox2.getChildren().addAll(oldDate, oldFarmID, oldMilkWeight);
    vBox2.setPadding(new Insets(50, 0, 0, 20));
    vBox2.setSpacing(15.0);
    root.setCenter(vBox2);

    // set up right pane (Textfields & Add button)
    TextField newDate = new TextField();
    newDate.setPromptText("New Date");
    newDate.setMaxSize(150, 30);
    newDate.setAlignment(Pos.CENTER);

    TextField newFarmID = new TextField();
    newFarmID.setPromptText("New Farm ID");
    newFarmID.setMaxSize(150, 30);
    newFarmID.setAlignment(Pos.CENTER);

    TextField NewMilkWeight = new TextField();
    NewMilkWeight.setPromptText("New Milk Weight");
    NewMilkWeight.setMaxSize(150, 30);
    NewMilkWeight.setAlignment(Pos.CENTER);

    Button editData = new Button("Edit Data");
    editData.setMaxWidth(Double.MAX_VALUE);
    editData.setAlignment(Pos.CENTER);
    editData.setOnAction(e-> {
      InformationDialog info = new InformationDialog();
      try {
        MilkWeight oldMW = InputReader.parseLine(oldFarmID.getText(), oldDate.getText(), oldMilkWeight.getText());
        MilkWeight newMW = InputReader.parseLine(newFarmID.getText(), newDate.getText(), NewMilkWeight.getText());
        info.edit(primaryStage);
        if (!Main.ds.contains(oldMW)) {
          info.editError(primaryStage, new Exception("Data was not found"));
        } else {
          Main.ds.removeEntry(oldMW);
          Main.ds.insert(newMW);
        }
      } catch (FormatException ex1) {
        info.editError(primaryStage, ex1);
      } catch (Exception ex2) {
        info.editError(primaryStage, ex2);
      }
    });

    VBox vBox3 = new VBox();
    vBox3.getChildren().addAll(newDate, newFarmID, NewMilkWeight, editData);
    vBox3.setPadding(new Insets(50, 0, 0, 0));
    vBox3.setSpacing(15.0);
    root.setRight(vBox3);

    // set up bottom pane
    HBox hBox = new HBox();
    Button backButton = new Button("Back");
    backButton.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #000000");
    backButton.setMinSize(100, 35);
    backButton.setOnAction(e -> {

      try {
        Main.lastUsedStage().start(primaryStage);
      } catch (Exception ex) {
        ex.printStackTrace();
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
    hBox.setPadding(new Insets(0, 0, 0, 20));
    root.setBottom(hBox);

    // make font size all the same
    DoubleProperty fontSize = new SimpleDoubleProperty(13); // font size in pt
    root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", fontSize));

    Scene scene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
