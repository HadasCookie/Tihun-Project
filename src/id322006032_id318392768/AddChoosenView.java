package id322006032_id318392768;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddChoosenView extends ViewPatterns implements ViewClass {
	private Button openBt, closedBt, returnBt;
	private BorderPane borderPane;
	private VBox vBox;
	private HBox hBoxDown;

	public AddChoosenView() {
		this.borderPane = borderPaneCreation();
		this.borderPane.setTop(stackPaneTextCreation("Please choose which type of Q&A"));
		this.hBoxDown = hBoxCreation();
		this.returnBt = buttonCreation("return to main menu");
		this.hBoxDown.getChildren().addAll(exitButtonCreation(), this.returnBt);
		this.borderPane.setBottom(this.hBoxDown);
		this.vBox = vBoxCreation();
		this.openBt = buttonCreation("Open Q&A");
		this.closedBt = buttonCreation("Closed Q&A");
		this.vBox.getChildren().addAll(this.openBt, this.closedBt);
		this.borderPane.setCenter(this.vBox);
	}

	@Override
	public Scene sceneCreation() {
		return sceneCreationSame();
	}

	@Override
	public void cleanScene() {
		cleanScreen();

	}

	public Button getOpenBt() {
		return openBt;
	}

	public Button getClosedBt() {
		return closedBt;
	}

	public Button getReturnBt() {
		return returnBt;
	}

}
