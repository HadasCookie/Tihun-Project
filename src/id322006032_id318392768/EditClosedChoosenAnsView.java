package id322006032_id318392768;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class EditClosedChoosenAnsView extends ViewPatterns implements ViewClass {

	private BorderPane borderPane;
	private Button returnBt, nextBt;
	@SuppressWarnings({ "rawtypes" })
	private ComboBox comboBox;
	private HBox hBoxDown, hBoxCombo;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditClosedChoosenAnsView(ArrayList<ArrayList<String>> answers) {
		this.borderPane = borderPaneCreation();
		this.borderPane.setTop(stackPaneTextCreation("Edit closed question's specific choice"));
		this.returnBt = buttonCreation("Return to main menu");
		this.nextBt = buttonCreation("Next");
		this.hBoxDown = hBoxCreation();
		this.hBoxDown.getChildren().addAll(exitButtonCreation(), this.returnBt, this.nextBt);
		this.borderPane.setBottom(this.hBoxDown);
		this.comboBox = new ComboBox(FXCollections.observableArrayList(answers));
		this.hBoxCombo = hBoxCreation();
		this.hBoxCombo.getChildren().add(this.comboBox);
		this.hBoxCombo.setSpacing(10);
		this.borderPane.setCenter(this.hBoxCombo);
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

	public Button getNextBt() {
		return nextBt;
	}

	@SuppressWarnings("rawtypes")
	public ComboBox getComboBox() {
		return comboBox;
	}
}
