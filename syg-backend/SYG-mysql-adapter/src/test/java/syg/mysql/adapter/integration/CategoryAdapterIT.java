package syg.mysql.adapter.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import syg.domain.exception.NotFoundException;
import syg.domain.model.Category;
import syg.mysql.SYGdbContainerIT;
import syg.mysql.adapter.CategoryAdapter;
import syg.mysql.configuration.IntegrationAdapterTest;

@IntegrationAdapterTest
public class CategoryAdapterIT extends SYGdbContainerIT {

	@Autowired
	private CategoryAdapter categoryAdapter;
	
	@Test
	@DisplayName("Se buscan todas las categorias en base de datos")
	void find_all_categories() {
		List<Category> categories = categoryAdapter.findAll();
		assertEquals(5, IterableUtil.sizeOf(categories));
		assertEquals(3, categories.get(2).getId());
		assertEquals("planta", categories.get(2).getName());
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id en base de datos")
	void find_question_by_id() {
		Category category = categoryAdapter.findById(4L);
		
		assertEquals(4, category.getId());
		assertEquals("deportes", category.getName());
	}
	
	@Test
	@DisplayName("Se busca una categoria a traves de un id que no existe")
	void find_category_by_not_exist_id() {
		final Executable exec;
		
		exec = () -> categoryAdapter.findById(50L);
		
		assertThrows(NotFoundException.class, exec);
	}
}
