package com.CineVault.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "movie",
        indexes = {
                @Index(name = "idx_movie_title", columnList = "title"),
                @Index(name = "idx_movie_genre", columnList = "genre")
        }
)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false, length = 100)
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private MovieGenre genre;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(precision = 3, scale = 1)
    private BigDecimal rating;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Required by JPA
    public Movie() {}

    // Business Constructor
    public Movie(String title,
                 String description,
                 Integer durationMinutes,
                 String language,
                 MovieGenre genre,
                 LocalDate releaseDate) {

        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.language = language;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.isActive = true;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters & Setters

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public MovieGenre getGenre() { return genre; }
    public void setGenre(MovieGenre genre) { this.genre = genre; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
