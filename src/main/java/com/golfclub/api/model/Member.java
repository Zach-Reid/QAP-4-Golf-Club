package com.golfclub.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phone;

    @Column(name = "membership_start")
    private LocalDate membershipStart;

    @Column(name = "duration_months")
    private int durationMonths;

    @ManyToMany(mappedBy = "participants")
    @JsonBackReference
    @JsonIgnore  // <- Ignore during deserialization to fix 415
    private Set<Tournament> tournaments = new HashSet<>();

    public Member() {}

    public Member(String name, String address, String email, String phone, LocalDate membershipStart, int durationMonths) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.membershipStart = membershipStart;
        this.durationMonths = durationMonths;
    }

    // --- Getters and setters ---

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getMembershipStart() { return membershipStart; }
    public int getDurationMonths() { return durationMonths; }
    public Set<Tournament> getTournaments() { return tournaments; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setMembershipStart(LocalDate membershipStart) { this.membershipStart = membershipStart; }
    public void setDurationMonths(int durationMonths) { this.durationMonths = durationMonths; }
    public void setTournaments(Set<Tournament> tournaments) { this.tournaments = tournaments; }
}
