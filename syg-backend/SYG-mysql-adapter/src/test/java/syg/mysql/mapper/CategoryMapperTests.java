package syg.mysql.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import syg.domain.model.Category;
import syg.mysql.configuration.UnitAdapterTest;
import syg.mysql.entities.CategoryEntity;

@UnitAdapterTest
public class CategoryMapperTests {
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Test
	@DisplayName("Se mapea un objeto categoria de dominio a entidad")
	void model_to_entity() {
		CategoryEntity categoryEntity = categoryMapper.toEntity(new Category(1L, "Animales"));
		
		assertEquals(1L, categoryEntity.getId());
		assertEquals("Animales", categoryEntity.getName());
	}
	
	@Test
	@DisplayName("Se mapea una lista de objetos categoria de dominio a entidad")
	void model_to_entity_list() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1L, "Categoria 1"));
		categories.add(new Category(2L, "Categoria 2"));
		categories.add(new Category(3L, "Categoria 3"));
		
		List<CategoryEntity> categoriesEntity = categoryMapper.toEntity(categories);
		
		assertEquals(3, categoriesEntity.size());
		assertEquals(1L, categoriesEntity.get(0).getId());
		assertEquals("Categoria 1", categoriesEntity.get(0).getName());
	}
	
	@Test
	@DisplayName("Se mapea un objeto categoria de entidad a dominio")
	void entity_to_model() {
		Category category = categoryMapper.toDomain(new CategoryEntity(1L, "Categoria 1"));
		
		assertEquals(1L, category.getId());
		assertEquals("Categoria 1", category.getName());
	}
	
	@Test
	@DisplayName("Se mapea una lista de objetos categoria de entidad a dominio")
	void entity_to_model_list() {
		List<CategoryEntity> categoriesEntity = new ArrayList<CategoryEntity>();
		categoriesEntity.add(new CategoryEntity(1L, "Categoria 1"));
		categoriesEntity.add(new CategoryEntity(2L, "Categoria 2"));
		categoriesEntity.add(new CategoryEntity(3L, "Categoria 3"));
		
		List<Category> category = categoryMapper.toDomain(categoriesEntity);
		
		assertEquals(3, category.size());
		assertEquals(1L, category.get(0).getId());
		assertEquals("Categoria 1", category.get(0).getName());
	}
}
