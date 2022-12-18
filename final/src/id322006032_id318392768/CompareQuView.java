package id322006032_id318392768;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class CompareQuView extends ViewPatterns implements ViewClass {

	private BorderPane borderPane;
	private Button returnBt, compareBt;
	@SuppressWarnings({ "rawtypes" })
	private ComboBox comboBoxQ1, comboBoxQ2;
	private HBox hBoxDown, hBoxCombo;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CompareQuView(ArrayList<String> questions) {
		this.borderPane = borderPaneCreation();
		this.borderPane.setTop(stackPaneTextCreation("Please choose a test by subject to copy"));
		this.returnBt = buttonCreation("Return to main menu");
		this.compareBt = buttonCreation("Choose");
		this.hBoxDown = hBoxCreation();
		this.hBoxDown.getChildren().addAll(exitButtonCreation(), this.returnBt, this.compareBt);
		this.borderPane.setBottom(this.hBoxDown);
		this.comboBoxQ1 = new ComboBox(FXCollections.observableArrayList(questions));
		this.comboBoxQ2 = new ComboBox(FXCollections.observableArrayList(questions));
		this.hBoxCombo = hBoxCreation();
		this.hBoxCombo.getChildren().addAll(this.comboBoxQ1, this.comboBoxQ2);
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

	public Button getCompareBt() {
		return compareBt;
	}

	@SuppressWarnings("rawtypes")
	public ComboBox getComboBoxQ1() {
		return comboBoxQ1;
	}

	@SuppressWarnings("rawtypes")
	public ComboBox getComboBoxQ2() {
		return comboBoxQ2;
	}

}
