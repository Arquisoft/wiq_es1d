package syg.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

	private Long id;
	
	private String name;

	private List<Question> questions;
	
}
