package com.Practice.Hackathon.Repository;

import com.Practice.Hackathon.model.ApproverDetails;
import com.Practice.Hackathon.model.VehicleClaimAllDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleClaimAllDetailsRepo extends JpaRepository<VehicleClaimAllDetails, Long> {
}
