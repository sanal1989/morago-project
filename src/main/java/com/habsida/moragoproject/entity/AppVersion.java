package com.habsida.moragoproject.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class AppVersion {

    @Id
    private EPlatform platform;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    private String latest;
    private String min;

    public AppVersion() {
    }

    public AppVersion(EPlatform platform, LocalDateTime createdAt, LocalDateTime updatedAt, String latest, String min) {
        this.platform = platform;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.latest = latest;
        this.min = min;
    }

    public EPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(EPlatform platform) {
        this.platform = platform;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
