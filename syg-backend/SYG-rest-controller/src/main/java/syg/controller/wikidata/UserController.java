package syg.controller.wikidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import syg.domain.model.User;
import syg.domain.ports.inbound.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
    @GetMapping
    public ResponseEntity<User> findUser(Long id) {
    	User user = userService.findUser(id);
    	return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(User user) {
    	User userCreated = userService.createUser(user);
    	return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
    
    @PutMapping
    public ResponseEntity<User> updateUser(User user) {
    	User userUpdated = userService.updateUser(user);
    	return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }
}
