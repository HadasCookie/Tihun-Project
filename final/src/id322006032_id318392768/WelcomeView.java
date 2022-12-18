/**
 * O.O
 */
package id322006032_id318392768;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WelcomeView extends ViewPatterns implements ViewClass {
	private Button showAllBt, addQnaBt, editQuBt, editAnsBt, deleteAndBt, crTestManualBt, crTestAutoBt, crTestCopyBt,
			compareQuBt;
	private BorderPane borderPane;
	private VBox vBox;
	private HBox hBoxDown,hBoxUp;

	public WelcomeView() throws FileNotFoundException {
		this.borderPane = borderPaneCreation();
		this.borderPane.setTop(new ImageView(new Image(new FileInputStream("pic.png"))));
		this.hBoxDown = hBoxCreation();
		this.hBoxUp = hBoxCreation();
		this.hBoxDown.getChildren().add(exitButtonCreation());
		this.hBoxUp.getChildren().addAll(stackPaneTextCreation("Hello! Please choose from the following"),new ImageView(new Image(new FileInputStream("pic.png"))));
		this.hBoxUp.setSpacing(10);
		this.borderPane.setBottom(this.hBoxDown);
		this.borderPane.setTop(this.hBoxUp);
		this.vBox = vBoxCreation();
		this.showAllBt = buttonCreation("Present all questions and answers of the test");
		this.addQnaBt = buttonCreation("Add question and answer");
		this.editQuBt = buttonCreation("Edit question");
		this.editAnsBt = buttonCreation("Edit answer");
		this.deleteAndBt = buttonCreation("Delete answer");
		this.crTestManualBt = buttonCreation("Create test manually");
		this.crTestAutoBt = buttonCreation("Create test automatically");
		this.crTestCopyBt = buttonCreation("Create test that copy of other test");
		this.compareQuBt = buttonCreation("Compare two questions according to the length of their answer");
		this.vBox.getChildren().addAll(this.showAllBt,this.addQnaBt, this.editQuBt, this.editAnsBt, this.deleteAndBt,
				this.crTestManualBt, this.crTestAutoBt, this.crTestCopyBt, this.compareQuBt);
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

	public Button getShowAllBt() {
		return showAllBt;
	}

	public Button getAddQnaBt() {
		return addQnaBt;
	}

	public Button getEditQuBt() {
		return editQuBt;
	}

	public Button getEditAnsBt() {
		return editAnsBt;
	}

	public Button getDeleteAndBt() {
		return deleteAndBt;
	}

	public Button getCrTestManualBt() {
		return crTestManualBt;
	}

	public Button getCrTestAutoBt() {
		return crTestAutoBt;
	}

	public Button getCrTestCopyBt() {
		return crTestCopyBt;
	}

	public Button getCompareQuBt() {
		return compareQuBt;
	}

}
