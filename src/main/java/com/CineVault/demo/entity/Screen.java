package com.CineVault.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "screen",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_screen_number_per_theatre",
                        columnNames = {"screen_number", "theatre_id"}
                )
        }
)
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "screen_number", nullable = false)
    private Integer screenNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;
    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();


    // Mandatory no-args constructor
    public Screen() {
    }

    public Screen(Integer screenNumber, Theatre theatre) {
        this.screenNumber = screenNumber;
        this.theatre = theatre;
    }

    public Long getId() {
        return id;
    }

    public Integer getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(Integer screenNumber) {
        this.screenNumber = screenNumber;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
    public void addSeat(Seat seat) {
        seats.add(seat);
        seat.setScreen(this);
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
        seat.setScreen(null);
    }

    public List<Seat> getSeats() {
        return seats;
    }

}
