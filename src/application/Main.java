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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 400;
	public static final String DEFAULT_FONT = "Calibri";
	public static final String APP_TITLE = "Milk Weight Data Visualizer";

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
		displayDataButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				DisplayDataPage ddp = new DisplayDataPage();
				ddp.start(primaryStage);
			}
		});

		// Edit Button setup
		Button editDataButton = new Button("Edit Data");
		editDataButton.setMinSize(180, 120);
		editDataButton.setStyle("-fx-background-color: #ffcccc; -fx-border-color: #000000");
		editDataButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				EditDataPage edp = new EditDataPage();
				edp.start(primaryStage);
			}
		});

		// Exit Button setup
		Button exitButton = new Button("Exit");
		exitButton.setStyle("-fx-background-color: #f8f8ff; -fx-border-color: #000000");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.close();
			}
		});
		// Title Label setup
		Label titleLabel = new Label("Milk Weight Organizer");
		titleLabel.setAlignment(Pos.CENTER);
		titleLabel.setStyle("-fx-background-color: #f8f8ff; -fx-border-color: #000000");

		DoubleProperty fontSize = new SimpleDoubleProperty(20); // font size in pt
		root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", fontSize));
		// Add the vertical box to the center of the root pane
		root.setTop(titleLabel);
		root.setAlignment(titleLabel, Pos.CENTER);
		root.setLeft(editDataButton);
		root.setAlignment(editDataButton, Pos.CENTER);
		root.setRight(displayDataButton);
		root.setAlignment(displayDataButton, Pos.CENTER);
		root.setBottom(exitButton);
		root.setAlignment(exitButton, Pos.CENTER);
		Scene mainScene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		
		primaryStage.setTitle(Main.APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
