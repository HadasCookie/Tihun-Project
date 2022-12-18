package id322006032_id318392768;

import java.io.Serializable;
import java.util.ArrayList;

public class CloseAnswer extends Answer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1923417255534806594L;
	private ArrayList<String> answers;
	private ArrayList<Boolean> isCorrect;

	private int numAnswers;

	public CloseAnswer(ArrayList<String> answers, ArrayList<Boolean> isCorrect, int questionId) {
		super(questionId);
		this.answerType = TypeOfAnswer.closeAnswer;
		this.answers = answers;
		this.isCorrect = isCorrect;
		this.numAnswers = answers.size();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Choose an answer:\n");
		for (int i = 0; i < this.answers.size(); i++) {
			if (answers.get(i) != null)
				sb.append((i + 1) + ") " + answers.get(i) + " - " + isCorrect.get(i) + "\n");
		}
		return sb.toString();
	}

	public void setCorrectAnswer(int correctAnswer) {
		isCorrect.set(correctAnswer - 1, true);
	}

	public boolean equals(CloseAnswer answer) {
		if (answers.size() != answer.getAnswers().size())
			return false; // different number of answers
		for (int i = 0; i < answers.size(); i++) {
			if (!answer.hasAnswer(answers.get(i)))
				return false;
		}
		if (answer.getCorrectAnswerIndex() != this.getCorrectAnswerIndex())
			return false;
		return true;
	}

	private boolean hasAnswer(String ansString) {
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).equalsIgnoreCase(ansString))
				return true;
		}
		return false;
	}

	public boolean addDefaults() {
		if (this.numAnswers == Test.MAX_NUM_ANSWERS) {
			answers.set(Test.MAX_NUM_ANSWERS - 2, "none of the answers are correct");
			answers.set(Test.MAX_NUM_ANSWERS - 1, "more than one answer is correct");
			this.isCorrect.set(Test.MAX_NUM_ANSWERS - 2, false);
			this.isCorrect.set(Test.MAX_NUM_ANSWERS - 1, false);
		} else {

			if (numAnswers == Test.MAX_NUM_ANSWERS - 1) {
				this.answers.set(Test.MAX_NUM_ANSWERS - 2, "none of the answers are correct");
				this.isCorrect.set(Test.MAX_NUM_ANSWERS - 2, false);
			} else {
				this.answers.add("none of the answers are correct");
				this.isCorrect.add(false);
				numAnswers++;

			}
			this.answers.add("more than one answer is correct");
			this.isCorrect.add(false);
			numAnswers++;
		}
		return true;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public ArrayList<Boolean> getIsCorrect() {
		return isCorrect;
	}

	public int getNumAnswers() {
		return numAnswers;
	}

	public int getAnswerLen() {
		int count = 0;
		for (int i = 0; i < answers.size(); i++) {
			count += answers.get(i).length();
		}
		return count;
	}

	public int getCorrectAnswerIndex() {
		for (int i = 0; i < isCorrect.size(); i++) {
			if (isCorrect.get(i))
				return i;
		}
		return -1; // all answers are wrong
	}
}
