package com.example.civiccomplaints.repository;

import com.example.civiccomplaints.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, String> {
    long countByStatus(String status);
}