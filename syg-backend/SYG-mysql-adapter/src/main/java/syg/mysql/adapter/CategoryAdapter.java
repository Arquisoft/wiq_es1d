package syg.mysql.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import syg.domain.exception.NotFoundException;
import syg.domain.model.Category;
import syg.domain.ports.outbounds.CategoryPersistence;
import syg.mysql.entities.CategoryEntity;
import syg.mysql.mapper.CategoryMapper;
import syg.mysql.repositories.CategoryRepository;

@Component
public class CategoryAdapter implements CategoryPersistence {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Category> findAll() {
		return categoryMapper.toDomain(categoryRepository.findAll());
	}

	@Override
	public Category findById(Long id) {
		Optional<CategoryEntity> optionalCategory =  categoryRepository.findById(id);
		if(!optionalCategory.isPresent()) {
			throw new NotFoundException("The category with id " + id + " does not exist");
		}
		return categoryMapper.toDomain(categoryRepository.findById(id).get());
	}

}
