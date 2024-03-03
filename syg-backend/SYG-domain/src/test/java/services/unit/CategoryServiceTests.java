package services.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import configuration.UnitDomainTest;
import syg.domain.exception.NotFoundException;
import syg.domain.model.Category;
import syg.domain.ports.inbound.CategoryService;
import syg.domain.ports.outbounds.CategoryPersistence;

@UnitDomainTest
public class CategoryServiceTests {

	@Autowired
	private CategoryService categoryService;
	
	@MockBean
	private CategoryPersistence categoryPersistence;
	
	@Test
	@DisplayName("Se buscan todas las categorias")
	void find_all_categories() {
		List<Category> categoriesResponse = new ArrayList<Category>();
		categoriesResponse.add(new Category(1L, "animales"));
		categoriesResponse.add(new Category(2L, "deportes"));
		
		when(categoryPersistence.findAll()).thenReturn(categoriesResponse);
		
		List<Category> categories = categoryService.findAll();
		assertEquals(2, IterableUtil.sizeOf(categories));
		assertEquals(2, categories.get(1).getId());
		assertEquals("deportes", categories.get(1).getName());
	}
	
	@Test
	@DisplayName("Se busca una categoria a traves de un id en base de datos")
	void find_category_by_id() {
		Category CategoryResponse = new Category(4L, "plantas");
		
		when(categoryPersistence.findById(4L)).thenReturn(CategoryResponse);
		
		Category category = categoryService.findById(4L);
		assertEquals(4, category.getId());
		assertEquals("plantas", category.getName());
	}
	
	@Test
	@DisplayName("Se busca una categoria a traves de un id que no existe")
	void find_category_by_not_exist_id() {
		final Executable exec;
		
		when(categoryPersistence.findById(50L)).thenThrow(NotFoundException.class);
		
		exec = () -> categoryService.findById(50L);
		assertThrows(NotFoundException.class, exec);
	}
}
