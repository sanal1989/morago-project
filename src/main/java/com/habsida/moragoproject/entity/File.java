package com.habsida.moragoproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class File extends AbstractAuditable{

    private String originalTitle;
    private String path;
    private String type;

    @OneToOne
    User user;

    public File() {
    }

    public File(String originalTitle, String path, String type, User user) {
        this.originalTitle = originalTitle;
        this.path = path;
        this.type = type;
        this.user = user;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
