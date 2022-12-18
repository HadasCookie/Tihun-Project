package id322006032_id318392768;

import java.io.Serializable;

public abstract class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3755237976970799181L;
	private String question;
	public static int id;
	private int qId;

	public TypeOfQuestion getQuestionType() {
		return questionType;
	}

	public enum TypeOfQuestion {
		openQuestion, closeQuestion
	};

	protected TypeOfQuestion questionType;

	public Question(String question) {
		this.question = question;
		this.qId = ++id;
	}

	public int getId() {
		return this.qId;
	}

	public String getQuestion() {
		return question;
	}

	public String toString() {
		return "id:" + this.qId + ", type: " + (this.questionType == TypeOfQuestion.openQuestion ? "open" : "close")
				+ ", " + "question: " + question;
	}

	public void setQuestion(String question) {
		this.question = question;

	}
}
