package application;

import java.util.Stack;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
  public static final int WINDOW_WIDTH = 600;
  public static final int WINDOW_HEIGHT = 400;
  public static final String DEFAULT_FONT = "Calibri";
  public static final String APP_TITLE = "Milk Weight Data Visualizer";
  private static Stack<Application> history = new Stack<Application>();
  public static MilkWeightDS ds;

  public void start(Stage primaryStage) {

    // BorderPane setup
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20, 70, 20, 70));
    BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
    root.setBackground(new Background(bgfill));

    // Display Button setup
    Button displayDataButton = new Button("Display Data");
    displayDataButton.setMinSize(180, 120);
    displayDataButton.setStyle("-fx-background-color: #b3ff99; -fx-border-color: #000000");
    displayDataButton.setOnAction(e -> {
      DisplayDataPage ddp = new DisplayDataPage();
      ddp.start(primaryStage);
      history.push(ddp);
    });

    // Edit Button setup	
    Button editDataButton = new Button("Edit Data");
    editDataButton.setMinSize(180, 120);
    editDataButton.setStyle("-fx-background-color: #ffcccc; -fx-border-color: #000000");
    editDataButton.setOnAction(e -> {
      EditDataPage edp = new EditDataPage();
      edp.start(primaryStage);
      history.push(edp);
    });

    // Exit Button setup
    Button exitButton = new Button("Exit");
    exitButton.setStyle("-fx-background-color: #f8f8ff; -fx-border-color: #000000");
    exitButton.setOnAction(e -> { primaryStage.close(); });

    // Title Label setup
    Label titleLabel = new Label(" Milk Weight Organizer ");
    titleLabel.setAlignment(Pos.CENTER);
    titleLabel.setStyle("-fx-background-color: #f8f8ff; -fx-border-color: #000000");

    DoubleProperty fontSize = new SimpleDoubleProperty(20); // font size in pt
    root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", fontSize));
    // Add the vertical box to the center of the root pane
    root.setTop(titleLabel);
    BorderPane.setAlignment(titleLabel, Pos.CENTER);
    root.setLeft(editDataButton);
    BorderPane.setAlignment(editDataButton, Pos.CENTER);
    root.setRight(displayDataButton);
    BorderPane.setAlignment(displayDataButton, Pos.CENTER);
    root.setBottom(exitButton);
    BorderPane.setAlignment(exitButton, Pos.CENTER);
    Scene mainScene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

    primaryStage.setTitle(Main.APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  public static void addHistory(Application stage) {
    history.push(stage);
  }

  public static Application lastUsedStage() {
    history.pop();
    return history.peek() == null ? new Main() : history.peek();
  }

  public static void main(String[] args) {
    history.push(new Main());
    ds = new MilkWeightDS();
    launch(args);
  }
}
