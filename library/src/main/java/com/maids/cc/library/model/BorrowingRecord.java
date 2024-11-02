package com.maids.cc.library.model;



import org.hibernate.validator.constraints.Normalized;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="borrowing_record")
@Data
public class BorrowingRecord {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "book_id", nullable = false)
	    @NotNull(message = "Book is required")
	    private Book book;

	    @ManyToOne
	    @JoinColumn(name = "patron_id", nullable = false)
	    @NotNull(message = "Patron is required")
	    private Patron patron;

	    @NotNull(message = "Borrow date is required")
	    @Column(name="borrow_date")
	    private LocalDateTime borrowDate;

	    @NotNull(message = "Due date is required")
	    @Future(message = "Due date must be in the future")
	    @Column(name="due_date")
	    private LocalDateTime dueDate;

	    @Column(name="return_date")
	    private LocalDateTime returnDate;

	    @NotBlank(message = "Status is required")
	    @Normalized
	    private String status;

	    @Min(value = 0, message = "Fines incurred cannot be negative")
	    @Column(name="fines_incurred")
	    @Normalized
	    private double finesIncurred;

	    @Size(max = 200, message = "Condition on borrowing cannot exceed 200 characters")
	    @Column(name="condition_on_borrowing")
	    private String conditionOnBorrowing;

	    @Size(max = 200, message = "Condition on return cannot exceed 200 characters")
	    @Column(name="condition_on_return")
	    @Normalized
	    private String conditionOnReturn;

	    @Min(value = 0, message = "Renewal count cannot be negative")
	    @Column(name="renewal_count")
	    private int renewalCount;

	    private Long staffId; 
	
}
