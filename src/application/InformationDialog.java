package application;

import backend.InputReader;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InformationDialog extends Application {
  Alert alert = new Alert(AlertType.INFORMATION);

  /**
   * puts out an information dialog to notify user inputing data occured successfully
   */
  public void start(Stage arg0) {
    alert.setTitle("Data Input Success");
    alert.setHeaderText(null);
    alert.setContentText("You have successfully inputed Milk Weight data");
    alert.showAndWait();
  }

  /**
  * puts out an information dialog to notify user editing data occurred successfully
  * 
  * @param arg0 the gui window
  */
  public void add(Stage arg0) {
    alert.setTitle("Data Edit Success");
    alert.setHeaderText(null);
    alert.setContentText("You have successfully added Milk Weight data");
    alert.showAndWait();
  }

  /**
   * puts out an information dialog to notify user editing data occurred successfully
   * 
   * @param arg0 the gui window
   */
  public void edit(Stage arg0) {
    alert.setTitle("Data Edit Success");
    alert.setHeaderText(null);
    alert.setContentText("You have successfully edited Milk Weight data");
    alert.showAndWait();
  }

  /**
   * puts out an information dialog to notify user inputing file data occurred successfully
   * 
   * @param arg0 the gui window
   * @param ir   the file that has been inputed
   */
  public void inputFile(Stage arg0, InputReader ir) {
    alert.setTitle("File Input Success");
    alert.setHeaderText(null);
    alert.setContentText("You have successfully inputed " + ir.getList().size() + " Milk Weight data");
    alert.showAndWait();
  }

  /**
   * puts out an information dialog to notify user removing data occurred successfully
   * 
   * @param arg0 the gui window
   */
  public void remove(Stage arg0) {
    alert.setTitle("Data Remove Success");
    alert.setHeaderText(null);
    alert.setContentText("You have successfully removed Milk Weight data");
    alert.showAndWait();
  }

  /**
   * puts out an error message for incorrect file input
   * @param arg0 the gui window
   */
  public void fileError(Stage arg0) {
    alert.setTitle("File Input Error");
    alert.setHeaderText(null);
    alert.setContentText("ERROR: There was an error in file");
    alert.showAndWait();
  }

  /**
   * puts out an error message for incorrect file input
   * @param arg0 the gui window
   */
  public void inputError(Stage arg0) {
    alert.setTitle("Input Data Error");
    alert.setHeaderText(null);
    alert.setContentText("ERROR: There was an error with data to input");
    alert.showAndWait();
  }

  /**
   * puts out an error message for incorrect file input
   * @param arg0 the gui window
   */
  public void removeError(Stage arg0) {
    alert.setTitle("Remove Data Error");
    alert.setHeaderText(null);
    alert.setContentText("ERROR: There was an error when trying to remove data");
    alert.showAndWait();
  }
  /**
   * puts out an error message for incorrect file input
   * @param arg0 the gui window
   */
  public void tableFormatError(Stage arg0) {
    alert.setTitle("Invalid input Error");
    alert.setHeaderText(null);
    alert.setContentText("ERROR: There was an error when trying to access table information");
    alert.showAndWait();
  }

  /**
   * puts out an error message for incorrect file input
   * @param arg0 the gui window
   */
  public void editError(Stage arg0) {
    alert.setTitle("Edit Data Error");
    alert.setHeaderText(null);
    alert.setContentText("ERROR: There was an error when trying to find data to edit");
    alert.showAndWait();
  }
}
