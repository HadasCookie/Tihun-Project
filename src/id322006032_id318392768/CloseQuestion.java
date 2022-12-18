package id322006032_id318392768;

public class CloseQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3569501285645980163L;
	public final static int maxAnswers = 10;

	public CloseQuestion(String question) {
		super(question);
		this.questionType = TypeOfQuestion.closeQuestion;

	}
}
