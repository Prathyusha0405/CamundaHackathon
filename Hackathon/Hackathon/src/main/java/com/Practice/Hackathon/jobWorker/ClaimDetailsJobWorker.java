package com.Practice.Hackathon.jobWorker;

import com.Practice.Hackathon.model.ApproverDetails;
import com.Practice.Hackathon.model.ClaimDetails;
import com.Practice.Hackathon.model.ClaimResponse;
import com.Practice.Hackathon.model.PolicyDetails;
import com.Practice.Hackathon.service.ApproverSendEmail;
import com.Practice.Hackathon.service.ClaimDetailsServiceImpl;

import com.Practice.Hackathon.service.SendEmail;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClaimDetailsJobWorker {
    @Autowired
    private ClaimDetailsServiceImpl claimDetailsService;
    @Autowired
    private SendEmail sendEmaildetails;
    @Autowired
    private ApproverSendEmail approverSendEmail;

    @JobWorker(type = "PolicyCheck", autoComplete = true)
    public Map<String, Boolean> getPolicyCheck(JobClient client, ActivatedJob job) {
        System.out.println("*****Service Task For Policy Details*****");
        List<ClaimDetails> claimDetails = claimDetailsService.getAllClaimDetails();
        List<PolicyDetails> policyDetails = claimDetailsService.getAllPolicyDetails();
        List<ClaimResponse> claimResponses = claimDetailsService.getAllClaimStatus();

        boolean validPolicy=false;
        for (ClaimDetails claimDetails1 : claimDetails) {
            for (PolicyDetails policy : policyDetails) {
                    if (claimDetails1.getPolicyNumber().equals(policy.getPolicyNumber())) {
                        validPolicy = true;
                        break;
                    } else {
                        ClaimResponse claim=new ClaimResponse();
                        claim.setClaimId(claimDetails1.getClaim_Id());
                        claim.setClaimStatus("Rejected");
                        claimDetailsService.saveClaimStatus(claim);
                    }
                }

        }
        System.out.println(validPolicy);
        return Map.of("validPolicy", validPolicy);
    }
    // Checking Duplicate Claim Details
    @JobWorker(type = "DuplicateCheck", autoComplete = true)
    public Map<String, Boolean> getDuplicateCheck(JobClient client, ActivatedJob job) {
        List<ClaimDetails> claimDetails = claimDetailsService.getAllClaimDetails();
        List<PolicyDetails> policyDetails = claimDetailsService.getAllPolicyDetails();
        List<ClaimResponse> claimResponses = claimDetailsService.getAllClaimStatus();

        boolean validDuplicateClaim=false;
        Map<String, Boolean> result=new HashMap<>();
        for (ClaimDetails claimDetails1 : claimDetails) {
            for (PolicyDetails policy : policyDetails) {
                for (ClaimResponse response : claimResponses) {
                    if ((claimDetails1.getPolicyNumber().equals(policy.getPolicyNumber()))
                            && (claimDetails1.getDriver_License_Numer().equals(policy.getDriver_License_Numer()))
                            && (claimDetails1.getVehicle_Model().equals(policy.getVehicle_Model()))) {
                        System.out.println(claimDetails1.getDriver_License_Numer());
                        System.out.println(claimDetails1.getVehicle_Model());
                        System.out.println(claimDetails1.getPolicyNumber());
                        System.out.println(policy.getDriver_License_Numer());
                        System.out.println(policy.getVehicle_Model());
                        System.out.println(policy.getPolicyNumber());
                        validDuplicateClaim = true;
                        break;
                    }
                    else {
                        ClaimResponse claim=new ClaimResponse();
                        claim.setClaimId(claimDetails1.getClaim_Id());
                        claim.setClaimStatus("Rejected");
                        claimDetailsService.saveClaimStatus(claim);
                    }
                }
            }
        }
        result.put("validDuplicateClaim", validDuplicateClaim);
        System.out.println(validDuplicateClaim);
        return result;
    }
    // Checking Duplicate Claim Details
    @JobWorker(type = "CreditabilyCheck", autoComplete = true)
    public Map<String, Boolean> getCreditabilityCheck(JobClient client, ActivatedJob job) {
        List<ClaimDetails> claimDetails = claimDetailsService.getAllClaimDetails();
        List<PolicyDetails> policyDetails = claimDetailsService.getAllPolicyDetails();
        List<ClaimResponse> claimResponses = claimDetailsService.getAllClaimStatus();

        boolean validSSN=false;
        for (ClaimDetails claimDetails1 : claimDetails) {
            for (PolicyDetails policy : policyDetails) {
                for (ClaimResponse response : claimResponses) {
                    if ((claimDetails1.getSSN_Number().equals(policy.getSSN_Number()))) {
                        validSSN = true;
                        break;
                    } else {
                        ClaimResponse claim=new ClaimResponse();
                        claim.setClaimId(claimDetails1.getClaim_Id());
                        claim.setClaimStatus("Rejected");
                        claimDetailsService.saveClaimStatus(claim);
                    }
                }
            }
        }

        System.out.println(validSSN);
        return Map.of("validSSN", validSSN);
    }
    @JobWorker(type = "sendMail", autoComplete = true)
    public void sendMail(JobClient client, ActivatedJob job) {
        List<ClaimDetails> claimDetails = claimDetailsService.getAllClaimDetails();
        sendEmaildetails.mail();
       System.out.println("*****Sent mail*****");
    }
    @JobWorker(type = "sendMailToApprover", autoComplete = true)
    public void sendDetailsToApprover(JobClient client, ActivatedJob job) {
        List<ClaimDetails> claimDetails = claimDetailsService.getAllClaimDetails();
        List<ApproverDetails> approverDetails = claimDetailsService.getApproverDetails();
        String role=null;
        for (ClaimDetails claimDetails1 : claimDetails) {
            for (ApproverDetails policy : approverDetails) {
                if (claimDetails1.getClaim_Amount() >= 0 && claimDetails1.getClaim_Amount() <= 5000) {
                    role="Senior_Manager";
                } else if (claimDetails1.getClaim_Amount() >= 5001 && claimDetails1.getClaim_Amount() <= 10000) {
                    role="General_Manager";
                } else if (claimDetails1.getClaim_Amount() >= 10001 && 10000 <= 50000) {
                    role="Director";
                } else if (claimDetails1.getClaim_Amount() > 50001) {
                    role="Sr.Director";
                }
                }
            }
        approverSendEmail.mail(role);
        System.out.println("*****Sent mail*****");
    }



}
