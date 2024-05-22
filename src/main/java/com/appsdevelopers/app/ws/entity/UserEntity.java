package com.appsdevelopers.app.ws.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name="users")
@NoArgsConstructor
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 9218902060648855794L;
	
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false,length=50)
	private String firstName;
	
	@Column(nullable=false,length=50)
	private String lastName;
	
	@Column(nullable=false,length=120) 
	private String email;
	
	@Column(nullable=false,unique=true,length=10,name="mobile_no")
	private String mobileNo;
	
	@OneToMany(mappedBy="userDetails",cascade=CascadeType.ALL)
	private List<BookEntity> books;
	
}
