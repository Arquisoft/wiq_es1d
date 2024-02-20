package syg.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {

	private Long id;
	
	private String text;

	private int timeLimit;

	private Category category;

	private List<Answer> answers;
	
	public Question(String text) {
		this.text = text;
		this.timeLimit = 60;
	}
	
}
