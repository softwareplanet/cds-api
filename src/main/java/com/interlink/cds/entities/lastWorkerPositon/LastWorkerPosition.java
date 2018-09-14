package com.interlink.cds.entities.lastWorkerPositon;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LastWorkerPosition implements Serializable {
    private Double longitude;
    private Double latitude;
    private LocalDateTime date;
    private String username;

    public LastWorkerPosition() {
    }

    public LastWorkerPosition(Double longitude, Double latitude, LocalDateTime date, String username) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.username = username;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
