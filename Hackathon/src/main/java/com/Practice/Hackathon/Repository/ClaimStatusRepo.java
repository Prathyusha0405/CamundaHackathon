package com.Practice.Hackathon.Repository;

import com.Practice.Hackathon.model.ClaimResponse;
import com.Practice.Hackathon.model.PolicyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimStatusRepo extends JpaRepository<ClaimResponse, Long> {
}
