package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.post.Post;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	{
		List<User> users = userDaoService.findAll();
		if(users.isEmpty()) {
			throw new UserNotFoundException("No users found");
		}
		return users;
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> findUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id -"+id);
		}
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteUser(id);
		if(user == null) {
			throw new UserNotFoundException("id -"+id);
		}
		
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User userSaved = userDaoService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(userSaved.getId()).toUri();
		
		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveUsersPosts(@PathVariable int id)
	{
		List<Post> posts = userDaoService.getUserPosts(id);
		if(posts == null || posts.isEmpty()) {
			throw new PostNotFoundException("No post found");
		}
		return posts;
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> postUsersPosts(@PathVariable int id,@RequestBody Post post)
	{
		Post posts = userDaoService.createUserPosts(id,post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getIdPost()).toUri();
		
		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/users/{id}/posts/{idPost}")
	public Post retrieveUsersEspecificPosts(@PathVariable int id, @PathVariable int idPost)
	{
		Post post = userDaoService.getUserEspecificPost(id, idPost);
		if(post == null) {
			throw new PostNotFoundException("No post has been found for this user");
		}
		return post;
	}
	
	
	
	

}
