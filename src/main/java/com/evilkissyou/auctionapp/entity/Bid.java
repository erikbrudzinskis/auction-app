package com.evilkissyou.auctionapp.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;


@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private Lot lot;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private String timeAgo;

    public Bid() {
        this.createdAt = LocalDateTime.now();
    }

    public Bid(BigDecimal amount, Lot lot) {
        this.amount = amount;
        this.lot = lot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTimeAgo() {
        LocalDateTime now = LocalDateTime.now();
        String timeAgo;
        if (Duration.between(createdAt, now).toDays() >= 1) {
            timeAgo = Duration.between(createdAt, now).toDays() + " days ago";
        } else if (Duration.between(createdAt, now).toHours() >= 1) {
            timeAgo = Duration.between(createdAt, now).toHours() + " hours ago";
        } else {
            timeAgo = Duration.between(createdAt, now).toMinutes() + " minutes ago";
        }
        return timeAgo;
    }
}
