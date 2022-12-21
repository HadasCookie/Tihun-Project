package id322006032_id318392768;

import id322006032_id318392768.Answer.TypeOfAnswer;
import id322006032_id318392768.Question.TypeOfQuestion;

import java.io.*;
import java.util.*;

public class DataBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Question> questions;
	private ArrayList<Answer> answers;
	private ArrayList<ModelListener> modelListeners;
	private HashMap<String, Integer> hashMapQuestions;
	private Map<String, Integer> treeMap;
	private HashSet<String> hashSetList = new HashSet<String>();
	private Iterator iter;

	@SuppressWarnings("unchecked")
	public DataBase() throws IOException, ClassNotFoundException {
		this.questions = new ArrayList<>();
		this.modelListeners = new ArrayList<>();
		this.answers = new ArrayList<>();
		try {
			ObjectInputStream objectInputStreamQuestions = new ObjectInputStream(
					new FileInputStream("Questions.Binary"));
			this.questions = (ArrayList<Question>) objectInputStreamQuestions.readObject();
			objectInputStreamQuestions.close();
			ObjectInputStream objectInputStreamAnswers = new ObjectInputStream(new FileInputStream("Solution.Binary"));
			this.answers = (ArrayList<Answer>) objectInputStreamAnswers.readObject();
			objectInputStreamAnswers.close();
		} catch (FileNotFoundException ex) {
			ArrayList<Question> dataBaseQuestions = new ArrayList<>(
					Arrays.asList(new OpenQuestion("how do you print in java?"),
							new CloseQuestion("which of given languages does not exist?"),
							new OpenQuestion("name 3 programming languages you learned this semester?"),
							new OpenQuestion("which software are you using in Java?"),
							new CloseQuestion("In java we use printf?"),
							new OpenQuestion("which programming language is your favorite?"),
							new CloseQuestion("when did you learn java?"),
							new OpenQuestion("name 4 big companies in the world?"),
							new OpenQuestion("which software are you using in C ?"),
							new CloseQuestion("Do you want to be a programmer in the future? ")));

			ArrayList<String> ans2 = new ArrayList<>(
					Arrays.asList("java", "ruby", "none of answers is correct", "more than one answer is correct"));

			ArrayList<Boolean> ans2Correct = new ArrayList<>(Arrays.asList(false, false, true, false));

			ArrayList<String> ans5 = new ArrayList<>(
					Arrays.asList("yes", "no", "none of answers is correct", "more than one answer is correct"));

			ArrayList<Boolean> ans5Correct = new ArrayList<>(Arrays.asList(false, true, false, false));

			ArrayList<String> ans7 = new ArrayList<>(Arrays.asList("university", "school", "none of answers is correct",
					"more than one answer is correct"));

			ArrayList<Boolean> ans7Correct = new ArrayList<>(Arrays.asList(false, true, false, false));

			ArrayList<String> ans10 = new ArrayList<>(
					Arrays.asList("yes", "no", "none of answers is correct", "more than one answer is correct"));

			ArrayList<Boolean> ans10Correct = new ArrayList<>(Arrays.asList(true, false, false, false));

			ArrayList<Answer> dataBaseAnswers = new ArrayList<>(Arrays.asList(new OpenAnswer("println", 1),
					new CloseAnswer(ans2, ans2Correct, 2), new OpenAnswer("python java js", 3),
					new OpenAnswer("eclipse", 4), new CloseAnswer(ans5, ans5Correct, 5), new OpenAnswer("C++", 6),
					new CloseAnswer(ans7, ans7Correct, 7), new OpenAnswer("Apple Microsoft Amazon Tesla", 8),
					new OpenAnswer("Visual Studio", 9), new CloseAnswer(ans10, ans10Correct, 10)));

			questions = dataBaseQuestions;
			answers = dataBaseAnswers;
			saveDatabaseToBinaryFile();
		}
	}

	public DataBase(ArrayList<Question> questions, ArrayList<Answer> answers) { // if we want to create database from
		// main program
		this.questions = questions;
		this.answers = answers;
	}

	public void addQuestion(Question quest) {

		questions.add(quest);
	}

	public void addAnswer(Answer ans) {

		answers.add(ans);
	}

	public boolean verifyQ(String question, TypeOfQuestion type, String answer) {
		for (int i = 0; i < this.questions.size(); i++) {
			if (this.questions.get(i).getQuestion().equals(question)) {
				if (this.questions.get(i).getQuestionType().equals(type)) {
					for (ModelListener m : modelListeners) {
						m.dupQu();
					}
					return true;
				}
			}

		}
		if (type.equals(TypeOfQuestion.openQuestion)) {
			for (ModelListener m : modelListeners) {
				m.createOpen(question, answer);
			}
		}
		return false;
	}

	public void addClosedQ(String question, ArrayList<String> answers, ArrayList<Boolean> corrects) {
		CloseQuestion cq = new CloseQuestion(question);
		this.questions.add(cq);
		this.answers.add(new CloseAnswer(answers, corrects, cq.getId()));
		for (ModelListener m : modelListeners) {
			m.successAddCloseQ(question, answers, corrects);
		}

	}

	public Question getQuestion(int index) {
		return questions.get(index);
	}

	public int getNumQuestions() {
		return this.questions.size();
	}

	public Answer getAnswer(int index) {
		return answers.get(index);
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public ArrayList<CloseAnswer> getClosedAnswers() {
		ArrayList<CloseAnswer> arr = new ArrayList<>();
		for (Answer answer : answers) {
			if (answer.getAnswerType().equals(TypeOfAnswer.closeAnswer))
				arr.add((CloseAnswer) answer);
		}
		return arr;
	}

	public ArrayList<OpenAnswer> getOpenAnswers() {
		ArrayList<OpenAnswer> arr = new ArrayList<>();
		for (Answer answer : answers) {
			if (answer.getAnswerType().equals(TypeOfAnswer.openAnswer))
				arr.add((OpenAnswer) answer);
		}
		return arr;
	}

	public int getRandomQuestionIndex() {
		return (int) (Math.random() * this.questions.size());
	}

	public void registerListener(Controller controller) {
		this.modelListeners.add(controller);

	}

	public boolean toStringToView() {
		if (this.questions.size() == 0) {
			for (ModelListener m : modelListeners) {
				m.noQuestions();
			}
			return true;
		}
		return false;
	}

	// ******************************************************************************************************************
	public String toString() {
		StringBuffer sb = new StringBuffer("Presenting Questions & Solutions\n\n\n");
		sb.append("Number of questions: " + this.questions.size() + "\n" + "Number of answers: " + this.answers.size()
				+ "\n\n");
		for (int i = 0; i < questions.size(); i++) {
			sb.append(questions.get(i).toString() + "\n");
			for (int j = 0; j < answers.size(); j++) {
				if (this.answers.get(j).getQuestionId() == this.questions.get(i).getId())
					sb.append(answers.get(j).toString() + "\n");
			}
		}
		return sb.toString();
	}
	// ******************************************************************************************************************

	public void changeQContext(String question, String value) {
		int i;
		for (i = 0; i < this.questions.size(); i++) {
			if (this.questions.get(i).getQuestion().equals(question)) {
				if (this.questions.get(i).getQuestionType().equals(TypeOfQuestion.openQuestion))
					this.questions.set(i, new OpenQuestion(value));
				else
					this.questions.set(i, new CloseQuestion(value));
				break;
			}
		}
		for (ModelListener m : modelListeners) {
			m.QChanged();
		}
	}

	public Boolean changeClosedAnsContext(String answer, String value, Boolean ans) {
		int i;
		for (i = 0; i < this.answers.size(); i++) {
			if (this.answers.get(i).getAnswerType().equals(TypeOfAnswer.closeAnswer)) {
				CloseAnswer a = (CloseAnswer) this.answers.get(i);
				if (ans && a.getIsCorrect().contains(ans)) {
					for (ModelListener m : modelListeners)
						m.alreadyTrue();
					return true;
				}
				for (int j = 0; j < a.getAnswers().size(); j++) {
					if (a.getAnswers().get(j).equals(answer)) {
						a.getAnswers().set(j, value);
						a.getIsCorrect().set(j, ans);
						this.answers.set(i, a);
					}
				}
			}
		}
		for (ModelListener m : modelListeners)
			m.answerChangedSuccesfully();
		return false;
	}

	public void changeOpenAnsContext(String value, String context) {
		int i;
		for (i = 0; i < this.answers.size(); i++) {
			if (this.answers.get(i).getAnswerType().equals(TypeOfAnswer.openAnswer)) {
				OpenAnswer a = (OpenAnswer) this.answers.get(i);
				if (a.getAnswer().equals(value))
					this.answers.set(i, new OpenAnswer(context, this.answers.get(i).getQuestionId()));
			}
		}
		for (ModelListener m : modelListeners)
			m.answerChangedSuccesfully();

	}

	public void deleteOpenAns(String value) {
		for (int i = 0; i < this.answers.size(); i++) {
			if (this.answers.get(i).getAnswerType().equals(TypeOfAnswer.openAnswer)) {
				OpenAnswer a = (OpenAnswer) this.answers.get(i);
				if (a.getAnswer().equals(value)) {
					this.answers.remove(i);
					break;
				}
			}

		}
		for (ModelListener m : modelListeners)
			m.openAnsRemoveSuccess();

	}

	public void deleteClosedAns(String value) {
		for (int i = 0; i < this.answers.size(); i++) {
			if (this.answers.get(i).getAnswerType().equals(TypeOfAnswer.closeAnswer)) {
				CloseAnswer a = (CloseAnswer) this.answers.get(i);
				for (int j = 0; j < a.getNumAnswers(); j++) {
					if (a.getAnswers().get(j).toString().equals(value)) {
						System.out.println(a.getAnswers().get(j));
						if (a.getAnswers().size() == 1)
						{
							this.answers.remove(i);

						}
						else
							a.getAnswers().remove(j);
						break;
					}

				}
			}

		}
		for (ModelListener m : modelListeners)
			m.closedAnsRemoveSuccess();
	}

	public Question findQuestion(String value) {
		for (Question question : questions) {
			if (question.getQuestion().equals(value))
				return question;
		}
		return null;
	}

	public Answer findClosedAnswer(ArrayList<String> answer) {
		for (int i = 0; i < this.answers.size(); i++) {
			if (this.answers.get(i).getAnswerType().equals(TypeOfAnswer.closeAnswer)) {
				CloseAnswer a = (CloseAnswer) this.answers.get(i);
				if (a.getAnswers().equals(answer)) {
					return a;
				}
			}
		}
		return null;
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public int getRandomClosedAnswerIndex() {
		int res = -1;
		for (int i = 0; i < this.answers.size(); i++) {
			res = (int) (Math.random() * this.answers.size());
			if (this.answers.get(res) instanceof CloseAnswer)
				break;
		}
		return res;
	}

	public void compareQu(String question1, String question2) {
		String shortQu = "";
		int shortAns = 0;
		Question q1 = null, q2 = null;
		Answer a1 = null, a2 = null;
		for (Question q : this.questions) {
			if (q.getQuestion().equals(question1))
				q1 = q;
			else if (q.getQuestion().equals(question2))
				q2 = q;
			else
				continue;
		}
		for (Answer a : this.answers) {
			if (a.getQuestionId() == q1.getId())
				a1 = a;
			else if (a.getQuestionId() == q2.getId())
				a2 = a;
			else
				continue;
		}
		if (a1.getAnswerLen() > a2.getAnswerLen()) {
			shortAns = a2.getAnswerLen();
			shortQu = q2.getQuestion();
		} else {
			shortAns = a1.getAnswerLen();
			shortQu = q1.getQuestion();
		}
		for (ModelListener m : modelListeners)
			m.compareQu(shortQu, shortAns);

	}

	public void saveDatabaseToBinaryFile() throws FileNotFoundException, IOException {

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
	public void copyArrayToANewCollectionAndSortWithDupes() {
		for (int i = 0; i < getNumQuestions(); i++) {
			hashMapQuestions.put(questions.get(i).getQuestion(), i);
		}
		treeMap = new TreeMap<String, Integer>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() > o2.length()) {
					return -1;
				} else if (o1.length() < o2.length()) {
					return 1;
				} else if (o1.length() == o2.length()){
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		treeMap.putAll(hashMapQuestions);
		printTreeMapCollection((TreeMap<String, Integer>) treeMap);
	}

	public void printTreeMapCollection(TreeMap<String, Integer> treeMap) {
		System.out.println("This is the sorted TreeMap collection: \n");
		Iterator<Map.Entry<String, Integer>> entryIt = treeMap.entrySet().iterator();
		while (entryIt.hasNext()) {
			System.out.println(entryIt.next());
		}
	}

	public HashSet<String> copyToHashSet(HashMap<String, Integer> hashMapSortedList, HashSet<String> hashSet) {
		for (Map.Entry<String, Integer> entry : hashMapSortedList.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			hashSet.add(key);
		}
		return hashSet;
	}
	public void addNewStringToHashSet(String theStr) {
		iter = hashSetList.iterator();
		ComparatorStr sComp = new ComparatorStr();
		while (iter.hasNext()) {
			int boolVal = sComp.compare((String) iter.next(), theStr);
			if (boolVal == 0) {
				System.out.println("Question already exists in the 'HashSet'.");
				break;
			}
		}
		hashSetList.add(theStr);
	}
	public void copyArrayToANewCollectionAndSortNoDupes() {
		hashSetList = copyToHashSet(hashMapQuestions, hashSetList);
		printHashSet();
	}

	public void printHashSet() {
		iter = hashSetList.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
