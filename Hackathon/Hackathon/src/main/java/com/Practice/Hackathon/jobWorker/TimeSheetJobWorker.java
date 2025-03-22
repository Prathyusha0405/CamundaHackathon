package com.Practice.Hackathon.jobWorker;

import com.Practice.Hackathon.model.ClaimDetails;
import com.Practice.Hackathon.model.PolicyDetails;
import com.Practice.Hackathon.service.ClaimDetailsServiceImpl;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TimeSheetJobWorker {
    @Autowired
    private ClaimDetailsServiceImpl claimDetailsService;

//     @JobWorker(type = "Approved", autoComplete = true)
//     public Map<String, Boolean> getApproved(JobClient client, ActivatedJob job) {
//         System.out.println("*****Service Task For Approved*****");
//         Map<String, Object> variables = job.getVariablesAsMap();
//         // Extract variables from the process
//          String PolicyNumber=(String) variables.get("PolicyNumber");
//          String Name=(String) variables.get("Name");
//          String Address=(String) variables.get("Address");
//          String PhoneNumber=(String) variables.get("PhoneNumber");
//        //  int Average_Km_Run_Per_year=(int) variables.get("Average_Km_Run_Per_year");
//         // int Claim_Amount=(int) variables.get("Claim_Amount");
//          String Driver_License_Numer=(String) variables.get("Driver_License_Numer");
//          String Driver_Name=(String) variables.get("Driver_Name");;
//         ArrayList Is_Vehicle_Under_Loan=(ArrayList) variables.get("Is_Vehicle_Under_Loan");;
//          String Milage_Run=(String) variables.get("Milage_Run");;
//          String Previous_Claim_Numbers=(String) variables.get("Previous_Claim_Numbers");;
//          String Vehicle_Manufacture=(String) variables.get("Vehicle_Manufacture");;
//          String Vehicle_Model=(String) variables.get("Vehicle_Model");
//          String Vehicle_Usage=(String) variables.get("Vehicle_Usage");

//         // Create a new Claim entity
//         ClaimDetails claim = new ClaimDetails();
//         claim.setPolicyNumber(PolicyNumber);
//         claim.setName(Name);
//         claim.setAddress(Address);
//         claim.setPhoneNumber(PhoneNumber);
//        // claim.setAverage_Km_Run_Per_year(Average_Km_Run_Per_year);
//        // claim.setClaim_Amount(Claim_Amount);
//         claim.setDriver_License_Numer(Driver_License_Numer);
//         claim.setDriver_Name(Driver_Name);
//         claim.setIs_Vehicle_Under_Loan(Is_Vehicle_Under_Loan);
//         claim.setMilage_Run(Milage_Run);
//         claim.setDate(LocalDate.now());
//         claim.setPrevious_Claim_Numbers(Previous_Claim_Numbers);
//         claim.setVehicle_Manufacture(Vehicle_Manufacture);
//         claim.setVehicle_Model(Vehicle_Model);
//         claim.setVehicle_Usage(Vehicle_Usage);
//         // Save the claim details to the database
//         claimDetailsService.saveCardDetails(claim);

//         // Complete the job
//         System.out.println("Stored Claim Details: " + claim);

//         //checking Policy Details
//         List<ClaimDetails> claimDetails = claimDetailsService.getAllClaimDetails();
//         List<PolicyDetails> policyDetails = claimDetailsService.getAllPolicyDetails();
//         boolean validPolicy=false;
//         for (ClaimDetails claimDetails1 : claimDetails) {
//             for (PolicyDetails policy : policyDetails) {
//                 if(claimDetails1.getPolicyNumber().equals(policy.getPolicyNumber())) {
//                     validPolicy=true;
//                 }
//             }
//         }
//         System.out.println(validPolicy);
// //        client.newCompleteCommand(job.getKey()).send().join();
//         return Map.of("validPolicy", validPolicy);
//     }


}
