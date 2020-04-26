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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DisplayDataPage extends Application{
	
  public void start(Stage primaryStage) {
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

    // Farm Report Button setup
    Button farmReportButton = new Button("Farm Report");
    farmReportButton.setMinSize(180, 120);
    farmReportButton.setStyle("-fx-background-color: #c679ec; -fx-border-color: #000000");
    farmReportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
    	  FarmReport frp = new FarmReport();
          frp.start(primaryStage);
          Main.addHistory(frp);
      }
    });

    // Annual Report Button setup
    Button annualReportButton = new Button("Annual Report");
    annualReportButton.setMinSize(180, 120);
    annualReportButton.setStyle("-fx-background-color: #87CEEB; -fx-border-color: #000000");
    annualReportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        AnnualReport arp = new AnnualReport();
        arp.start(primaryStage);
        Main.addHistory(arp);
      }
    });
    // Monthly Report Button setup
    Button monthlyReportButton = new Button("Monthly Report");
    monthlyReportButton.setMinSize(180, 120);
    monthlyReportButton.setStyle("-fx-background-color: #ff6666; -fx-border-color: #000000");
    monthlyReportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
    	  MonthlyReport mrp = new MonthlyReport();
    	  mrp.start(primaryStage);
    	  Main.addHistory(mrp);
      }
    });

    // Date Range Report Button setup
    Button dateRangeReportButton = new Button("Date Range Report");
    dateRangeReportButton.setMinSize(180, 120);
    dateRangeReportButton.setStyle("-fx-background-color: #fbfbd0; -fx-border-color: #000000");
    dateRangeReportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
    	  DateRangeReport drrp = new DateRangeReport();
    	  drrp.start(primaryStage);
    	  Main.addHistory(drrp);
      }
    });


    // Home Button setup
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

    // Back Button Setup
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
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
