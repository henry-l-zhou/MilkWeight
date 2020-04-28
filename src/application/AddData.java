package application;

import java.io.File;
import backend.MilkWeightDS;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddData extends Application {
  public static final String APP_TITLE = "Add Data";

  public void start(Stage primaryStage) {

    // BorderPane setup
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20, 70, 20, 70));
    BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
    root.setBackground(new Background(bgfill));

    // title setup
    Label titleLabel = new Label(APP_TITLE);
    titleLabel.setStyle("-fx-background-color: #87CEEB; -fx-border-color: #000000");
    titleLabel.setMaxWidth(Double.MAX_VALUE);
    titleLabel.setAlignment(Pos.CENTER);
    titleLabel.setMinSize(300, 30);
    root.setTop(titleLabel);

    // leftpane setup (Gives user the option to view the data if desired)
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

    // file input setup
    FileChooser fc = new FileChooser();
    fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
    Button fileInput = new Button("Add By File Input");
    fileInput.setMaxWidth(Double.MAX_VALUE);
    fileInput.setOnAction(e -> {
      InformationDialog info = new InformationDialog();
      File inputFile = fc.showOpenDialog(primaryStage);
      try {
        InputReader ir = new InputReader(inputFile);
        
        //System.out.println(ir.getList());
        
        ir.getList().forEach(mw->Main.ds.insert(mw));
        Main.ds.getMilkWeightFarm("Farm 0", 2019);
        info.inputFile(primaryStage, ir);
      } catch (NullPointerException ex) {
        info.fileError(primaryStage);
      }
    });

    VBox vBox = new VBox();
    vBox.getChildren().addAll(clickText, displayButton, fileInput);
    vBox.setPadding(new Insets(35, 0, 0, 0));
    vBox.setSpacing(15);
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

    Button addData = new Button("Add Data");
    addData.setMaxWidth(Double.MAX_VALUE);
    addData.setAlignment(Pos.CENTER);
    addData.setOnAction(e -> {
      InformationDialog info = new InformationDialog();
      try {
        MilkWeight item = InputReader.parseLine(farmID.getText(), date.getText(), milkWeight.getText());
        Main.ds.insert(item);
        info.add(primaryStage);
      } catch (Exception ex) {
        info.editError(primaryStage);
      }
    });

    VBox vBox2 = new VBox();
    vBox2.getChildren().addAll(date, farmID, milkWeight, addData);
    vBox2.setPadding(new Insets(20, 0, 0, 0));
    vBox2.setSpacing(15);
    root.setRight(vBox2);

    // set up back button
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
    homeButton.setOnAction(e -> {
      Main main = new Main();
      main.start(primaryStage);
      Main.addHistory(main);
    });

    HBox hBox = new HBox(250);
    hBox.getChildren().addAll(backButton, homeButton);
    root.setBottom(hBox);

    // make font size all the same
    DoubleProperty fontSize = new SimpleDoubleProperty(13); // font size in pt
    root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", fontSize));

    Scene scene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
