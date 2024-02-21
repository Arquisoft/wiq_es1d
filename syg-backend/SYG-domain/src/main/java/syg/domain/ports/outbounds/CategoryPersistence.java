package syg.domain.ports.outbounds;

import java.util.List;

import syg.domain.model.Category;

public interface CategoryPersistence {

	public List<Category> findAll();
	
	public Category findById(Long id);
}
