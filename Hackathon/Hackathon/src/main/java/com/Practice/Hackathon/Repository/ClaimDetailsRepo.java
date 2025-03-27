package com.Practice.Hackathon.Repository;

import com.Practice.Hackathon.model.ClaimDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimDetailsRepo extends JpaRepository<ClaimDetails, Long> {
    void deleteById(Long claimId);
}
