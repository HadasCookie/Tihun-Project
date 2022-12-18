package id322006032_id318392768;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import id322006032_id318392768.Question.TypeOfQuestion;

public interface ViewListener {

	boolean anyTestToShow();
	boolean verify(String question, TypeOfQuestion type,String answer);

	void addClosedQ(String question, ArrayList<String> answers, ArrayList<Boolean> corrects);
	void showAll();
	ArrayList<Question> getQToView();
	void changeQuContext(String question,String value);
	ArrayList<CloseAnswer> getClosedAToView();
	ArrayList<OpenAnswer> getOpenAToView();
	void changeOpenAnsContext(String value, String context);
	Boolean changeClosedAnsContext(String answer, String value, Boolean ans);
	void delOpenAns(String value);
	void delClosedAns(String value);
	void addOpenQuToTest(String value);
	void initTest(int amount);
	void addClosedQuToTest(String question, ArrayList<String> answer);
	void showTest();
	void createRandomTest(int num_amount) throws Exception;
	void saveManTest() throws FileNotFoundException;
	int maxQuestions();
	ArrayList<String> testSubjects();
	int getTests();
	void copyTest(String subject);
	void compareQuestions(String question1, String question2);
	void saveToFileFromView() throws FileNotFoundException, IOException;

}
