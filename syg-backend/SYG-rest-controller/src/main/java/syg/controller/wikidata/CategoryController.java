package syg.controller.wikidata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import syg.domain.model.Category;
import syg.domain.ports.inbound.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
    	List<Category> categories = categoryService.findAll();
    	return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
    
    @GetMapping("/id")
    public ResponseEntity<Category> findById(@RequestParam(name = "id") Long id) {
    	Category category = categoryService.findById(id);
    	return ResponseEntity.status(HttpStatus.OK).body(category);
    }
}
