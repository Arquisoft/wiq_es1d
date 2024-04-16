package syg.domain.ports.inbound;

import java.util.List;

import syg.domain.model.Question;

public interface QuestionService {
	
	/**
	 * Método para encontrar todas las preguntas guardadas.
	 * 
	 * @return List<Question>
	 */
	public List<Question> findAll();
	
	/**
	 * Método para encontrar una pregunta por su identificador unico.
	 * 
	 * @return Question
	 */
	public Question findById(Long id);
	
	/**
	 * Método para encontrar una o varias preguntas por su categoria.
	 * 
	 * @return List<Question>
	 */
	public List<Question> findByCategory(Long categoryId);

	/**
	 * Método encargado de generar las preguntas con sus respuestas a traves de
	 * wikidata
	 * 
	 */
	public void generateQuestions();
	
	/**
	 * Método encargado de borrar todas las preguntas de la base de datos
	 * 
	 */
	public void deleteQuestions();

}
