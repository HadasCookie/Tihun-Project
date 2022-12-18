package id322006032_id318392768;

import java.io.Serializable;

public class OpenAnswer extends Answer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3325848873059580970L;
	private String answer;

	public OpenAnswer(String answer, int questionId) {
		super(questionId);
		this.answer = answer;
		this.answerType = TypeOfAnswer.openAnswer;
	}

	public int getAnswerLen() {
		return answer.length();
	}

	public String getAnswer() {
		return answer;
	}

	public String toString() {
		return "Answer- " + answer + "\n";
	}

}
