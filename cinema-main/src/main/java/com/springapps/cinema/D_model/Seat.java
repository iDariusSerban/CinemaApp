package com.springapps.cinema.D_model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer seatRow;

    @Column
    private Integer seatColumn;

    @Column
    private boolean isAvailable;

    @Column
    private Double extraPrice;



    @OneToMany(mappedBy = "seat",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("seats-tickets")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "cinemaroom_id")
    @JsonBackReference("seats-cinemarooms")
    private CinemaRoom cinemaRoom;

    public Seat() {
    }

    public Seat(Integer seatRow, Integer seatColumn, boolean isAvailable, Double extraPrice) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.isAvailable = isAvailable;
        this.extraPrice = extraPrice;
    }

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer row) {
        this.seatRow = row;
    }

    public Integer getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(Integer column) {
        this.seatColumn = column;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }
}
