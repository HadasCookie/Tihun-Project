package id322006032_id318392768;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditClosedAnsView extends ViewPatterns implements ViewClass {

	private BorderPane borderPane;
	private Button returnBt, changeBt;
	private Label label;
	private TextField textField;
	@SuppressWarnings({ "rawtypes" })
	private ComboBox comboBox;
	private HBox hBoxDown, hBoxContext, hBoxCombo, hBoxChoice;
	private ToggleGroup group;
	private RadioButton rButtoncorrent, rButtonNotCorrect;
	private VBox vBox;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditClosedAnsView(ArrayList<String> answerStrings) {
		this.borderPane = borderPaneCreation();
		this.borderPane.setTop(stackPaneTextCreation("Edit closed question's specific choice - Choo"));
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
		this.comboBox = new ComboBox(FXCollections.observableArrayList(answerStrings));
		this.hBoxCombo = hBoxCreation();
		this.hBoxCombo.getChildren().add(this.comboBox);
		this.hBoxCombo.setSpacing(10);
		this.vBox = vBoxCreation();
		this.hBoxChoice = hBoxCreation();
		group = new ToggleGroup();
		rButtoncorrent = new RadioButton("Correct Answer");
		rButtonNotCorrect = new RadioButton("Incorrect Answer");
		this.rButtoncorrent.setToggleGroup(this.group);
		this.rButtonNotCorrect.setToggleGroup(this.group);
		this.hBoxChoice.getChildren().addAll(this.rButtoncorrent, this.rButtonNotCorrect);
		this.vBox.getChildren().addAll(this.hBoxCombo, this.hBoxContext, this.hBoxChoice);
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

	public ToggleGroup getGroup() {
		return group;
	}

	public RadioButton getrButtoncorrent() {
		return rButtoncorrent;
	}

}
