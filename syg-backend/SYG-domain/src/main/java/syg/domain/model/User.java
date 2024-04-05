package syg.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private String id;
	
	@NotNull
	private String name;

	@NotNull
	private int totalGames;

	@NotNull
	private int correctAnswers;
	
	@NotNull
	private int inCorrectAnswers;
	
	@NotNull
	private int totalQuestionAnswered;
	
	@NotNull
	private String lastCategoryPlayed;
}
