package com.golfclub.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private double entryFee;
    private double prizeAmount;

    @ManyToMany
    @JoinTable(
        name = "tournament_members",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    @JsonManagedReference
    @JsonIgnore  // Prevent 415 error during POST
    private Set<Member> participants = new HashSet<>();

    public Tournament() {}

    public Tournament(LocalDate startDate, LocalDate endDate, String location, double entryFee, double prizeAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.prizeAmount = prizeAmount;
    }

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getLocation() { return location; }
    public double getEntryFee() { return entryFee; }
    public double getPrizeAmount() { return prizeAmount; }
    public Set<Member> getParticipants() { return participants; }

    public void setId(Long id) { this.id = id; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setLocation(String location) { this.location = location; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }
    public void setPrizeAmount(double prizeAmount) { this.prizeAmount = prizeAmount; }
    public void setParticipants(Set<Member> participants) { this.participants = participants; }
}
