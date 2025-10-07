package com.example.civiccomplaints.controller;

import com.example.civiccomplaints.dto.MetricsDto;
import com.example.civiccomplaints.dto.RecentComplaintDto;
import com.example.civiccomplaints.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*") // change in production to your frontend origin
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/metrics")
    public MetricsDto metrics() {
        return service.getMetrics();
    }

    @GetMapping("/recent-complaints")
    public List<RecentComplaintDto> recentComplaints(@RequestParam(value = "limit", defaultValue = "20") int limit) {
        return service.getRecentComplaints(limit);
    }
}