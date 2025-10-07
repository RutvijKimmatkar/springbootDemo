package com.example.civiccomplaints.service;

import com.example.civiccomplaints.dto.MetricsDto;
import com.example.civiccomplaints.dto.RecentComplaintDto;
import com.example.civiccomplaints.repository.DashboardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final DashboardRepository repo;

    public DashboardService(DashboardRepository repo) {
        this.repo = repo;
    }

    public MetricsDto getMetrics() {
        return repo.fetchMetrics();
    }

    public List<RecentComplaintDto> getRecentComplaints(int limit) {
        return repo.fetchRecentComplaints(limit);
    }
}