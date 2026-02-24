package com.CineVault.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "movie_show",
        indexes = {
                @Index(name = "idx_show_movie", columnList = "movie_id"),
                @Index(name = "idx_show_screen", columnList = "screen_id"),
                @Index(name = "idx_show_start_time", columnList = "show_start_time")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_show_screen_start",
                        columnNames = {"screen_id", "show_start_time"}
                )
        }
)
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Movie relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    // Screen relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @Column(name = "show_start_time", nullable = false)
    private LocalDateTime showStartTime;

    @Column(name = "show_end_time", nullable = false)
    private LocalDateTime showEndTime;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Required by JPA
    public Show() {}

    // Business Constructor
    public Show(Movie movie,
                Screen screen,
                LocalDateTime showStartTime,
                LocalDateTime showEndTime,
                BigDecimal basePrice) {

        this.movie = movie;
        this.screen = screen;
        this.showStartTime = showStartTime;
        this.showEndTime = showEndTime;
        this.basePrice = basePrice;
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

    // Getters

    public Long getId() { return id; }

    public Movie getMovie() { return movie; }

    public Screen getScreen() { return screen; }

    public LocalDateTime getShowStartTime() { return showStartTime; }

    public LocalDateTime getShowEndTime() { return showEndTime; }

    public BigDecimal getBasePrice() { return basePrice; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters

    public void setShowStartTime(LocalDateTime showStartTime) {
        this.showStartTime = showStartTime;
    }

    public void setShowEndTime(LocalDateTime showEndTime) {
        this.showEndTime = showEndTime;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}