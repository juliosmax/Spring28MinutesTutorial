package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.user.Post;
import com.in28minutes.rest.webservices.restfulwebservices.repository.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.repository.UserRepository;

@RestController
public class UserJpaResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {
			throw new UserNotFoundException("No users found");
		}
		return users;
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> findUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id -"+id);
		}
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		
	}
	
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User userSaved = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(userSaved.getId()).toUri();
		
		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("jpa/users/{id}/posts")
	public List<Post> retrieveUsersPosts(@PathVariable int id)
	{
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id -"+id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> addUserPost(@PathVariable int id,@RequestBody Post post)
	{
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id -"+id);
		}
		
		post.setUser(user.get());
		
	    postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();

		
	}

	
	
//	
//	@PostMapping("/users/{id}/posts")
//	public ResponseEntity<Object> postUsersPosts(@PathVariable int id,@RequestBody Post post)
//	{
//		Post posts = userDaoService.createUserPosts(id,post);
//		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(post.getIdPost()).toUri();
//		
//		return ResponseEntity.created(location).build();
//
//	}
//	
//	@GetMapping("/users/{id}/posts/{idPost}")
//	public Post retrieveUsersEspecificPosts(@PathVariable int id, @PathVariable int idPost)
//	{
//		Post post = userDaoService.getUserEspecificPost(id, idPost);
//		if(post == null) {
//			throw new PostNotFoundException("No post has been found for this user");
//		}
//		return post;
//	}
	
	
	
	

}
