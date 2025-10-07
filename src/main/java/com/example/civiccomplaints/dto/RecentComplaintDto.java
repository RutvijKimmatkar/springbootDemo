package com.example.civiccomplaints.dto;

import java.time.OffsetDateTime;

public class RecentComplaintDto {
    private String id;
    private String category;
    private String location; // best-effort, may be empty if no place column
    private OffsetDateTime dateFiled;
    private String status;

    public RecentComplaintDto() {}
    public RecentComplaintDto(String id, String category, String location, OffsetDateTime dateFiled, String status) {
        this.id = id;
        this.category = category;
        this.location = location;
        this.dateFiled = dateFiled;
        this.status = status;
    }

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public OffsetDateTime getDateFiled() { return dateFiled; }
    public void setDateFiled(OffsetDateTime dateFiled) { this.dateFiled = dateFiled; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}