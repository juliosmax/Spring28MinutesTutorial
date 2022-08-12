package com.in28minutes.rest.webservices.restfulwebservices.post;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class Post {
	
	private Integer idPost;
	private String content;
	private Date dateCreated;

}
