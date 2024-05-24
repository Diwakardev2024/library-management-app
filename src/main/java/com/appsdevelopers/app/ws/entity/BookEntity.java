package com.appsdevelopers.app.ws.entity;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="BooksList")
@NoArgsConstructor
public class BookEntity implements Serializable {
	
	private static final long serialVersionUID = 3078518063582992060L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=30,nullable=false)
	private String bookId;
	
	@Column(length=30,nullable=false,unique=true)
	private String title;
	
	@Column(length=70,nullable=false)
	private String Author;
	
	@Column(length=15,nullable=false)
	private String ISBN;
	
	@Column(length=10,nullable=false)
	private Float price;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity userDetails;

}
