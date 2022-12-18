package id322006032_id318392768;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Test implements Cloneable {

	public static final int MAX_NUM_ANSWERS = 10; // close answer

	private ArrayList<Question> questions;
	private ArrayList<Answer> answers;
	private Set<CloseAnswer> allClosedAnswers;
	private String nameOfSubject;
	private int numOfQuestions;

	public Test(String nameOfSubject) {
		setNameOfSubject(nameOfSubject);
		this.numOfQuestions = 0;
		this.questions = new ArrayList<>();
		this.answers = new ArrayList<>();
		this.allClosedAnswers = new Set<CloseAnswer>();

	}

	public Test(Test test) {
		questions = test.questions;
		answers = test.answers;
		allClosedAnswers = test.allClosedAnswers;
		this.numOfQuestions = test.numOfQuestions;
		this.nameOfSubject = test.nameOfSubject;

	}

	public Test(int numOfQuestions) {
		this.questions = new ArrayList<>();
		this.answers = new ArrayList<>();
		this.allClosedAnswers = new Set<CloseAnswer>();
		this.numOfQuestions = numOfQuestions;
		this.nameOfSubject = "";

	}

	public void resetTest() { // �����

		this.questions = new ArrayList<>();
		this.answers = new ArrayList<>();
		this.numOfQuestions = 0;
		this.allClosedAnswers = new Set<CloseAnswer>();

	}

	public Test(ArrayList<Question> questions, ArrayList<Answer> answer) { // Question and Answer from last test
		this.questions = questions;
		this.answers = answer;
		this.numOfQuestions = questions.size();
		this.allClosedAnswers = new Set<CloseAnswer>();
	}

	public Test clone() throws CloneNotSupportedException {

		return (Test) super.clone();
	}

	public String getNameOfSubject() {
		return nameOfSubject;
	}

	public boolean addQuestion(Question question, Answer answer) {

		if (answer instanceof CloseAnswer) { // set data structure , checks if closeAnswer is valid
			try {

				if (allClosedAnswers.add(answer)) { // true if valid , --> insert to test , else --> don't insert
					this.questions.add(question);
					this.answers.add(answer);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else { // answer is open
			this.questions.add(question); // add
			this.answers.add(answer);
		}
		return true;
	}

	private void swap(int i, int j) {

		Question tempQuest = questions.get(i);
		Answer tempAns = answers.get(i);
		questions.set(i, questions.get(j));
		questions.set(j, tempQuest);
		answers.set(i, answers.get(j));
		answers.set(j, tempAns);
	}

	public void saveTestFile() throws FileNotFoundException {
		LocalDateTime localDate1 = LocalDateTime.now();
		DateTimeFormatter dateformatter1 = DateTimeFormatter.ofPattern("YYYY _MM_ d");
		File file_question = new File("Exem" + dateformatter1.format(localDate1));// save question
		PrintWriter pw_qu = new PrintWriter(file_question);
		this.saveTestQuestionsToTextFile(pw_qu);
		pw_qu.close();

		File file_solution = new File("Solution" + dateformatter1.format(localDate1));// save answer
		PrintWriter pw_sol = new PrintWriter(file_solution);
		this.saveTestSolutionToTextFile(pw_sol);
		pw_sol.close();

	}

	private int partition(boolean flag, int low, int high) throws Exception {

		Question pivot = questions.get(high);

		int i = (low - 1);

		for (int j = low; j <= high - 1; j++) {
			if (!flag) {
				if (compare(questions.get(j), pivot) == -1) {

					i++;
					swap(i, j);
				}
			} else {
				if (compareLen(questions.get(j), pivot) == -1) {

					i++;
					swap(i, j);
				}
			}
		}
		swap(i + 1, high);
		return (i + 1);
	}

	public int compareLen(Question quest1, Question quest2) throws Exception {
		int len1, len2;
		int index1 = getIndexById(quest1.getId());
		int index2 = getIndexById(quest2.getId());
		len1 = answers.get(index1).getAnswerLen();
		len2 = answers.get(index2).getAnswerLen();
		if (len1 < len2)
			return -1;
		else if (len1 == len2)
			return 0;
		else
			return 1;
	}

	private int getIndexById(int id) throws Exception {
		for (int i = 0; i < questions.size(); i++) {
			if (id == questions.get(i).getId())
				return i;
		}
		return -1; // question does not exist
	}

	public int compare(Question quest1, Question quest2) {
		if (quest1.getQuestion().equalsIgnoreCase(quest2.getQuestion())) // check if quests are equal
			return 0;
		else {
			int shortLen;
			if (quest1.getQuestion().length() < quest2.getQuestion().length())
				shortLen = quest1.getQuestion().length();
			else
				shortLen = quest2.getQuestion().length();

			for (int i = 0; i < shortLen; i++) { // check if ques1 is before quest2
				if (quest1.getQuestion().toLowerCase().charAt(i) < quest2.getQuestion().toLowerCase().charAt(i))
					return -1;
				else if (quest1.getQuestion().toLowerCase().charAt(i) > quest2.getQuestion().toLowerCase().charAt(i))
					return 1;
			}
			if (quest1.getQuestion().length() == shortLen) // check if quest1 is substring of quest2
				return -1;

			return 1;
		}
	}

	public void quickSort(boolean flag, int low, int high) throws Exception {
		if (low < high) {

			int pi = partition(flag, low, high);

			quickSort(flag, low, pi - 1);
			quickSort(flag, pi + 1, high);
		}
	}

	public void sortTest(boolean flag) throws Exception { // close questions before open questions
		// flag=true -> sort by len , flag=false -> sort by abc
		// ***************************************
		quickSort(flag, 0, numOfQuestions - 1);
		// ***************************************

	}

	public void setNameOfSubject(String nameOfSubject) {
		this.nameOfSubject = nameOfSubject;
	}

	public int getNumOfQuestions() {
		return questions.size();
	}

	public void setNumOfQuestions(int numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
	}

	public boolean isExistQuestion(Question question) {
		for (int i = 0; i < numOfQuestions; i++) {
			if (((question instanceof CloseQuestion) && (questions.get(i) instanceof CloseQuestion))
					|| ((question instanceof OpenQuestion) && (questions.get(i) instanceof OpenQuestion))) {
				if (question.getQuestion().equalsIgnoreCase(questions.get(i).getQuestion())) {
					return true; // question text is equal and question type is equal
				}
			}
		}
		return false; // question does not exist
	}

	public String presentQuestions() {
		StringBuffer sb = new StringBuffer("Presenting questions:\n");
		for (int i = 0; i < numOfQuestions; i++) {
			sb.append("(" + (i + 1) + ")" + questions.get(i) + "\n");
		}
		return sb.toString();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Presenting test\n");
		sb.append("Subject: " + nameOfSubject + " , num of questions: " + this.questions.size() + "\n\n");
		for (int i = 0; i < questions.size(); i++)
			sb.append(questions.get(i) + "\n");
		for (int i = 0; i < answers.size(); i++)
			sb.append(answers.get(i) + "\n\n");
		sb.append(allClosedAnswers);
		return sb.toString();
	}

	public String presentAnswers() {
		StringBuffer sb = new StringBuffer("Presenting answers:\n");
		for (int i = 0; i < numOfQuestions; i++) {
			sb.append("(" + (i + 1) + ")" + answers.get(i) + "\n");
		}

		return sb.toString();
	}

	public boolean deleteAnswer(int id) {
		answers.set(id - 1, null);
		return true;
	}

	public boolean editAnswer(Answer answer, int choiceAnswer) {
		answers.set(choiceAnswer - 1, answer);
		return true;
	}

	public void saveTestSolutionToTextFile(PrintWriter pw) {/// save Solution to text file

		for (int i = 0; i < numOfQuestions; i++) {

			pw.print("(" + (i + 1) + ")" + answers.get(i) + "\n");
		}

	}

	public void saveTestQuestionsToTextFile(PrintWriter pw) {/// save Questions to text file

		for (int i = 0; i < numOfQuestions; i++) {

			pw.print("(" + (i + 1) + ")" + questions.get(i) + "\n");
		}

	}

	public void saveTestToBinaryFile() throws FileNotFoundException, IOException {

		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Questions.Binary"));/// save Questions
		/// to Binary
		/// file
		outFile.writeObject(this.questions);
		outFile.close();
		ObjectOutputStream outFile1 = new ObjectOutputStream(new FileOutputStream("Solution.Binary"));/// save Questions
		/// to Binary
		/// file
		outFile1.writeObject(this.answers);
		outFile1.close();

	}

	public String getSubject() {
		return this.nameOfSubject;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
}
