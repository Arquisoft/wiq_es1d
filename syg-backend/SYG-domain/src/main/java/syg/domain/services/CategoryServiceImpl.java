package syg.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import syg.domain.model.Category;
import syg.domain.ports.inbound.CategoryService;
import syg.domain.ports.outbounds.CategoryPersistence;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryPersistence categoryPersistence;
	
	@Override
	public List<Category> findAll() {
		return categoryPersistence.findAll();
	}

	@Override
	public Category findById(Long id) {
		return categoryPersistence.findById(id);
	}

}
