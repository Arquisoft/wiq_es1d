package syg.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Question {

	private String text;

	private int timeLimit;

	private Category category;

	private List<Answer> answers;
	
	public Question(String text) {
		this.text = text;
		this.timeLimit = 60;
	}
	
}
