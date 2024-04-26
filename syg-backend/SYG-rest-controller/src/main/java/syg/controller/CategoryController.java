package syg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import syg.domain.exception.NotFoundException;
import syg.domain.model.Category;
import syg.domain.ports.inbound.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
    @GetMapping
    public ResponseEntity<Object> findAll() {
    	List<Category> categories = categoryService.findAll();
    	return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
    
    @GetMapping("/id")
    public ResponseEntity<Object> findById(@RequestParam(name = "id") Long id) {
    	try {
    		Category category = categoryService.findById(id);
    		return ResponseEntity.status(HttpStatus.OK).body(category);			
		}catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		} 
    	catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());	
		}
    }
}
