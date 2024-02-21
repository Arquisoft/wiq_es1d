package syg.domain.ports.inbound;

import java.util.List;

import syg.domain.model.Category;

public interface CategoryService {

	public List<Category> findAll();
	
	public Category findById(Long id);
}
