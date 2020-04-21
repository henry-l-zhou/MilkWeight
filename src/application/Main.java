package application;

import java.util.Stack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  public static final int WINDOW_WIDTH = 600;
  public static final int WINDOW_HEIGHT = 400;
  public static final String DEFAULT_FONT = "Calibri";
  public static final String APP_TITLE = "Milk Weight Data Visualizer";
  private static Stage stage;
  private static Stack<Scene> sceneStack;

  public Main() {
    sceneStack = new Stack<Scene>();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    stage = primaryStage;
    stage.setTitle(APP_TITLE);
    stage.setScene(MainPageScene.getScene());
    sceneStack.push(MainPageScene.getScene());
    stage.show();
  }

  public static void goToMainPageScene() {
    stage.setScene(MainPageScene.getScene());
    sceneStack.push(MainPageScene.getScene());
    stage.show();
  }

  public static void goToEditDataScene() {
    stage.setScene(EditDataScene.getScene());
    sceneStack.push(EditDataScene.getScene());
    stage.show();
    System.out.println(sceneStack);
  }

  public static void goToDisplayDataScene() {
    stage.setScene(DisplayDataScene.getScene());
    sceneStack.push(DisplayDataScene.getScene());
    stage.show();
  }

  public static void goToAddDataScene() {
    stage.setScene(AddDataPage.getScene());
    sceneStack.push(AddDataPage.getScene());
    stage.show();
  }

  public static void goToRemoveDataScene() {
    //stage.setScene(RemoveDataPage.getScene());
    stage.show();
  }

  //this goes to the scene after the edit button is clicked on the edit data page
  public static void goToEditScene() {
    //stage.setScene(EditPage.getScene());
    stage.show();
  }

  public static void goToFarmReportScene() {
    //stage.setScene(FarmReportPage.getScene());
    stage.show();
  }

  public static void goToMonthlyReportScene() {
    //stage.setScene(MonthlyReportPage.getScene());
    stage.show();
  }

  public static void goToDateRangeReportScene() {
    //stage.setScene(DateRangeReport.getScene());
    stage.show();
  }

  public static void goToAnnualReportScene() {
    stage.setScene(AnnualReportPage.getScene());
    sceneStack.push(AnnualReportPage.getScene());
    stage.show();
  }

  public static void back() {
    sceneStack.pop();
    stage.setScene(sceneStack.peek());
    
  }

  public static void exit() {
    stage.close();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
