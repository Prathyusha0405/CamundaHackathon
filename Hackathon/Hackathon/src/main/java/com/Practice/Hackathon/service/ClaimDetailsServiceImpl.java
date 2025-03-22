package com.Practice.Hackathon.service;

import com.Practice.Hackathon.Repository.ApproverDetailsRepo;
import com.Practice.Hackathon.Repository.ClaimDetailsRepo;
import com.Practice.Hackathon.Repository.ClaimStatusRepo;
import com.Practice.Hackathon.Repository.PolicyDetailsRepo;
import com.Practice.Hackathon.model.ApproverDetails;
import com.Practice.Hackathon.model.ClaimDetails;

import com.Practice.Hackathon.model.ClaimResponse;
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
    @Autowired
    private ClaimStatusRepo repo;
    @Autowired
    private ApproverDetailsRepo approverDetailsRepo;

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
    public ClaimResponse saveClaimStatus(ClaimResponse claimResponse) {
        return repo.save(claimResponse);
    }
    public List<ClaimResponse> getAllClaimStatus() {
        return repo.findAll();
    }
    public List<ApproverDetails> getApproverDetails() {
        return approverDetailsRepo.findAll();
    }


}
