package com.Practice.Hackathon.service;

import com.Practice.Hackathon.Repository.ClaimDetailsRepo;
import com.Practice.Hackathon.Repository.PolicyDetailsRepo;
import com.Practice.Hackathon.model.ClaimDetails;

import com.Practice.Hackathon.model.PolicyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClaimDetailsServiceImpl {

    @Autowired
    private ClaimDetailsRepo timeEntryRepository;
    @Autowired
    private PolicyDetailsRepo policyDetailsRepo;

    public ClaimDetails saveCardDetails(ClaimDetails claimDetails) {
        claimDetails.setDate(LocalDate.now());
        return timeEntryRepository.save(claimDetails);
    }

    public List<ClaimDetails> getAllClaimDetails() {
        return timeEntryRepository.findAll();
    }
    public List<PolicyDetails> getAllPolicyDetails() {
        return policyDetailsRepo.findAll();
    }

}
