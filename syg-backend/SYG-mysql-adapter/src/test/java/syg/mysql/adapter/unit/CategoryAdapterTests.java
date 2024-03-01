package syg.mysql.adapter.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import syg.domain.exception.NotFoundException;
import syg.domain.model.Category;
import syg.mysql.adapter.CategoryAdapter;
import syg.mysql.configuration.UnitTest;
import syg.mysql.entities.CategoryEntity;
import syg.mysql.repositories.CategoryRepository;

@UnitTest
public class CategoryAdapterTests {

	@Autowired
	private CategoryAdapter categoryAdapter;
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	@Test
	@DisplayName("Se buscan todas las categorias en base de datos")
	void find_all_categories() {
		List<CategoryEntity> categoriesEntitiesResponse = new ArrayList<CategoryEntity>();
		categoriesEntitiesResponse.add(new CategoryEntity(1L, "animales"));
		categoriesEntitiesResponse.add(new CategoryEntity(2L, "deportes"));
		
		when(categoryRepository.findAll()).thenReturn(categoriesEntitiesResponse);
		
		List<Category> categories = categoryAdapter.findAll();
		assertEquals(2, IterableUtil.sizeOf(categories));
		assertEquals(2, categories.get(1).getId());
		assertEquals("deportes", categories.get(1).getName());
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id en base de datos")
	void find_question_by_id() {
		Optional<CategoryEntity> categoryEntityResponse = Optional.of(new CategoryEntity(4L, "plantas"));
		
		when(categoryRepository.findById(4L)).thenReturn(categoryEntityResponse);
		
		Category category = categoryAdapter.findById(4L);
		assertEquals(4, category.getId());
		assertEquals("plantas", category.getName());
	}
	
	@Test
	@DisplayName("Se busca una categoria a traves de un id que no existe")
	void find_category_by_not_exist_id() {
		final Executable exec;
		
		when(categoryRepository.findById(50L)).thenThrow(NotFoundException.class);
		
		exec = () -> categoryAdapter.findById(50L);
		assertThrows(NotFoundException.class, exec);
	}
}
