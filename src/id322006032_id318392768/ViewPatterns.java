/**
 * O.O
 */
package id322006032_id318392768;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ViewPatterns {
	// Same style for the whole project
	private String style = "-fx-background-color: ALICEBLUE; -fx-border-color: WHITE; -fx-border-width: 2px; -fx-font-size: 20;";
	//JavaFX variables
	private HBox hBox;
	private Button button, exitButton;
	private StackPane stackPane;
	private BorderPane borderPane;
	private VBox vBox;

// Designs
	// HBox designs for all HBoxes
	public HBox hBoxCreation() {
		this.hBox = new HBox(5);
		this.hBox.setMinSize(450, 50);
		this.hBox.setPadding(new Insets(10, 10, 10, 10));
		this.hBox.setAlignment(Pos.CENTER);
		return hBox;
	}
	public VBox vBoxCreation() {
		this.vBox = new VBox();
		this.vBox.setSpacing(10);
		this.vBox.setAlignment(Pos.CENTER);
		return this.vBox;
	}
	public BorderPane borderPaneCreation() {
		this.borderPane = new BorderPane();
		//this.borderPane.setStyle("-fx-background-color: red");
		return this.borderPane;
	}


	// Button Design, with the text in it (optional)
	public Button buttonCreation(String text) {
		this.button = new Button(text);
		this.button.setStyle(this.style);
		return this.button;
	}

// Patterns
	// StackPane for texts
	public StackPane stackPaneTextCreation(String text) {
		this.stackPane = new StackPane();
		Label center = new Label(text);
		center.setStyle(this.style);
		this.stackPane.getChildren().add(center);
		this.stackPane.setAlignment(Pos.TOP_CENTER);
		return this.stackPane;
	}

	// Exit button option
	public Button exitButtonCreation() {
		this.exitButton = buttonCreation("Exit");
		this.exitButton.setOnAction(e -> {
			
			javafx.application.Platform.exit();
		});
		return this.exitButton;
	}
	//Init scene same size for each window
	public Scene sceneCreationSame() {
		return new Scene(this.borderPane, 600, 700);
	}
	//Cleaning StackPane from screen.
	public void cleanScreen() {
		this.stackPane.getChildren().clear();
		this.borderPane.getChildren().clear();
	}
}
