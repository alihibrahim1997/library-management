package com.maids.cc.library.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


import org.hibernate.validator.constraints.Normalized;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Entity
@Table(name="patron")
@Data
public class Patron {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "Name is required")
	    @Size(max = 100, message = "Name cannot exceed 100 characters")
	    private String name;

	    @NotBlank(message = "Email is required")
	    @Email(message = "Email should be valid")
	    private String email;

	    @NotBlank(message = "Phone number is required")
	    @Pattern(regexp = "\\+?[0-9. ()-]{7,25}", message = "Phone number format is invalid")
	    @Column(name="phone_number")
	    private String phoneNumber;

	    @Size(max = 200, message = "Address cannot exceed 200 characters")
	    @Normalized
	    private String address;

	    @NotBlank(message = "Membership type is required")
	    @Column(name="membership_type")
	    @Normalized
	    private String membershipType;

	    @PastOrPresent(message = "Registration date cannot be in the future")
	    @Column(name="registration_date")
	    private LocalDateTime registrationDate;

	    @FutureOrPresent(message = "Membership expiration date must be in the future or present")
	    @Column(name="membership_expiration_date")
	    private LocalDateTime membershipExpirationDate;

	    @Past(message = "Date of birth must be in the past")
	    @Column(name="date_of_birth")
	    private LocalDate dateOfBirth;

	    @Min(value = 0, message = "Fines cannot be negative")
	    private double fines;

	    @Min(value = 1, message = "Borrowing limit must be at least 1")
	    @Column(name="borrowing_limit")
	    private int borrowingLimit;

	    @ElementCollection
	    @Column(name="favorite_genres")
	    private Set<String> favoriteGenres;

	    @Column(name="active_status")
	    private boolean activeStatus;

}
