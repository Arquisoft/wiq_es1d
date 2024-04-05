package syg.mysql.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "TOTAL_GAMES")
	private int totalGames;

	@Column(name = "CORRECT_ANSWERS")
	private int correctAnswers;
	
	@Column(name = "INCORRECT_ANSWERS")
	private int inCorrectAnswers;
	
	@Column(name = "TOTAL_QUESTIONS_ANSWERED")
	private int totalQuestionAnswered;
	
	@Column(name = "LAST_CATEGORY_PLAYED")
	private String lastCategoryPlayed;
}
