package application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class EditDataScene {
	public static Scene getScene() {
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		return scene;
	}
}
