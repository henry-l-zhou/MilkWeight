package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 400;
    public static final String DEFAULT_FONT = "Calibri";
    public static final String APP_TITLE = "Milk Weight Data Visualizer";
    private static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {    
    	stage = primaryStage;
        stage.setTitle(APP_TITLE);
        stage.setScene(MainPageScene.getScene());
        stage.show();    
    }
    
    public static void goToMainPageScene() {
    	stage.setScene(MainPageScene.getScene());
    	stage.show();
    }

    public static void goToEditDataScene() {
    	stage.setScene(EditDataScene.getScene());
    	stage.show();
    }
    
    public static void goToDisplayDataScene() {
    	stage.setScene(AnnualReportPage.getScene());
    	stage.show();
    }
    public static void goToAddDataPage() {
    	stage.setScene(AddDataPage.getScene());
    	stage.show();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
           launch(args);
    }
}