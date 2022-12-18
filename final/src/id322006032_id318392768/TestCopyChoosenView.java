package id322006032_id318392768;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TestCopyChoosenView extends ViewPatterns implements ViewClass {
	
private BorderPane borderPane;
private Button returnBt,chooseBt;
@SuppressWarnings({  "rawtypes" })
private ComboBox comboBox;
private HBox hBoxDown;
@SuppressWarnings({ "unchecked", "rawtypes" })
public TestCopyChoosenView (ArrayList<String> tests) {
	this.borderPane = borderPaneCreation();
	this.borderPane.setTop(stackPaneTextCreation("Please choose a test by subject to copy"));
	this.returnBt = buttonCreation("Return to main menu");
	this.chooseBt = buttonCreation("Choose");
	this.hBoxDown = hBoxCreation();
	this.hBoxDown.getChildren().addAll(exitButtonCreation(),this.returnBt,this.chooseBt);
	this.borderPane.setBottom(this.hBoxDown);
	this.comboBox = new ComboBox(FXCollections.observableArrayList(tests));
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
	public Button getChooseBt() {
		return chooseBt;
	}
	@SuppressWarnings("rawtypes")
	public ComboBox getComboBox() {
		return comboBox;
	}

}
