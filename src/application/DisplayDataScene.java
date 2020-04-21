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

public class DisplayDataScene {
  public static Scene getScene() {
    BorderPane root = new BorderPane();
    Button backButton = new Button("Back");
    root.setPadding(new Insets(20, 70, 20, 70));

    BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
    root.setBackground(new Background(bgfill));

    // set up title
    Label titleLabel = new Label("Display Data");
    titleLabel.setStyle("-fx-background-color: #b3ff99; -fx-border-color: #000000");
    titleLabel.setAlignment(Pos.CENTER);
    titleLabel.setMinSize(460, 30);

    //Farm Report Button setup
    Button farmReportButton = new Button("Farm Report");
    farmReportButton.setMinSize(180, 120);
    farmReportButton.setStyle("-fx-background-color: #c679ec; -fx-border-color: #000000");
    farmReportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.goToFarmReportScene();
      }
    });

    //Annual Report Button setup
    Button annualReportButton = new Button("Annual Report");
    annualReportButton.setMinSize(180, 120);
    annualReportButton.setStyle("-fx-background-color: #87CEEB; -fx-border-color: #000000");
    annualReportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.goToAnnualReportScene();
      }
    });

    //Date Range Report Button setup
    Button dateRangeReportButton = new Button("Date Range Report");
    dateRangeReportButton.setMinSize(180, 120);
    dateRangeReportButton.setStyle("-fx-background-color: #fbfbd0; -fx-border-color: #000000");
    dateRangeReportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.goToDateRangeReportScene();
      }
    });

    //Monthly Report Button setup
    Button monthlyReportButton = new Button("Monthly Report");
    monthlyReportButton.setMinSize(180, 120);
    monthlyReportButton.setStyle("-fx-background-color: #ff6666; -fx-border-color: #000000");
    monthlyReportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.goToMonthlyReportScene();
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

    //Back Button Setup
    backButton.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #000000");
    backButton.setMinSize(100, 35);
    backButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main.back();
      }
    });

    // Set up Root
    GridPane center = new GridPane();
    center.setHgap(50);
    center.setVgap(10);
    root.setTop(titleLabel);
    center.setPadding(new Insets(20, 20, 20, 20));
    center.add(farmReportButton, 0, 0);
    center.add(annualReportButton, 1, 0);
    center.add(monthlyReportButton, 0, 1);
    center.add(dateRangeReportButton, 1, 1);

    GridPane bottom = new GridPane();
    bottom.add(backButton, 0, 0);
    bottom.add(homeButton, 1, 0);
    bottom.setHgap(250);
    root.setBottom(bottom);
    root.setCenter(center);
    DoubleProperty fontSize = new SimpleDoubleProperty(13); // font size in pt
    root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", fontSize));

    Scene scene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    return scene;
  }
}
