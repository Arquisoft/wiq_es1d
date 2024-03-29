package syg.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

	private Long id;
	
	private String text;

	private Boolean isCorrect;
	
	public Answer(String text, Boolean isCorrect) {
		this.text = text;
		this.isCorrect = isCorrect;
	}
	
}
