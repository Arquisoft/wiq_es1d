package syg.domain.ports.inbound;

import java.util.List;

import syg.domain.model.Category;

public interface CategoryService {

	/**
	 * Método para encontrar todas las categorias guardadas.
	 * 
	 * @return List<Category>
	 */
	public List<Category> findAll();
	
	/**
	 * Método para encontrar una categoría por su identifcador unico.
	 * 
	 * @return Category
	 */
	public Category findById(Long id);
}
