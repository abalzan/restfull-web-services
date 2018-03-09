package com.andrei.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description="all necessary details about user entity")
//@JsonFilter("PasswordFilter") to implement Dynamic Filter it impose to use it(PasswordFilter) in every method that use this class.
@Data
@Entity
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(notes="Name should have at least 2 characters!!!")
	@Size(min=2, message="Name should have at least 2 characters!!!")
	private String name;
	
	@ApiModelProperty(notes="Birth Date need to be in the past!!!")
	@Past
	private LocalDate birthDate;
	
	@JsonIgnore //You can use static filters like that to ignore this field at Rest request
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Post> posts;
	
	public User(String name, LocalDate birthDate, String password) {
		this.name = name;
		this.birthDate = birthDate;
		this.password = password;
	}

	public User(String name, LocalDate birthDate, String password, List<Post> posts) {
		this.name = name;
		this.birthDate = birthDate;
		this.password = password;
		this.posts = posts;
	}
	
}
