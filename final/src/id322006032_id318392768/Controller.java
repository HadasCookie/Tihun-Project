/**
 * O.O
 */
package id322006032_id318392768;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import id322006032_id318392768.Question.TypeOfQuestion;

public class Controller implements ModelListener, ViewListener {
	private DataBase model;
	private View view;
	private ArrayList<Test> tests;

	public Controller(DataBase model, View view) throws ClassNotFoundException, IOException {
		this.model = model;
		this.view = view;
		this.tests = new ArrayList<>();
		model.registerListener(this);
		view.registerListener(this);
	}

	@Override
	public boolean anyTestToShow() {
		return this.model.toStringToView();
	}

	@Override
	public void noQuestions() {
		this.view.popMessage("No questions or Answers to show in Database!");

	}

	public void addTest(Test test) {
		// test.sortTest(false);
		// test.saveTestFile(test);
		this.tests.add(test);
	}

	@Override
	public void showAll() {
		this.view.getShowAllView().toStringInScreen(this.model.toString());
	}

	public boolean verify(String question, TypeOfQuestion type, String answer) {
		return this.model.verifyQ(question, type, answer);
	}

	@Override
	public void createOpen(String question, String answer) {
		OpenQuestion q = new OpenQuestion(question);
		OpenAnswer a = new OpenAnswer(answer, q.getId());
		this.model.addQuestion(q);
		this.model.addAnswer(a);
		this.view.popMessage("Question added succesfully");
	}

	@Override
	public void addClosedQ(String question, ArrayList<String> answers, ArrayList<Boolean> corrects) {
		this.model.addClosedQ(question, answers, corrects);

	}

	@Override
	public void dupQu() {
		this.view.popMessage("Question already exist!!");

	}

	@Override
	public void successAddCloseQ(String question, ArrayList<String> answers, ArrayList<Boolean> corrects) {
		this.view.popMessage("Question added succesfully");

	}

	@Override
	public ArrayList<Question> getQToView() {
		return this.model.getQuestions();
	}

	@Override
	public void changeQuContext(String question, String value) {
		this.model.changeQContext(question, value);

	}

	@Override
	public void QChanged() {
		this.view.popMessage("Question context changed Succesfully!");

	}

	@Override
	public ArrayList<CloseAnswer> getClosedAToView() {
		return this.model.getClosedAnswers();
	}

	@Override
	public Boolean changeClosedAnsContext(String answer, String value, Boolean ans) {
		return this.model.changeClosedAnsContext(answer, value, ans);

	}

	@Override
	public ArrayList<OpenAnswer> getOpenAToView() {
		ArrayList<OpenAnswer> arr = this.model.getOpenAnswers();
		return arr;
	}

	@Override
	public void changeOpenAnsContext(String value, String context) {
		this.model.changeOpenAnsContext(value, context);

	}

	@Override
	public void answerChangedSuccesfully() {
		this.view.popMessage("Answer context changed Succesfully!");

	}

	@Override
	public void alreadyTrue() {
		this.view.popMessage("THERE IS ALREADY A TRUE ANSWER!!");

	}

	@Override
	public void delOpenAns(String value) {
		this.model.deleteOpenAns(value);

	}

	@Override
	public void openAnsRemoveSuccess() {
		this.view.popMessage("Answer removed Succesfully");

	}

	@Override
	public void delClosedAns(String value) {
		this.model.deleteClosedAns(value);

	}

	@Override
	public void closedAnsRemoveSuccess() {
		this.view.popMessage("Answer removed Succesfully");

	}

	@Override
	public void initTest(int amount) {
		Test t = new Test(amount);
		t.setNameOfSubject(String.valueOf(this.tests.size()));
		addTest(t);
	}

	@Override
	public void addOpenQuToTest(String value) {
		Question q = this.model.findQuestion(value);
		this.tests.get(this.tests.size() - 1).addQuestion(q, null);

	}

	@Override
	public void addClosedQuToTest(String question, ArrayList<String> answer) {
		Question q = this.model.findQuestion(question);
		Answer a = this.model.findClosedAnswer(answer);
		this.tests.get(this.tests.size() - 1).addQuestion(q, a);
		this.view.popMessage("Q&A added successfully to test.");

	}

	@Override
	public void showTest() {
		this.view.getShowTestView().toStringInScreen(this.tests.get(this.tests.size() - 1).toString());

	}

	@Override
	public void createRandomTest(int num_amount) throws Exception {
		Test test = new Test(num_amount);
		for (int i = 0; i < num_amount; i++) {
			Answer a = null;
			Question q = this.model.getQuestion(this.model.getRandomQuestionIndex());
			if (test.getQuestions().contains(q)) {
				i--;
				continue;
			}
			for (int j = 0; j < this.model.getAnswers().size(); j++) {
				if (this.model.getAnswer(j).getQuestionId() == q.getId()) {
					a = this.model.getAnswer(j);
					break;
				}
			}
			test.addQuestion(q, a);

		}
		test.setNameOfSubject(String.valueOf(this.tests.size()));
		addTest(test);
	}

	@Override
	public void saveManTest() throws FileNotFoundException {
		 this.tests.get(this.tests.size()-1).saveTestFile();
	}

	@Override
	public int maxQuestions() {
		return this.model.getNumQuestions();
	}

	@Override
	public ArrayList<String> testSubjects() {
		ArrayList<String> testSubjets = new ArrayList<>();
		for (Test test : tests) {
			testSubjets.add(test.getSubject());
		}
		return testSubjets;
	}

	@Override
	public int getTests() {
		return this.tests.size();
	}

	@Override
	public void copyTest(String subject) {
		for (Test test : this.tests) {
			if (!(test.getSubject() != null)) {
				if (test.getSubject().equals(subject)) {
					addTest(test);
					break;
				}
			}
			this.view.popMessage("Test copied successfully");
		}
	}

	@Override
	public void compareQuestions(String question1, String question2) {
		this.model.compareQu(question1, question2);

	}

	@Override
	public void compareQu(String shortQu, int shortAns) {
		this.view.popMessage("Shortest question: " + shortQu + "\nWith " + shortAns + " chars in answer");

	}

	@Override
	public void saveToFileFromView() throws FileNotFoundException, IOException {
		this.model.saveDatabaseToBinaryFile();
	}

}