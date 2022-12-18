

package id322006032_id318392768;

import id322006032_id318392768.Question.TypeOfQuestion;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class View {
	private Stage stage;
	private ArrayList<ViewListener> viewListener;
	private WelcomeView welcomeView;
	private ShowAllView showAllView;
	private AddOpenView addOpenView;
	private AddChoosenView addChoiceView;
	private AddClosedView addClosedView;
	private AddClosedViewMoreAns addClosedViewMoreAns;
	private EditQView editQView;
	private EditAnsChooseQView editAnsChooseQView;
	private EditClosedChoosenAnsView editClosedChoosenAnsView;
	private EditClosedAnsView editClosedAnsView;
	private EditOpenAnsView editOpenAnsView;
	private DeleteOpenAnsView deleteOpenAnsView;
	private DeleteClosedChoosenAnsView deleteClosedChoosenAnsView;
	private DeleteClosedAnsView deleteClosedAnsView;
	private TestCountView testCountView;
	private TestManuallyView testManuallyView;
	private OpenQuForTestView openQuForTestView;
	private ClosedQuForTestView closedQuForTestView;
	private ClosedAnsForTestView closedAnsForTestView;
	private ShowTestView showTestView;
	private TestCopyChoosenView testCopyChoosenView;
	private CompareQuView compareQuView;

	public View(Stage stage) {
		this.viewListener = new ArrayList<>();
		this.stage = new Stage();
		this.stage.setTitle("Program");
		shortWelcomeView();
	}

	private void welcomeView() throws FileNotFoundException {
		this.welcomeView = new WelcomeView();
		this.stage.setScene(this.welcomeView.sceneCreation());
		this.stage.show();
		this.welcomeView.getShowAllBt().setOnAction(e -> {
			for (ViewListener v : viewListener) {
				if (v.anyTestToShow()) {
					this.welcomeView.cleanScene();
					shortWelcomeView();
				} else {
					this.welcomeView.cleanScene();
					showAllView();
				}
			}
		});
		this.welcomeView.getAddQnaBt().setOnAction(e -> {
			this.welcomeView.cleanScene();
				try {
					addChoiceView();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		});
		this.welcomeView.getEditQuBt().setOnAction(e -> {
			this.welcomeView.cleanScene();
			editQView();
		});
		this.welcomeView.getEditAnsBt().setOnAction(e -> {
			this.welcomeView.cleanScene();
			editAnsChooseQView(false);
		});
		this.welcomeView.getDeleteAndBt().setOnAction(e -> {
			editAnsChooseQView(true);
		});
		this.welcomeView.getCrTestManualBt().setOnAction(e -> {
			testCountView(false);
		});
		this.welcomeView.getCrTestAutoBt().setOnAction(e -> {
			testCountView(true);
		});
		this.welcomeView.getCrTestCopyBt().setOnAction(e -> {
			testCopyChoosenView();
		});
		this.welcomeView.getCompareQuBt().setOnAction(e -> {
			compareQuView();

		});
	}

	private void compareQuView() {
		ArrayList<Question> questions = questionsInView();
		ArrayList<String> questionsStrings = new ArrayList<>();
		for (Question question : questions)
			questionsStrings.add(question.getQuestion());
		this.compareQuView = new CompareQuView(questionsStrings);
		this.stage.setScene(this.compareQuView.sceneCreation());
		this.stage.show();
		this.compareQuView.getReturnBt().setOnAction(e -> {
			this.compareQuView.cleanScene();
			shortWelcomeView();
		});
		this.compareQuView.getCompareBt().setOnAction(e -> {
			if (this.compareQuView.getComboBoxQ1().getValue() == null
					|| this.compareQuView.getComboBoxQ2().getValue() == null)
				this.popMessage("PLEASE CHOOSE A QUESTIONS!");
			else if (this.compareQuView.getComboBoxQ1().getValue()
					.equals(this.compareQuView.getComboBoxQ2().getValue()))
				this.popMessage("PLEASE CHOOSE DIFFRENET QUESTIONS!");
			else {
				for (ViewListener v : viewListener)
					v.compareQuestions((String) this.compareQuView.getComboBoxQ1().getValue(),
							(String) this.compareQuView.getComboBoxQ2().getValue());
				this.compareQuView.cleanScene();
				shortWelcomeView();
			}
		});
	}

	private void testCopyChoosenView() {
		int amountOfTests = 0;
		for (ViewListener v : viewListener)
			amountOfTests = v.getTests();
		if (amountOfTests == 0) {
			this.popMessage("NO TESTS TO COPY!");
			shortWelcomeView();
		} else {
			ArrayList<String> testSubjects = new ArrayList<>();
			for (ViewListener v : viewListener)
				testSubjects = v.testSubjects();
			this.testCopyChoosenView = new TestCopyChoosenView(testSubjects);
			this.stage.setScene(this.testCopyChoosenView.sceneCreation());
			this.stage.show();
			this.testCopyChoosenView.getReturnBt().setOnAction(e -> {
				this.testCopyChoosenView.cleanScene();
				shortWelcomeView();
			});
			this.testCopyChoosenView.getChooseBt().setOnAction(e -> {
				if (this.testCopyChoosenView.getComboBox().getValue() == null)
					this.popMessage("PLEASE CHOOSE A TEST!");
				else {
					for (ViewListener v : viewListener)
						v.copyTest((String) this.testCopyChoosenView.getComboBox().getValue());
					this.testCopyChoosenView.cleanScene();
					shortWelcomeView();
				}
			});
		}
	}

	private void testCountView(boolean auto) {
		this.testCountView = new TestCountView();
		this.stage.setScene(this.testCountView.sceneCreation());
		this.stage.show();
		this.testCountView.getReturnBt().setOnAction(e -> {
			this.testCountView.cleanScene();
			shortWelcomeView();
		});
		this.testCountView.getNextBt().setOnAction(e -> {
			String amount = this.testCountView.getTextField().getText();
			int num_amount = 0, max_amount = 0;
			for (ViewListener v : viewListener)
				max_amount = v.maxQuestions();
			if (amount.equals("")) {
				this.popMessage("PLEASE ENTER AMOUNT");
				this.testCountView.cleanScene();
				testCountView(auto);
			} else if ((!(amount.chars().allMatch(Character::isDigit)) || amount.equals("0"))) {
				this.popMessage("PLEASE ENTER PROPER VALUE!!");
				this.testCountView.cleanScene();
				testCountView(auto);
			} else {
				num_amount = Integer.parseInt(amount);
				if (num_amount > max_amount) {
					this.popMessage("NOT ENOUGH QUESTIONS. PLEASE ENTER A NUMBER SMALLER THAN " + max_amount);
					this.testCountView.cleanScene();
					testCountView(auto);
				} else {
					for (ViewListener v : viewListener)
						v.initTest(num_amount);
					this.testCountView.cleanScene();
					if (auto)
						try {
							testAuto(num_amount);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					else
						try {
							testManuallyView(num_amount, 0);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		});

	}

	private void testAuto(int num_amount) throws Exception {
		for (ViewListener v : viewListener) {
			v.createRandomTest(num_amount);
		}

		showTestView();

	}

	private void testManuallyView(int amount, int i) throws FileNotFoundException {
		if (i == amount) {
			this.testManuallyView.cleanScene();
			for (ViewListener v : viewListener) {
				System.out.println("llll");
				v.saveManTest();
			}
			showTestView();
		} else {
			testManuallyView = new TestManuallyView(i + 1);
			this.stage.setScene(this.testManuallyView.sceneCreation());
			this.stage.show();
			this.testManuallyView.getReturnBt().setOnAction(e -> {
				this.testManuallyView.cleanScene();
				shortWelcomeView();
			});
			this.testManuallyView.getOpenBt().setOnAction(e -> {
				this.testManuallyView.cleanScene();
				openQuForTestView(amount, i);
			});
			this.testManuallyView.getClosedBt().setOnAction(e -> {
				this.testManuallyView.cleanScene();
				closedQuForTestView(amount, i);
			});
		}
	}

	private void closedQuForTestView(int amount, int i) {
		int j = i;
		ArrayList<Question> questions = questionsInView();
		ArrayList<String> questionsStrings = new ArrayList<>();
		for (Question question : questions) {
			if (question instanceof CloseQuestion)
				questionsStrings.add(question.getQuestion());
		}
		this.closedQuForTestView = new ClosedQuForTestView(questionsStrings);
		this.stage.setScene(this.closedQuForTestView.sceneCreation());
		this.stage.show();
		this.closedQuForTestView.getReturnBt().setOnAction(e -> {
			this.closedQuForTestView.cleanScene();
			shortWelcomeView();
		});
		this.closedQuForTestView.getNextBt().setOnAction(e -> {
			if (this.closedQuForTestView.getComboBox().getValue() == null) {
				this.popMessage("CHOOSE A QUESTION TO ADD");
				this.closedQuForTestView.cleanScene();
				closedQuForTestView(amount, j);
			} else {
				String context = (String) this.closedQuForTestView.getComboBox().getValue();
				this.closedQuForTestView.cleanScene();
				closedAnsForTestView(context, i, amount);
			}
		});

	}

	private ArrayList<Question> questionsInView() {
		for (ViewListener v : viewListener)
			return v.getQToView();
		return null;
	}

	private void closedAnsForTestView(String question, int i, int amount) {
		ArrayList<CloseAnswer> closedAnswers = new ArrayList<>();
		for (ViewListener v : viewListener)
			closedAnswers = v.getClosedAToView();
		ArrayList<ArrayList<String>> answersString = new ArrayList<ArrayList<String>>();
		for (CloseAnswer c : closedAnswers)
			answersString.add(c.getAnswers());
		closedAnsForTestView = new ClosedAnsForTestView(answersString);
		this.stage.setScene(this.closedAnsForTestView.sceneCreation());
		this.stage.show();
		this.closedAnsForTestView.getReturnBt().setOnAction(e -> {
			this.closedAnsForTestView.cleanScene();
			shortWelcomeView();
		});
		this.closedAnsForTestView.getAddBt().setOnAction(e -> {
			if (this.closedAnsForTestView.getComboBox().getValue() == null) {
				this.popMessage("PLEASE CHOOSE AN ANSWER!");
				this.closedAnsForTestView.cleanScene();
				closedAnsForTestView(question, i, amount);
			} else {
				@SuppressWarnings("unchecked")
				ArrayList<String> answer = (ArrayList<String>) this.closedAnsForTestView.getComboBox().getValue();
				for (ViewListener v : viewListener)
					v.addClosedQuToTest(question, answer);
				this.closedAnsForTestView.cleanScene();
				int j = i + 1;
				try {
					testManuallyView(amount, j);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	private void openQuForTestView(int amount, int i) {
		int j = i;
		ArrayList<Question> questions = questionsInView();
		ArrayList<String> questionsStrings = new ArrayList<>();
		for (Question question : questions) {
			if (question instanceof OpenQuestion)
				questionsStrings.add(question.getQuestion());
		}
		this.openQuForTestView = new OpenQuForTestView(questionsStrings);
		this.stage.setScene(this.openQuForTestView.sceneCreation());
		this.stage.show();
		this.openQuForTestView.getReturnBt().setOnAction(e -> {
			this.openQuForTestView.cleanScene();
			shortWelcomeView();
		});
		this.openQuForTestView.getAddBt().setOnAction(e -> {

			if (this.openQuForTestView.getComboBox().getValue() == null) {
				this.popMessage("CHOOSE QUESTION TO ADD!");
				this.openQuForTestView.cleanScene();
				openQuForTestView(amount, j);
			} else {
				for (ViewListener v : viewListener)
					v.addOpenQuToTest((String) this.openQuForTestView.getComboBox().getValue());
				this.openQuForTestView.cleanScene();
				int j2 = i + 1;
				try {
					testManuallyView(amount, j2);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void shortWelcomeView() {
		try {
			welcomeView();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private void showAllView() {
		this.showAllView = new ShowAllView();
		this.stage.setScene(this.showAllView.sceneCreation());
		for (ViewListener v : viewListener) {
			v.showAll();
		}
		this.stage.show();
		this.showAllView.getButton().setOnAction(e -> {
			this.showAllView.cleanScene();
			shortWelcomeView();
		});

	}

	private void showTestView() {
		this.showTestView = new ShowTestView();
		this.stage.setScene(this.showTestView.sceneCreation());
		for (ViewListener v : viewListener) {
			v.showTest();
		}
		this.stage.show();
		this.showTestView.getButton().setOnAction(e -> {
			this.showTestView.cleanScene();
			shortWelcomeView();
		});

	}

	private void addChoiceView() throws FileNotFoundException, IOException {
		this.addChoiceView = new AddChoosenView();
		this.stage.setScene(this.addChoiceView.sceneCreation());
		this.stage.show();
		this.addChoiceView.getReturnBt().setOnAction(e -> {
			this.addChoiceView.cleanScene();
			shortWelcomeView();
		});
		this.addChoiceView.getOpenBt().setOnAction(e -> {
			this.addChoiceView.cleanScene();
			addOpenView();
		});
		this.addChoiceView.getClosedBt().setOnAction(e -> {
			this.addChoiceView.cleanScene();
			addClosedView();
		});
	}

	private void addOpenView() {
		this.addOpenView = new AddOpenView();
		this.stage.setScene(this.addOpenView.sceneCreation());
		this.stage.show();
		this.addOpenView.getReturnBt().setOnAction(e -> {
			this.addOpenView.cleanScene();
			shortWelcomeView();
		});
		this.addOpenView.getAddBt().setOnAction(e -> {
			for (ViewListener v : viewListener) {
				String q = this.addOpenView.getTxQu().getText();
				String a = this.addOpenView.getTxAns().getText();
				if (q.equals("") || a.equals("")) {
					this.popMessage("PLEASE ENTER QUESTION / ANSWER");
					this.addOpenView.cleanScene();
					addOpenView();
				} else {
					if (v.verify(q, TypeOfQuestion.openQuestion, a)) {
						this.addOpenView.cleanScene();
						addOpenView();
					} else {
							try {
								v.saveToFileFromView();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						this.addOpenView.cleanScene();
						shortWelcomeView();
					}
				}
			}
		});

	}

	private void addClosedView() {
		this.addClosedView = new AddClosedView();
		this.stage.setScene(this.addClosedView.sceneCreation());
		this.stage.show();
		this.addClosedView.getReturnBt().setOnAction(e -> {
			this.addClosedView.cleanScene();
			shortWelcomeView();
		});
		this.addClosedView.getAddBt().setOnAction(e -> {
			for (ViewListener v : viewListener) {
				ArrayList<String> answers = new ArrayList<>();
				ArrayList<Boolean> corrects = new ArrayList<>();
				String q = this.addClosedView.getTxQu().getText();
				String a = this.addClosedView.getTxAns().getText();
				if (q.equals("") || a.equals("") || this.addClosedView.getGroup().getSelectedToggle() == null) {
					this.popMessage("PLEASE ENTER QUESTION / ANSWER / CHOOSE CORRECT OR NOT!");
					this.addClosedView.cleanScene();
					addClosedView();

				} else if (v.verify(q, TypeOfQuestion.closeQuestion, a)) {
					this.addClosedView.cleanScene();
					shortWelcomeView();
				} else {
					Boolean correct = this.addClosedView.getGroup().getSelectedToggle()
							.equals(this.addClosedView.getrButtoncorrent()) ? true : false;
					answers.add(a);
					corrects.add(correct);
					MoreToAdd(q, answers, corrects);
				}

			}
		});

	}

	private void addClosedViewMoreAns(String question, ArrayList<String> answers, ArrayList<Boolean> corrects) {
		addClosedViewMoreAns = new AddClosedViewMoreAns(question);
		this.stage.setScene(this.addClosedViewMoreAns.sceneCreation());
		this.stage.show();
		this.addClosedViewMoreAns.getReturnBt().setOnAction(e -> {
			for (ViewListener v : viewListener) {
				v.addClosedQ(question, answers, corrects);
			}
			this.addClosedViewMoreAns.cleanScene();
			shortWelcomeView();
		});
		this.addClosedViewMoreAns.getAddBt().setOnAction(e -> {
			String a = this.addClosedViewMoreAns.getTxAns().getText();
			if (a.equals("") || this.addClosedViewMoreAns.getGroup().getSelectedToggle() == null) {
				this.popMessage("PLEASE ENTER ANSWER / CHOOSE CORRECT OR NOT!");
				this.addClosedViewMoreAns.cleanScene();
				addClosedViewMoreAns(question, answers, corrects);
			} else if ((corrects.contains(true)) && (this.addClosedViewMoreAns.getGroup().getSelectedToggle()
					.equals(this.addClosedViewMoreAns.getrButtoncorrent()))) {
				this.popMessage("Correct answer already was chosen!!");
				this.addClosedViewMoreAns.cleanScene();
				addClosedViewMoreAns(question, answers, corrects);
			} else {
				Boolean correct = this.addClosedViewMoreAns.getGroup().getSelectedToggle()
						.equals(this.addClosedViewMoreAns.getrButtoncorrent()) ? true : false;
				corrects.add(correct);
				answers.add(a);
				MoreToAdd(question, answers, corrects);
			}
		});
	}

	private void editQView() {
		ArrayList<Question> questions = questionsInView();
		ArrayList<String> questionsStrings = new ArrayList<>();
		for (Question question : questions) {
			questionsStrings.add(question.getQuestion());
		}

		editQView = new EditQView(questionsStrings);
		this.stage.setScene(this.editQView.sceneCreation());
		this.stage.show();
		this.editQView.getReturnBt().setOnAction(e -> {
			this.editQView.cleanScene();
			shortWelcomeView();
		});
		this.editQView.getChangeBt().setOnAction(e -> {
			String context = this.editQView.getTextField().getText();
			if (context.equals("") || this.editQView.getComboBox().getValue() == null) {
				this.popMessage("PLEASE ENTER CONTEXT TO CHANGE / CHOOSE QUESTION TO EDIT!");
				this.editQView.cleanScene();
				editQView();
			} else {
				for (ViewListener v : viewListener) {
					v.changeQuContext((String) this.editQView.getComboBox().getValue(), context);
					this.editQView.cleanScene();
					shortWelcomeView();
				}
			}
		});
	}

	private void editAnsChooseQView(boolean delete) {
		editAnsChooseQView = new EditAnsChooseQView();
		this.stage.setScene(this.editAnsChooseQView.sceneCreation());
		this.stage.show();
		this.editAnsChooseQView.getReturnBt().setOnAction(e -> {
			this.editAnsChooseQView.cleanScene();
			shortWelcomeView();
		});
		this.editAnsChooseQView.getClosedBt().setOnAction(e -> {
			this.editAnsChooseQView.cleanScene();
			if (delete)
				deleteClosedChoosenAnsView();
			else
				editClosedChoosenAnsView();
		});
		this.editAnsChooseQView.getOpenBt().setOnAction(e -> {
			this.editAnsChooseQView.cleanScene();
			if (delete)
				deleteOpenAnsView();
			else
				editOpenAnsView();
		});
	}

	private void deleteOpenAnsView() {
		ArrayList<OpenAnswer> openAnswers = new ArrayList<>();
		for (ViewListener v : viewListener)
			openAnswers = v.getOpenAToView();
		ArrayList<String> answersString = new ArrayList<>();
		for (OpenAnswer openAnswer : openAnswers)
			answersString.add(openAnswer.getAnswer());
		deleteOpenAnsView = new DeleteOpenAnsView(answersString);
		this.stage.setScene(this.deleteOpenAnsView.sceneCreation());
		this.stage.show();
		this.deleteOpenAnsView.getReturnBt().setOnAction(e -> {
			this.deleteOpenAnsView.cleanScene();
			shortWelcomeView();
		});
		this.deleteOpenAnsView.getDelBt().setOnAction(e -> {
			if (this.deleteOpenAnsView.getComboBox().getValue() == null) {
				this.popMessage("CHOOSE ANSWER TO DELETE!");
				this.deleteOpenAnsView.cleanScene();
				deleteOpenAnsView();
			} else {
				for (ViewListener v : viewListener) {
					v.delOpenAns((String) this.deleteOpenAnsView.getComboBox().getValue());
					try {
						v.saveToFileFromView();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					this.deleteOpenAnsView.cleanScene();
					shortWelcomeView();
				}
			}
		});
	}

	private void deleteClosedChoosenAnsView() {
		ArrayList<CloseAnswer> closedAnswers = new ArrayList<>();
		for (ViewListener v : viewListener)
			closedAnswers = v.getClosedAToView();
		ArrayList<ArrayList<String>> answersString = new ArrayList<ArrayList<String>>();
		for (CloseAnswer c : closedAnswers)
			answersString.add(c.getAnswers());
		deleteClosedChoosenAnsView = new DeleteClosedChoosenAnsView(answersString);
		this.stage.setScene(this.deleteClosedChoosenAnsView.sceneCreation());
		this.stage.show();
		this.deleteClosedChoosenAnsView.getReturnBt().setOnAction(e -> {
			this.deleteClosedChoosenAnsView.cleanScene();
			shortWelcomeView();
		});
		this.deleteClosedChoosenAnsView.getNextBt().setOnAction(e -> {
			if (this.deleteClosedChoosenAnsView.getComboBox().getValue() == null) {
				this.popMessage("CHOOSE CHOICES TO Delete!");
				this.deleteClosedChoosenAnsView.cleanScene();
				deleteClosedChoosenAnsView();
			} else {
				@SuppressWarnings("unchecked")
				ArrayList<String> context = (ArrayList<String>) this.deleteClosedChoosenAnsView.getComboBox()
						.getValue();
				this.deleteClosedChoosenAnsView.cleanScene();
				deleteClosedAnsView(context);
			}
		});
	}

	private void deleteClosedAnsView(ArrayList<String> answers) {
		deleteClosedAnsView = new DeleteClosedAnsView(answers);
		this.stage.setScene(this.deleteClosedAnsView.sceneCreation());
		this.stage.show();
		this.deleteClosedAnsView.getReturnBt().setOnAction(e -> {
			this.deleteClosedAnsView.cleanScene();
			shortWelcomeView();
		});
		this.deleteClosedAnsView.getDelBt().setOnAction(e -> {
			if (this.deleteClosedAnsView.getComboBox().getValue() == null) {
				this.popMessage("PLEASE CHOOSE ANSWER TO DELETE!");
				this.deleteClosedAnsView.cleanScene();
				deleteClosedAnsView(answers);
			} else {
				for (ViewListener v : viewListener) {
					v.delClosedAns((String) this.deleteClosedAnsView.getComboBox().getValue());
					try {
						v.saveToFileFromView();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				this.deleteClosedAnsView.cleanScene();
				shortWelcomeView();
			}
		});
	}

	private void editOpenAnsView() {
		ArrayList<OpenAnswer> openAnswers = new ArrayList<>();
		for (ViewListener v : viewListener)
			openAnswers = v.getOpenAToView();
		ArrayList<String> answersString = new ArrayList<>();
		for (OpenAnswer openAnswer : openAnswers)
			answersString.add(openAnswer.getAnswer());
		editOpenAnsView = new EditOpenAnsView(answersString);
		this.stage.setScene(this.editOpenAnsView.sceneCreation());
		this.stage.show();
		this.editOpenAnsView.getReturnBt().setOnAction(e -> {
			this.editOpenAnsView.cleanScene();
			shortWelcomeView();
		});
		this.editOpenAnsView.getChangeBt().setOnAction(e -> {
			String context = this.editOpenAnsView.getTextField().getText();
			if (context.equals("") || this.editOpenAnsView.getComboBox().getValue() == null) {
				this.popMessage("PLEASE ENTER CONTEXT TO CHANGE / CHOOSE ANSWER TO EDIT!");
				this.editOpenAnsView.cleanScene();
				editOpenAnsView();
			} else {
				for (ViewListener v : viewListener) {
					v.changeOpenAnsContext((String) this.editOpenAnsView.getComboBox().getValue(), context);
					this.editOpenAnsView.cleanScene();
					shortWelcomeView();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void editClosedChoosenAnsView() {
		ArrayList<CloseAnswer> closedAnswers = new ArrayList<>();
		for (ViewListener v : viewListener)
			closedAnswers = v.getClosedAToView();
		ArrayList<ArrayList<String>> answersString = new ArrayList<ArrayList<String>>();
		for (CloseAnswer c : closedAnswers)
			answersString.add(c.getAnswers());
		editClosedChoosenAnsView = new EditClosedChoosenAnsView(answersString);
		this.stage.setScene(this.editClosedChoosenAnsView.sceneCreation());
		this.stage.show();
		this.editClosedChoosenAnsView.getReturnBt().setOnAction(e -> {
			this.editClosedChoosenAnsView.cleanScene();
			shortWelcomeView();
		});
		this.editClosedChoosenAnsView.getNextBt().setOnAction(e -> {
			if (this.editClosedChoosenAnsView.getComboBox().getValue() == null) {
				this.popMessage("CHOOSE CHOICES TO EDIT!");
				this.editClosedChoosenAnsView.cleanScene();
				editClosedChoosenAnsView();
			} else {
				ArrayList<String> context = (ArrayList<String>) this.editClosedChoosenAnsView.getComboBox().getValue();
				this.editClosedChoosenAnsView.cleanScene();
				editClosedAnsView(context);
			}
		});
	}

	private void editClosedAnsView(ArrayList<String> answerStrings) {
		editClosedAnsView = new EditClosedAnsView(answerStrings);
		this.stage.setScene(this.editClosedAnsView.sceneCreation());
		this.stage.show();
		this.editClosedAnsView.getReturnBt().setOnAction(e -> {
			this.editClosedAnsView.cleanScene();
			shortWelcomeView();
		});
		this.editClosedAnsView.getChangeBt().setOnAction(e -> {
			String context = this.editClosedAnsView.getTextField().getText();
			if (context.equals("") || this.editClosedAnsView.getComboBox().getValue() == null
					|| this.editClosedAnsView.getGroup().getSelectedToggle() == null) {
				this.popMessage(
						"PLEASE ENTER CONTEXT TO CHANGE / CHOOSE ANSWER TO EDIT!/CHOOSE CORRECT/NOT FOR ANSWER!");
				this.editClosedAnsView.cleanScene();
				editClosedAnsView(answerStrings);
			} else {
				Boolean ans = this.editClosedAnsView.getGroup().getSelectedToggle()
						.equals(this.editClosedAnsView.getrButtoncorrent()) ? true : false;
				for (ViewListener v : viewListener) {
					if (v.changeClosedAnsContext((String) this.editClosedAnsView.getComboBox().getValue(), context,
							ans)) {
						this.editClosedAnsView.cleanScene();
						editClosedAnsView(answerStrings);
					} else {
						this.editClosedAnsView.cleanScene();
						shortWelcomeView();
					}
				}
			}
		});
	}

	public void registerListener(Controller controller) {
		this.viewListener.add(controller);
	}

	public void popMessage(String text) {
		UIManager.put("OptionPane.minimumSize",new Dimension(500,100)); 
		JOptionPane.showMessageDialog(null, text);
	}

	public WelcomeView getWelcomeView() {
		return welcomeView;
	}

	public ShowAllView getShowAllView() {
		return showAllView;
	}

	public ShowTestView getShowTestView() {
		return showTestView;
	}

	public void MoreToAdd(String question, ArrayList<String> answers, ArrayList<Boolean> corrects) {
		if (answers.size() == 10) {
			this.popMessage("Reached the limit of answers per question!");
			for (ViewListener v : viewListener) {
				v.addClosedQ(question, answers, corrects);
				try {
					v.saveToFileFromView();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			this.addClosedView.cleanScene();
			shortWelcomeView();
		} else {
			if (JOptionPane.showConfirmDialog(null, "Answer added succesfully. Would you like to add another one?",
					"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				this.addClosedView.cleanScene();
				addClosedViewMoreAns(question, answers, corrects);
			} else {
				boolean checkForTrueAns = false;
				for (Boolean ans : corrects) {
					if(ans.equals(true)) {
						checkForTrueAns=true;
					}
				}
				if(!checkForTrueAns) {
					this.popMessage("Can't add closed question with no correct answer!!");
					this.addClosedView.cleanScene();
					shortWelcomeView();

				}else {
				for (ViewListener v : viewListener) {
					v.addClosedQ(question, answers, corrects);
					try {
						v.saveToFileFromView();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				}
				if (answers.size() == 1)
					this.addClosedView.cleanScene();
				else
					this.addClosedViewMoreAns.cleanScene();
				shortWelcomeView();
			}
		}
	}
}
