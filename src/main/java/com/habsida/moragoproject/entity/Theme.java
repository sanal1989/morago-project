package com.habsida.moragoproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Theme extends AbstractAuditable{
    private String description;
    private Boolean isActive;
    private Boolean isPopular;
    private String koreanTitle;
    private String name;
    private Double nightPrice;
    private Double price;

    @ManyToOne
    private Category category = new Category();

    @OneToOne
    private File file = new File();

    public Theme() {
    }

    public Theme(String description, Boolean isActive, Boolean isPopular, String koreanTitle, String name, Double nightPrice, Double price, Category category, File file) {
        this.description = description;
        this.isActive = isActive;
        this.isPopular = isPopular;
        this.koreanTitle = koreanTitle;
        this.name = name;
        this.nightPrice = nightPrice;
        this.price = price;
        this.category = category;
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getPopular() {
        return isPopular;
    }

    public void setPopular(Boolean popular) {
        isPopular = popular;
    }

    public String getKoreanTitle() {
        return koreanTitle;
    }

    public void setKoreanTitle(String koreanTitle) {
        this.koreanTitle = koreanTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(Double nightPrice) {
        this.nightPrice = nightPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
