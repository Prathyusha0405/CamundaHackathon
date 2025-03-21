package com.Practice.Hackathon.jobWorker;

import com.Practice.Hackathon.model.ClaimDetails;
import com.Practice.Hackathon.model.PolicyDetails;
import com.Practice.Hackathon.service.ClaimDetailsServiceImpl;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClaimDetailsJobWorker {
    @Autowired
    private ClaimDetailsServiceImpl claimDetailsService;
    @Autowired
    private ZeebeClient zeebeClient;

    @JobWorker(type = "PolicyCheck", autoComplete = true)
    public Map<String, Boolean> getPolicyCheck(JobClient client, ActivatedJob job) {
        System.out.println("*****Service Task For Policy Details*****");
//        Map<String, Object> variables = job.getVariablesAsMap();
//        // Extract variables from the process
//         String PolicyNumber=(String) variables.get("PolicyNumber");
//         String Name=(String) variables.get("Name");
//        String SSN_Number=(String) variables.get("SSN_Number");
//        String Address=(String) variables.get("Address");
//         String PhoneNumber=(String) variables.get("PhoneNumber");
//         int Average_Km_Run_Per_year=(int) variables.get("Average_Km_Run_Per_year");
//        int Claim_Amount=(int) variables.get("Claim_Amount");
//         String Driver_License_Numer=(String) variables.get("Driver_License_Numer");
//         String Driver_Name=(String) variables.get("Driver_Name");;
//        boolean Is_Vehicle_Under_Loan= (boolean) variables.get("Is_Vehicle_Under_Loan");;
//         String Milage_Run=(String) variables.get("Milage_Run");;
//         String Previous_Claim_Numbers=(String) variables.get("Previous_Claim_Numbers");;
//         String Vehicle_Manufacture=(String) variables.get("Vehicle_Manufacture");;
//         String Vehicle_Model=(String) variables.get("Vehicle_Model");
//         String Vehicle_Usage=(String) variables.get("Vehicle_Usage");
//
//        // Create a new Claim entity
//        ClaimDetails claim = new ClaimDetails();
//        claim.setPolicyNumber(PolicyNumber);
//        claim.setName(Name);
//        claim.setAddress(Address);
//        claim.setPhoneNumber(PhoneNumber);
//        claim.setAverage_Km_Run_Per_year(Average_Km_Run_Per_year);
//       claim.setClaim_Amount(Claim_Amount);
//        claim.setDriver_License_Numer(Driver_License_Numer);
//        claim.setDriver_Name(Driver_Name);
//        claim.setIs_Vehicle_Under_Loan(Is_Vehicle_Under_Loan);
//        claim.setMilage_Run(Milage_Run);
//        claim.setDate(LocalDate.now());
//        claim.setPrevious_Claim_Numbers(Previous_Claim_Numbers);
//        claim.setVehicle_Manufacture(Vehicle_Manufacture);
//        claim.setVehicle_Model(Vehicle_Model);
//        claim.setVehicle_Usage(Vehicle_Usage);
//        claim.setSSN_Number(SSN_Number);
//        // Save the claim details to the database
//        claimDetailsService.saveCardDetails(claim);
//
//        // Complete the job
//        System.out.println("Stored Claim Details: " + claim);

        //checking Policy Details
        List<ClaimDetails> claimDetails = claimDetailsService.getAllClaimDetails();
        List<PolicyDetails> policyDetails = claimDetailsService.getAllPolicyDetails();
        boolean validPolicy=false;
        for (ClaimDetails claimDetails1 : claimDetails) {
            for (PolicyDetails policy : policyDetails) {
                if(claimDetails1.getPolicyNumber().equals(policy.getPolicyNumber())) {
                    validPolicy=true;
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
        boolean validDuplicateClaim=false;
        Map<String, Boolean> result=new HashMap<>();
        for (ClaimDetails claimDetails1 : claimDetails) {
            for (PolicyDetails policy : policyDetails) {
                if((claimDetails1.getPolicyNumber().equals(policy.getPolicyNumber()))
                        &&(claimDetails1.getDriver_License_Numer().equals(policy.getDriver_License_Numer()))
                &&(claimDetails1.getVehicle_Model().equals(policy.getVehicle_Model()))) {
                    validDuplicateClaim=true;
                    break;
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
        boolean validSSN=false;
        for (ClaimDetails claimDetails1 : claimDetails) {
            for (PolicyDetails policy : policyDetails) {
                if((claimDetails1.getSSN_Number().equals(policy.getSSN_Number()))) {
                    validSSN=true;
                    break;
                }

            }
        }
        System.out.println(validSSN);
        return Map.of("validSSN", validSSN);
    }


    }
