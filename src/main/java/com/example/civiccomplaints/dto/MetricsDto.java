package com.example.civiccomplaints.dto;

public class MetricsDto {
    private long totalComplaints;
    private long resolved;
    private long inProgress;
    private Double avgResolutionDays; // nullable if we can't compute it

    public MetricsDto() {}
    public MetricsDto(long totalComplaints, long resolved, long inProgress, Double avgResolutionDays) {
        this.totalComplaints = totalComplaints;
        this.resolved = resolved;
        this.inProgress = inProgress;
        this.avgResolutionDays = avgResolutionDays;
    }

    // getters & setters
    public long getTotalComplaints() { return totalComplaints; }
    public void setTotalComplaints(long totalComplaints) { this.totalComplaints = totalComplaints; }
    public long getResolved() { return resolved; }
    public void setResolved(long resolved) { this.resolved = resolved; }
    public long getInProgress() { return inProgress; }
    public void setInProgress(long inProgress) { this.inProgress = inProgress; }
    public Double getAvgResolutionDays() { return avgResolutionDays; }
    public void setAvgResolutionDays(Double avgResolutionDays) { this.avgResolutionDays = avgResolutionDays; }
}