package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.rest.webservices.restfulwebservices.post.Post;

@Component
public class UserDaoService {
		private static List<User> users = new ArrayList<>();
		
		private static int userCount=3;
		private static int postCount=0;
		
		static {
			users.add(new User(1, "Adam", new Date()));
			users.add(new User(2, "Evelin", new Date()));
			users.add(new User(3, "Jack", new Date())); 
			
		}
		
		public List<User> findAll() {
			return users;
		}
		
		public User save(User user) {
			if(user.getId()== null) {
				user.setId(++userCount);
			}
			users.add(user);
			return user;
		}
		
		public User findOne(int id) {
			for(User user: users) {
				if(user.getId() == id) {
					return user;
				}
			}
			return null;
		}
		
//		public List<Post> getUserPosts(int idUser){
//		   User user = users.get(idUser);
//		   return user.getPosts();
//		}

//		public Post createUserPosts(int id, Post post) {
//			// TODO Auto-generated method stub
//			if(post.getIdPost() == null) {
//				post.setIdPost(++postCount);
//			}
//			post.setDateCreated(new Date());
//			User user = users.get(id);
//			user.getPosts().add(post);
//			return post;
//		}
		
//		public Post getUserEspecificPost(int idUser, int idPost){
//			if(idUser<users.size()) {
//			   User user = users.get(idUser);
//			   List<Post> posts = user.getPosts();
//			   for (Post post : posts) {
//				if(post.getIdPost() == idPost) {
//					return post;
//				}
//			}
//			}
//			   return null;
//			}
		
		public User deleteUser(int id) {
			Iterator<User> iterator = users.iterator();
			while(iterator.hasNext()) {
				User user = iterator.next();
				if(user.getId() == id) {
					iterator.remove();
					return user;
				}
			}
			return null;
		}
		


}
