package com.Practice.Hackathon.Repository;

import com.Practice.Hackathon.model.ClaimDetails;
import com.Practice.Hackathon.model.PolicyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyDetailsRepo extends JpaRepository<PolicyDetails, Long> {
}
