package application;

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

/**
 * This class sets the monthly report of the gui
 * 
 * @author ateam85
 *
 */
public class MonthlyReport extends Application {
  public static final String APP_TITLE = "Monthly Report";
  private ObservableList<MilkWeightData> data;
  private MonthlyReportProcessor mrp;

  /**
   * starts the application
   * @param primaryStage
   */
  public void start(Stage primaryStage) {

    // GridPane setup
    GridPane root = new GridPane();
    root.setPadding(new Insets(20, 70, 20, 70));
    BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
    root.setBackground(new Background(bgfill));

    // set up title
    Label titleLabel = new Label(APP_TITLE);
    titleLabel.setStyle("-fx-background-color: #ff6666; -fx-border-color: #000000");
    titleLabel.setMaxWidth(Double.MAX_VALUE);
    titleLabel.setAlignment(Pos.CENTER);
    titleLabel.setMinSize(460, 30);
    root.add(titleLabel, 0, 0);

    // set up Enter month
    TextField month = new TextField();
    month.setPromptText("Enter Month");
    month.setAlignment(Pos.CENTER);
    month.setMaxWidth(150);

    // set up Enter Year
    TextField year = new TextField();
    year.setPromptText("Enter Year");
    year.setAlignment(Pos.CENTER);
    year.setMaxWidth(150);

    // setup a send to csv button
    Button sendToCSV = new Button("SendToCSV");
    sendToCSV.setVisible(false);
    sendToCSV.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #000000");
    sendToCSV.setMinSize(100, 40);
    sendToCSV.setOnAction(e -> {
      InformationDialog info = new InformationDialog();
      if (mrp != null) {
        mrp.toCSV();
        info.toCSV(primaryStage);
      } else {
        info.toCSVInvalid(primaryStage, new Exception("CSV could not be created"));
      }
    });

    // set up Table of Data
    TableView<MilkWeightData> table = new TableView<MilkWeightData>();
    table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    table.setEditable(true);


    TableColumn farmCol = new TableColumn("Farm ID");
    farmCol.setMinWidth(100);
    farmCol.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("farmID"));
    farmCol.setStyle("-fx-alignment: CENTER;");

    TableColumn weightCol = new TableColumn("Total Weight");
    weightCol.setMinWidth(150);
    weightCol.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("totalMilkWeight"));
    weightCol.setStyle("-fx-alignment: CENTER;");

    TableColumn percentageCol = new TableColumn("%");
    percentageCol.setMinWidth(100);
    percentageCol.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("totalPercent"));
    percentageCol.setStyle("-fx-alignment: CENTER;");

    TableColumn minCol = new TableColumn("Min");
    minCol.setMinWidth(100);
    minCol.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("min"));
    minCol.setStyle("-fx-alignment: CENTER;");

    TableColumn maxCol = new TableColumn("Max");
    maxCol.setMinWidth(100);
    maxCol.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("max"));
    maxCol.setStyle("-fx-alignment: CENTER;");

    TableColumn avgCol = new TableColumn("Average");
    avgCol.setMinWidth(100);
    avgCol.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("avg"));
    avgCol.setStyle("-fx-alignment: CENTER;");

    // set up Display Data
    Button displayData = new Button("Display Data");
    displayData.setMaxWidth(Double.MAX_VALUE);
    displayData.setOnAction(e -> {

      try {
        mrp = new MonthlyReportProcessor(Main.ds, Integer.parseInt(month.getText()), Integer.parseInt(year.getText()));
        data = FXCollections.observableArrayList();

        for (String farmId : mrp.uniqueFarms()) {
          data.add(new MilkWeightData(farmId, String.valueOf(mrp.getWeight(farmId)),
              String.valueOf(mrp.getPercent(farmId)), String.valueOf(mrp.getMin(farmId)),
              String.valueOf(mrp.getMax(farmId)), String.valueOf(mrp.getAvg(farmId))));
        }
        table.setItems(data);
        sendToCSV.setVisible(true);
      } catch (Exception ex) {
        InformationDialog info = new InformationDialog();
        info.tableFormatError(primaryStage, ex);
      }

    });

    // add buttons/textfields to the hBox
    HBox hBox = new HBox();
    hBox.setSpacing(20);
    hBox.setPadding(new Insets(20, 0, 0, 0));
    hBox.getChildren().addAll(month, year, displayData);
    root.add(hBox, 0, 1);

    table.setItems(data);
    table.getColumns().addAll(farmCol, weightCol, percentageCol, minCol, maxCol, avgCol);

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
    homeButton.setOnAction(e -> {

      Main main = new Main();
      main.start(primaryStage);
      Main.addHistory(main);

    });

    hBox2.getChildren().addAll(backButton, sendToCSV, homeButton);
    hBox2.setSpacing(68);
    root.add(hBox2, 0, 3);

    // make font size all the same
    DoubleProperty fontSize = new SimpleDoubleProperty(13); // font size in pt
    root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", fontSize));

    Scene scene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
