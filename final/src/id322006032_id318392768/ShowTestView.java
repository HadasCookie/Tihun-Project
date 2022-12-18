package id322006032_id318392768;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ShowTestView extends ViewPatterns implements ViewClass {
	private Text text;
	private Button button;
	private HBox hBox;
	private ScrollPane scrollPane;
	private BorderPane borderPane;

	public ShowTestView() {
		this.borderPane = borderPaneCreation();
		this.borderPane.setTop(stackPaneTextCreation("All questions and answers of the test"));
		this.button = buttonCreation("Return to main menu");
		this.hBox = hBoxCreation();
		this.hBox.getChildren().addAll(exitButtonCreation(), this.button);
		this.borderPane.setBottom(this.hBox);
	}

	public void toStringInScreen(String string) {
		this.text = new Text(string);
		this.text.setFill(Color.GREEN);
		this.text.setFont(new Font("Serif", 18));
		this.scrollPane = new ScrollPane();
		this.scrollPane.setFitToHeight(true);
		this.scrollPane.setContent(this.text);
		this.borderPane.setCenter(this.scrollPane);
	}

	@Override
	public Scene sceneCreation() {
		return sceneCreationSame();
	}

	@Override
	public void cleanScene() {
		cleanScreen();

	}

	public Button getButton() {
		return button;
	}

}
