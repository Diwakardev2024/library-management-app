package com.appsdevelopers.app.ws.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "BookShelf")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BookShelfEntity implements Serializable {

	private static final long serialVersionUID = 133533697974231027L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 40, nullable = false)
	private String serialNo;

	@Column(length = 30, nullable = false)
	private String bookId;

	@Column()
	private boolean isAvailable=false;

	@Column(updatable = false)
	@CreatedDate
	private Instant createdOn;
	@LastModifiedDate
	private Instant lastUpdatedOn;

	
	@OneToMany(mappedBy = "bookDetails", cascade = CascadeType.ALL)
    private List<BookEntity> books;

}
