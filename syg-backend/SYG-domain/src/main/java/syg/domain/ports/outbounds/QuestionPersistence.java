package syg.domain.ports.outbounds;

import java.util.List;

import syg.domain.model.Question;

public interface QuestionPersistence {

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
	public void generatedQuestions();
	
	/**
	 * Método encargado de borrar todas las preguntas de la base de datos
	 * 
	 */
	public void deleteQuestions();

}
