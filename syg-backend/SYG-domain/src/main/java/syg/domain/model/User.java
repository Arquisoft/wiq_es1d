package syg.domain.model;

import lombok.Data;

@Data
public class User {
	
	private Long id;
	
	private String name;

	private String password;

	private int totalGames;

	private int correctAnswers;
	
	private int inCorrectAnswers;

}
