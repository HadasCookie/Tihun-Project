package id322006032_id318392768;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TestManuallyView extends ViewPatterns implements ViewClass{
	private BorderPane borderPane;
	private Button returnBt,openBt,closedBt;
	private HBox hBoxDown,hbBoxBt;
	public TestManuallyView(int amount) {
	this.borderPane = borderPaneCreation();
	this.borderPane.setTop(stackPaneTextCreation("Select Type of question for question #: "+amount));
	this.closedBt = buttonCreation("Closed Question");
	this.openBt = buttonCreation("Open Question");
	this.hbBoxBt = hBoxCreation();
	this.hbBoxBt.getChildren().addAll(this.closedBt,this.openBt);
	this.hBoxDown = hBoxCreation();
	this.returnBt = buttonCreation("Return to main menu");
	this.hBoxDown = hBoxCreation();
	this.hBoxDown.getChildren().addAll(exitButtonCreation(),this.returnBt);
	this.borderPane.setBottom(this.hBoxDown);
	this.borderPane.setCenter(this.hbBoxBt);


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

	public Button getOpenBt() {
		return openBt;
	}
	public Button getClosedBt() {
		return closedBt;
	}
}
