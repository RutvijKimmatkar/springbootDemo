
package com.example.civiccomplaints.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.civiccomplaints.entity.Complaint;
import com.example.civiccomplaints.repository.ComplaintRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {
    private final ComplaintRepository repo;

    public ComplaintController(ComplaintRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }

    @PostMapping
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        complaint.setId(UUID.randomUUID().toString());
        if (complaint.getStatus() == null) complaint.setStatus("open");
        if (complaint.getUpvoteCount() == null) complaint.setUpvoteCount(0);
        return repo.save(complaint);
    }
}