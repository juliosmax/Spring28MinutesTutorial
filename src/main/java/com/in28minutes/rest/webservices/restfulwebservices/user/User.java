package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.in28minutes.rest.webservices.restfulwebservices.user.Post;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="El tamaño debe ser mayor a 2 caracteres")
	private String name;

	@Past
	private Date birthDate;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Post> posts;
	
	public User(Integer id, String name, Date birthDate /*, List<Post> posts*/) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
//		this.posts = posts;
	}
	

//	public List<Post> getPosts() {
//		return posts;
//	}
//
//
//	public void setPosts(List<Post> posts) {
//		this.posts = posts;
//	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}


	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
