package syg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import syg.domain.exception.ConflictException;
import syg.domain.exception.NotFoundException;
import syg.domain.model.User;
import syg.domain.ports.inbound.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
    @GetMapping
    public ResponseEntity<Object> findUsers() {
    	try {
    		List<User> users = userService.findAll();
    		return ResponseEntity.status(HttpStatus.OK).body(users);			
    	}catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		} 
    	catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());	
		}
    }
    
    @GetMapping("/userId")
    public ResponseEntity<Object> findUser(@RequestParam(name = "id") String id) {
    	try {
    		User user = userService.findById(id);
    		return ResponseEntity.status(HttpStatus.OK).body(user);			
    	}catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		} 
    	catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());	
		}
    }
    
    @GetMapping("/name")
    public ResponseEntity<Object> findName(@RequestParam(name = "userName") String userName) {
    	try {
    		User user = userService.findByName(userName);
    		return ResponseEntity.status(HttpStatus.OK).body(user);			
    	}catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		} 
    	catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());	
		}
    }
    
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
    	try {
    		User userCreated = userService.createUser(user);
    		return ResponseEntity.status(HttpStatus.OK).body(userCreated);			
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());	
		}
    }
    
    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
    	try {
    		User userUpdated = userService.updateUser(user);
    		return ResponseEntity.status(HttpStatus.OK).body(userUpdated);			
    	} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());	
		}
    }
}
