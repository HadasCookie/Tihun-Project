package id322006032_id318392768;

import java.io.Serializable;

public abstract class Answer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8944676687457932991L;
	int questionId;

	public enum TypeOfAnswer {
		openAnswer, closeAnswer
	};

	protected TypeOfAnswer answerType;

	public Answer(int questionId) {
		this.questionId = questionId;
	}

	public Answer(Answer answer) {
		this.questionId = answer.getQuestionId();
		this.answerType = answer.getAnswerType();
	}

	public TypeOfAnswer getAnswerType() {
		return answerType;
	}

	public void setAnswerType(TypeOfAnswer answerType) {
		this.answerType = answerType;
	}

	public int getQuestionId() {
		return questionId;
	}

	public abstract int getAnswerLen();
}