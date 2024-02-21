package syg.mysql.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "QUESTIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TEXT")
	private String text;

	@Column(name = "TIME_LIMIT")
	private Integer timeLimit;

	@ManyToOne
	@JoinColumn(name = "category", referencedColumnName = "ID")
	private CategoryEntity category;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<AnswerEntity> answers;
	
	public QuestionEntity(Long id, String text, int timeLimit, CategoryEntity category) {
		this.id = id;
		this.text = text;
		this.timeLimit = timeLimit;
		this.category = category;
	}

}
