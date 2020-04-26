package application;

import backend.MilkWeight;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FarmReport extends Application{
  public static final String APP_TITLE = "Farm Report";
  private final static ObservableList<MilkWeight> data =
      FXCollections.observableArrayList(new MilkWeight(2018, 1, 1, "Farm 1", 5451),
          new MilkWeight(2018, 1, 1,  "Farm 2", 8754), new MilkWeight(2019, 1, 1,  "Farm 1", 84125),
          new MilkWeight(2019, 1, 1,  "Farm 3", 9874), new MilkWeight(2019, 1, 1,  "Farm 4", 74106));

  public void start(Stage primaryStage) {

    // GridPane setup
    GridPane root = new GridPane();
    root.setPadding(new Insets(20, 70, 20, 70));
    BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
    root.setBackground(new Background(bgfill));

    // set up title
    Label titleLabel = new Label(APP_TITLE);
    titleLabel.setStyle("-fx-background-color: #c679ec; -fx-border-color: #000000");
    titleLabel.setMaxWidth(Double.MAX_VALUE);
    titleLabel.setAlignment(Pos.CENTER);
    titleLabel.setMinSize(460, 30);
    root.add(titleLabel, 0, 0);

    // set up Enter FarmID
    TextField farmID = new TextField();
    farmID.setPromptText("Enter Farm ID");
    farmID.setAlignment(Pos.CENTER);
    farmID.setMaxWidth(150);
    
    // set up Enter Year
    TextField year = new TextField();
    year.setPromptText("Enter Year");
    year.setAlignment(Pos.CENTER);
    year.setMaxWidth(150);

    // set up Display Data
    Button displayData = new Button("Display Data");
    displayData.setMaxWidth(Double.MAX_VALUE);
    displayData.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        // displays data to the Table of Data
      }
    });
    // add buttons/textfields to the hBox
    HBox hBox = new HBox();
    hBox.setSpacing(20);
    hBox.setPadding(new Insets(20, 0, 0, 0));
    hBox.getChildren().addAll(farmID, year, displayData);
    root.add(hBox, 0, 1);

    // set up Table of Data
    TableView<MilkWeight> table = new TableView<MilkWeight>();
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.setEditable(true);

    TableColumn yearCol = new TableColumn("Year");
    yearCol.setMinWidth(100);
    yearCol.setCellValueFactory(new PropertyValueFactory<MilkWeight, String>("date"));
    yearCol.setStyle("-fx-alignment: CENTER;");

    TableColumn farmCol = new TableColumn("Farm ID");
    farmCol.setMinWidth(100);
    farmCol.setCellValueFactory(new PropertyValueFactory<MilkWeight, String>("farmId"));
    farmCol.setStyle("-fx-alignment: CENTER;");

    TableColumn weightCol = new TableColumn("Weight");
    weightCol.setMinWidth(200);
    weightCol.setCellValueFactory(new PropertyValueFactory<MilkWeight, String>("weight"));
    weightCol.setStyle("-fx-alignment: CENTER;");

    table.setItems(data);
    table.getColumns().addAll(yearCol, farmCol, weightCol);

    VBox vBox = new VBox();
    vBox.setSpacing(5);
    vBox.setPadding(new Insets(10, 0, 10, 0));
    vBox.getChildren().addAll(table);
    root.add(vBox, 0, 2);

    // set up Back and Home button
    HBox hBox2 = new HBox();
    Button backButton = new Button("Back");
    backButton.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #000000");
    backButton.setMinSize(100, 40);
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
    homeButton.setMinSize(100, 40);
    homeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Main main = new Main();
        main.start(primaryStage);
        Main.addHistory(main);
      }
    });

    hBox2.getChildren().addAll(backButton, homeButton);
    hBox2.setSpacing(250);
    root.add(hBox2, 0, 3);

    // make font size all the same
    DoubleProperty fontSize = new SimpleDoubleProperty(13); // font size in pt
    root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", fontSize));

    Scene scene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
