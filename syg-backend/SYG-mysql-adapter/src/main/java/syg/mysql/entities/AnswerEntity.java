package syg.mysql.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ANSWERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TEXT")
	private String text;

	@Column(name = "IS_CORRECT")
	private Boolean isCorrect;

	@ManyToOne
	@JoinColumn(name = "question", referencedColumnName = "ID")
	private QuestionEntity question;

	public AnswerEntity(Long id, String text, Boolean isCorrect) {
		this.id = id;
		this.text = text;
		this.isCorrect = isCorrect;
	}
}
