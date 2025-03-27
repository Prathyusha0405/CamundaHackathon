package com.Practice.Hackathon.Repository;

import com.Practice.Hackathon.model.ApproverDetails;
import com.Practice.Hackathon.model.ClaimDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproverDetailsRepo extends JpaRepository<ApproverDetails, Long> {
}
