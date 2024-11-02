package com.maids.cc.library.model;
import java.util.Set;


import org.hibernate.validator.constraints.Normalized;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Table(name="book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    @Normalized
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 100, message = "Author name cannot exceed 100 characters")
    @Normalized
    private String author;

    @Min(value = 1500, message = "Publication year should be after 1500")
    @Max(value = 2024, message = "Publication year cannot be in the future")
    @Column(name="publication_year")
    private int publicationYear;

    @NotBlank(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN should be between 10 and 13 characters")
    private String isbn;

    @Size(max = 50, message = "Genre cannot exceed 50 characters")
    @Normalized
    private String genre;

    @Size(max = 100, message = "Publisher name cannot exceed 100 characters")
    @Normalized
    private String publisher;

    @Size(max = 30, message = "Language cannot exceed 30 characters")
    @Normalized
    private String language;

    @Min(value = 1, message = "Total copies must be at least 1")
    private int totalCopies;

    @Min(value = 0, message = "Available copies cannot be negative")
    @Column(name="available_copies")
    private int availableCopies;

    @Size(max = 20, message = "Shelf location cannot exceed 20 characters")
    @Column(name="shelf_location")
    @Normalized
    private String shelfLocation;

    @Lob
    @Normalized
    private String summary;

    @ElementCollection
    private Set<String> keywords;

}
