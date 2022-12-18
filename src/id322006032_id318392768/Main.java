package id322006032_id318392768;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {

	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		DataBase model = new DataBase();
		View view = new View(stage);
		Controller controller = new Controller(model, view);
		controller.getClass();
		//hi fsfsefsgsg
	}
}