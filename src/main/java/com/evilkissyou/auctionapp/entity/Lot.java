package com.evilkissyou.auctionapp.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.time.Duration;
import java.time.LocalDateTime;


@Entity
@Table(name = "lots")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "start_price")
    private BigDecimal startPrice;

    @Column(name = "lot_step")
    private BigDecimal lotStep;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "winner_id")
    private Long winnerId;

    @Column(name = "passed_moderation")
    private boolean passedModeration;

    @Transient
    private Duration tillEnds;

    @Transient
    private MultipartFile image;

    public Lot() {
        this.createdAt = LocalDateTime.now();
        this.imageUrl = null;
    }

    public Lot(LocalDateTime endDate, String name, int categoryId, String description, String imageUrl, BigDecimal startPrice, BigDecimal lotStep, Long userId, Long winnerId, boolean passedModeration, MultipartFile image) {
        this.endDate = endDate;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.imageUrl = imageUrl;
        this.startPrice = new BigDecimal(String.valueOf(startPrice)).setScale(2, RoundingMode.HALF_UP);
        this.lotStep = new BigDecimal(String.valueOf(lotStep)).setScale(2, RoundingMode.HALF_UP);
        this.userId = userId;
        this.winnerId = winnerId;
        this.passedModeration = passedModeration;
        this.tillEnds = Duration.between(LocalDateTime.now(), endDate);
        this.image = image;
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getLotStep() {
        return lotStep;
    }

    public void setLotStep(BigDecimal lotStep) {
        this.lotStep = lotStep;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public boolean isPassedModeration() {
        return passedModeration;
    }

    public void setPassedModeration(boolean passedModeration) {
        this.passedModeration = passedModeration;
    }

    public String getTillEnds() {
        return  Duration.between(LocalDateTime.now(), endDate).toDaysPart() +
                "D " +
                Duration.between(LocalDateTime.now(), endDate).toHoursPart() +
                "H " +
                Duration.between(LocalDateTime.now(), endDate).toMinutesPart() +
                "M " +
                Duration.between(LocalDateTime.now(), endDate).toSecondsPart() +
                "S";
    }

    public void setTillEnds(Duration tillEnds) {
        this.tillEnds = Duration.between(LocalDateTime.now(), endDate);
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
