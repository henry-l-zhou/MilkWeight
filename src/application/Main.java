package application;
import java.util.List;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Main extends Application {
    // store any command-line arguments that were entered.
    // NOTE: this.getParameters().getRaw() will get these also
    private List<String> args;

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final String APP_TITLE = "Milk Weight Data Visualizer";
    private static final String DEFAULT_FONT = "Calibri";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // save args example
        args = this.getParameters().getRaw();
        
        //BorderPane setup
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20, 70, 20, 70));
        BackgroundFill bgfill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        root.setBackground(new Background(bgfill));
        
        //Display Button setup
        Button displayDataButton = new Button("Display Data");
        displayDataButton.setMinSize(180, 120);
        displayDataButton.setStyle("-fx-background-color: #ffcccc; -fx-border-color: #000000");
        
        
        //Edit Button setup
        Button editDataButton = new Button("Edit Data");
        editDataButton.setMinSize(180, 120);
        editDataButton.setStyle("-fx-background-color: #b3ff99; -fx-border-color: #000000");
        
        //Exit Button setup
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #f8f8ff; -fx-border-color: #000000");
        
        //Title Label setup
        Label titleLabel = new Label("  Milk Weight Organizer  ");
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
        Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Add the stuff and set the primary stage
            primaryStage.setTitle(APP_TITLE);
            primaryStage.setScene(mainScene);
            primaryStage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
           launch(args);
    }
}