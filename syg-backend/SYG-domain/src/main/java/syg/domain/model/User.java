package syg.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private Long id;
	
	@NotNull
	private String name;

	@NotNull
	private String password;

	@NotNull
	private int totalGames;

	@NotNull
	private int correctAnswers;
	
	@NotNull
	private int inCorrectAnswers;

}
