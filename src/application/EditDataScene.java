package application;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class EditDataScene {
  public static Scene getScene() {
    BorderPane root = new BorderPane();
    Button backButton = new Button("Back");
    root.setPadding(new Insets(20, 70, 20, 70));

    BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
    root.setBackground(new Background(bgfill));

    // set up title
    Label titleLabel = new Label("Edit Data");
    titleLabel.setStyle("-fx-background-color: #ffcccc; -fx-border-color: #000000");
    titleLabel.setAlignment(Pos.CENTER);
    titleLabel.setMinSize(460, 30);

    // Add Button setup
    Button addButton = new Button("Add");
    addButton.setMinSize(120, 100);
    addButton.setStyle("-fx-background-color: #87CEEB; -fx-border-color: #000000");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.goToAddDataScene();
      }
    });

    // Edit Report Button setup
    Button editButton = new Button("Edit");
    editButton.setMinSize(120, 100);
    editButton.setStyle("-fx-background-color: #ffcc99; -fx-border-color: #000000");
    editButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.goToEditScene();
      }
    });

    // Remove Report Button setup
    Button removeButton = new Button("Remove");
    removeButton.setMinSize(120, 100);
    removeButton.setStyle("-fx-background-color: #ccccff; -fx-border-color: #000000");
    removeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.goToRemoveDataScene();
      }
    });

    // Home Button setup
    Button homeButton = new Button("Home");
    homeButton.setStyle("-fx-background-color: #FFB6C1; -fx-border-color: #000000");
    homeButton.setMinSize(100, 35);
    homeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.goToMainPageScene();
      }
    });

    // Back Button Setup
    backButton.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #000000");
    backButton.setMinSize(100, 35);
    backButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.back();
      }
    });

    // Set up Root
    HBox hbox = new HBox();
    hbox.setSpacing(50);
    root.setTop(titleLabel);
    hbox.setPadding(new Insets(100, 0, 50, 0));
    hbox.getChildren().add(addButton);
    hbox.getChildren().add(editButton);
    hbox.getChildren().add(removeButton);

    GridPane bottom = new GridPane();
    bottom.add(backButton, 0, 0);
    bottom.add(homeButton, 1, 0);
    bottom.setHgap(250);


    root.setBottom(bottom);
    root.setCenter(hbox);
    DoubleProperty fontSize = new SimpleDoubleProperty(13); // font size in pt
    root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", fontSize));

    Scene scene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    return scene;
  }
}
