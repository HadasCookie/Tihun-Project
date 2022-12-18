package id322006032_id318392768;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditOpenAnsView extends ViewPatterns implements ViewClass {

	private BorderPane borderPane;
	private Button returnBt, changeBt;
	private Label label;
	private TextField textField;
	@SuppressWarnings({ "rawtypes" })
	private ComboBox comboBox;
	private HBox hBoxDown, hBoxContext, hBoxCombo;
	private VBox vBox;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditOpenAnsView(ArrayList<String> answersString) {
		this.borderPane = borderPaneCreation();
		this.borderPane.setTop(stackPaneTextCreation("Edit open answer"));
		this.changeBt = buttonCreation("Change");
		this.returnBt = buttonCreation("Return to main menu");
		this.hBoxDown = hBoxCreation();
		this.hBoxDown.getChildren().addAll(exitButtonCreation(), this.returnBt, this.changeBt);
		this.borderPane.setBottom(this.hBoxDown);
		this.label = new Label("New Context");
		this.textField = new TextField();
		this.hBoxContext = hBoxCreation();
		this.hBoxContext.getChildren().addAll(this.label, this.textField);
		this.hBoxContext.setSpacing(10);
		this.comboBox = new ComboBox(FXCollections.observableArrayList(answersString));
		this.hBoxCombo = hBoxCreation();
		this.hBoxCombo.getChildren().add(this.comboBox);
		this.hBoxCombo.setSpacing(10);
		this.vBox = vBoxCreation();
		this.vBox.getChildren().addAll(this.hBoxCombo, this.hBoxContext);
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

	public Button getReturnBt() {
		return returnBt;
	}

	public Button getChangeBt() {
		return changeBt;
	}

	public TextField getTextField() {
		return textField;
	}

	@SuppressWarnings("rawtypes")
	public ComboBox getComboBox() {
		return comboBox;
	}

}
