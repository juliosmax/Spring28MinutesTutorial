package com.in28minutes.rest.webservices.restfulwebservices.post;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

//This post was my homework, later the teacher Mahendra will implement another post in the curse
@Data
public class Post {
	
	private Integer idPost;
	private String content;
	private Date dateCreated;

}
