package com.example.civiccomplaints.repository;

import com.example.civiccomplaints.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {
}