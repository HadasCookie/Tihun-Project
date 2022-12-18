package id322006032_id318392768;

import java.util.ArrayList;

public interface ModelListener {

	void dupQu();

	void createOpen(String question, String answer);

	void noQuestions();

	void successAddCloseQ(String question, ArrayList<String> answers, ArrayList<Boolean> corrects);

	void QChanged();

	void answerChangedSuccesfully();

	void alreadyTrue();

	void openAnsRemoveSuccess();

	void closedAnsRemoveSuccess();

	public void compareQu(String shortQu, int shortAns);

}
