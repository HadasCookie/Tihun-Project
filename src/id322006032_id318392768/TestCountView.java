package id322006032_id318392768;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TestCountView extends ViewPatterns implements ViewClass {
	
private BorderPane borderPane;
private Button returnBt,nextBt;
private Label label;
private TextField textField;
private HBox hBoxDown,hBoxContext;
public TestCountView() {
	this.borderPane = borderPaneCreation();
	this.borderPane.setTop(stackPaneTextCreation("Test builder"));
	this.nextBt = buttonCreation("Next");
	this.returnBt = buttonCreation("Return to main menu");
	this.hBoxDown = hBoxCreation();
	this.hBoxDown.getChildren().addAll(exitButtonCreation(),this.returnBt,this.nextBt);
	this.borderPane.setBottom(this.hBoxDown);
	this.label = new Label("Amount of questions: ");
	this.textField = new TextField();
	this.hBoxContext = hBoxCreation();
	this.hBoxContext.getChildren().addAll(this.label,this.textField);
	this.hBoxContext.setSpacing(10);
	this.borderPane.setCenter(this.hBoxContext);
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

	public TextField getTextField() {
		return textField;
	}

}
