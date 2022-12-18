package id322006032_id318392768;

import java.io.Serializable;
import java.util.ArrayList;

public class Set<T extends CloseAnswer> implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CloseAnswer> answers;

	public Set() {
		this.answers = new ArrayList<CloseAnswer>();
	}

	public boolean add(Object answer) throws Exception {
		if (!((answer instanceof CloseAnswer))) // tests of input
			return false;

		for (int i = 0; i < this.answers.size(); i++) { // test if one of answers already exist
			if (this.answers.get(i).equals(answer))
				return false; // answer already exists in set
		}

		this.answers.add((CloseAnswer) answer);
		return true;
	}

	public boolean remove(int index) throws Exception {
		if (index < 0 || index >= answers.size())
			return false;
		answers.remove(index);
		return true;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Presenting closed answers:\n");
		for (int i = 0; i < answers.size(); i++) {
			sb.append("(" + (i + 1) + ")" + answers.get(i) + "\n");
		}

		return sb.toString();
	}

}
