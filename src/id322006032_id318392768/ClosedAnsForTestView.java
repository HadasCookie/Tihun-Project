package id322006032_id318392768;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ClosedAnsForTestView extends ViewPatterns implements ViewClass {

	private BorderPane borderPane;
	private Button returnBt, addBt;
	@SuppressWarnings({ "rawtypes" })
	private ComboBox comboBox;
	private HBox hBoxDown;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ClosedAnsForTestView(ArrayList<ArrayList<String>> answersString) {
		this.borderPane = borderPaneCreation();
		this.borderPane.setTop(stackPaneTextCreation("Choose answers for the closed question: "));
		this.returnBt = buttonCreation("Return to main menu");
		this.addBt = buttonCreation("Add to test");
		this.hBoxDown = hBoxCreation();
		this.hBoxDown.getChildren().addAll(exitButtonCreation(), this.returnBt, this.addBt);
		this.borderPane.setBottom(this.hBoxDown);
		this.comboBox = new ComboBox(FXCollections.observableArrayList(answersString));
		this.borderPane.setCenter(this.comboBox);
	}

	@Override
	public Scene sceneCreation() {
		return sceneCreationSame();
	}

	@Override
	public void cleanScene() {
		cleanScreen();
	}

	public Button getReturnBt() {
		return returnBt;
	}

	public Button getAddBt() {
		return addBt;
	}

	@SuppressWarnings("rawtypes")
	public ComboBox getComboBox() {
		return comboBox;
	}

}
