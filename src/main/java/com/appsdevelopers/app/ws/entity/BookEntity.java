package com.appsdevelopers.app.ws.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name="Book")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BookEntity implements Serializable {
	
	private static final long serialVersionUID = 3078518063582992060L;
	
	@Id
	@Column(length=30,nullable=false)
	private String bookId;
	
	@Column(length=30,nullable=false)
	private String title;
	
	@Column(length=70,nullable=false)
	private String Author;
	
	@Column(length=15,nullable=false,unique=true)
	private String ISBN;
	
	@Column(length=10,nullable=false)
	private Float price;
	
	@Column(length=10,nullable=false)
	private long count=1;
	
	@Column(updatable = false)
	@CreatedDate
	private Instant createdOn;
	@LastModifiedDate 
	private Instant lastUpdatedOn;
	
//	@OneToMany(mappedBy="bookDetails",cascade=CascadeType.ALL)
//	private List<BookShelfEntity> books;
}
