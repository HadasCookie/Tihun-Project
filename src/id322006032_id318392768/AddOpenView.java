package id322006032_id318392768;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddOpenView extends ViewPatterns implements ViewClass {
	private BorderPane borderPane;
	private HBox hBoxQu, hBoxAns, hBoxDown;
	private TextField txQu, txAns;
	private Label lbQu, lbAns;
	private Button addBt, returnBt;
	private VBox vBox;

	public AddOpenView() {
		this.borderPane = borderPaneCreation();
		this.borderPane.setTop(stackPaneTextCreation("Adding open Q&A"));
		this.addBt = buttonCreation("Add");
		this.returnBt = buttonCreation("Return to main menu");
		this.hBoxDown = hBoxCreation();
		this.hBoxDown.getChildren().addAll(exitButtonCreation(), returnBt, addBt);
		this.borderPane.setBottom(this.hBoxDown);
		this.lbQu = new Label("Question: ");
		this.txQu = new TextField();
		this.hBoxQu = hBoxCreation();
		this.hBoxQu.getChildren().addAll(this.lbQu, this.txQu);
		this.hBoxQu.setSpacing(10);
		this.lbAns = new Label("Answer: ");
		this.txAns = new TextField();
		this.hBoxAns = hBoxCreation();
		this.hBoxAns.getChildren().addAll(this.lbAns, this.txAns);
		this.hBoxAns.setSpacing(10);
		this.vBox = vBoxCreation();
		this.vBox.getChildren().addAll(this.hBoxQu, this.hBoxAns);
		this.borderPane.setCenter(this.vBox);
	}

	@Override
	public Scene sceneCreation() {
		return sceneCreationSame();
	}

	@Override
	public void cleanScene() {
		this.txQu.clear();
		this.borderPane.getChildren().clear();
		this.txAns.clear();
		cleanScreen();
	}

	public Button getAddBt() {
		return addBt;
	}

	public Button getReturnBt() {
		return returnBt;
	}

	public TextField getTxQu() {
		return txQu;
	}

	public TextField getTxAns() {
		return txAns;
	}

}
