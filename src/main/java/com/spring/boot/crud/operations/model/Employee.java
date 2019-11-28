package com.spring.boot.crud.operations.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@ToString @EqualsAndHashCode
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Employee {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) long id;
	@Column(name = "first_name", nullable = false)
	String firstName;
	@Column(name = "last_name", nullable = false)
	String lastName;
	@Column(name = "email_address", nullable = false)
	String emailId;

	
}
