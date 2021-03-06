package application;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * GUI representation of the date range report
 * @author atem85
 *
 */
public class DateRangeReport extends Application {
	public static final String APP_TITLE = "Date Range Report"; //page title
	private ObservableList<MilkWeightData> data; //observable list for the table
	private DateRangeReportProcessor drrp; //provides stats on the data

	public void start(Stage primaryStage) {

		// GridPane setup
		GridPane root = new GridPane();
		root.setPadding(new Insets(20, 70, 20, 70));
		BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
		root.setBackground(new Background(bgfill));

		// set up title
		Label titleLabel = new Label(APP_TITLE);
		titleLabel.setStyle("-fx-background-color: #fbfbd0; -fx-border-color: #000000");
		titleLabel.setMaxWidth(Double.MAX_VALUE);
		titleLabel.setAlignment(Pos.CENTER);
		titleLabel.setMinSize(460, 30);
		root.add(titleLabel, 0, 0);

		// set up Enter month
		TextField startDate = new TextField();
		startDate.setPromptText("Enter Start Date");
		startDate.setAlignment(Pos.CENTER);
		startDate.setMaxWidth(150);

		// set up Enter Year
		TextField endDate = new TextField();
		endDate.setPromptText("Enter End Date");
		endDate.setAlignment(Pos.CENTER);
		endDate.setMaxWidth(150);

		// setup a send to csv button
		Button sendToCSV = new Button("SendToCSV");
		sendToCSV.setVisible(false);
		sendToCSV.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #000000");
		sendToCSV.setMinSize(100, 40);
		sendToCSV.setOnAction(e -> {
			InformationDialog info = new InformationDialog();
			if (drrp != null) {
				drrp.toCSV();
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
				//parse the date from text field
				String[] fromDateSplit = startDate.getText().split("-");
				String yearFrom = fromDateSplit[0];
				String monthFrom = fromDateSplit[1];
				String dateFrom = fromDateSplit[2];
				
				//parse the date to text field
				String[] toDateSplit = endDate.getText().split("-");
				String yearTo = toDateSplit[0];
				String monthTo = toDateSplit[1];
				String dateTo = toDateSplit[2];
				
				//get the stats for the date range
				drrp = new DateRangeReportProcessor(Main.ds, Integer.parseInt(dateFrom), Integer.parseInt(monthFrom),
						Integer.parseInt(yearFrom), Integer.parseInt(dateTo), Integer.parseInt(monthTo),
						Integer.parseInt(yearTo));
				data = FXCollections.observableArrayList();

				for (String farmId : drrp.uniqueFarms()) { //for each farm, add a new row with stats from the DRRP
					data.add(new MilkWeightData(farmId, String.valueOf(drrp.getWeight(farmId)),
							String.valueOf(drrp.getPercent(farmId)), String.valueOf(drrp.getMin(farmId)),
							String.valueOf(drrp.getMax(farmId)), String.valueOf(drrp.getAvg(farmId))));
				}
				table.setItems(data); //set the observable list to the table
				sendToCSV.setVisible(true);
			} catch (Exception ex) {
				InformationDialog info = new InformationDialog();
				info.tableFormatError(primaryStage, ex);

			}
		});

		table.setItems(data);
		table.getColumns().addAll(farmCol, weightCol, percentageCol, minCol, maxCol, avgCol);

		// add buttons/textfields to the hBox
		HBox hBox = new HBox(20);
		hBox.setPadding(new Insets(20, 0, 0, 0));
		hBox.getChildren().addAll(startDate, endDate, displayData);
		root.add(hBox, 0, 1);

		VBox vBox = new VBox(5);
		vBox.setPadding(new Insets(10, 0, 10, 0));
		vBox.getChildren().addAll(table);
		root.add(vBox, 0, 2);

		// set up Back and Home button
		HBox hBox2 = new HBox();
		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #000000");
		backButton.setMinSize(100, 40);
		backButton.setOnAction(e -> {
			try {
				Main.lastUsedStage().start(primaryStage);
			} catch (Exception ex) {
				ex.printStackTrace();
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
