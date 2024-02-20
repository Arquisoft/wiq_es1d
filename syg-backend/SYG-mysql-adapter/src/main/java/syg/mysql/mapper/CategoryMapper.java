package syg.mysql.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import syg.domain.model.Category;
import syg.mysql.entities.CategoryEntity;

@Component
public class CategoryMapper {
	
	@Autowired
	private QuestionMapper questionMapper;
	
	public CategoryEntity toEntity(Category category) {
		return new CategoryEntity(category.getId(), category.getName(), questionMapper.toEntity(category.getQuestions()));
	}
	
	public List<CategoryEntity> toEntity(List<Category> categories) {
		List<CategoryEntity> categoriesEntity = new ArrayList<CategoryEntity>();
		for (Category category : categories) {
			categoriesEntity.add(toEntity(category));
		}
		return categoriesEntity;
	}
	
	public Category toDomain(CategoryEntity categoryEntity) {
		return new Category(categoryEntity.getId(), categoryEntity.getName(), questionMapper.toDomain(categoryEntity.getQuestions()));
	}
	
	public List<Category> toDomain(List<CategoryEntity> categoriesEntity) {
		List<Category> categories = new ArrayList<Category>();
		for (CategoryEntity categoryEntity : categoriesEntity) {
			categories.add(toDomain(categoryEntity));
		}
		return categories;
	}
}
