package com.Practice.Hackathon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Claim")
@Data
public class ClaimDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Claim_Id;
    private LocalDate Date;
    @JsonProperty("PolicyNumber")
    private String PolicyNumber;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("Email")
    private String Email;
    @JsonProperty("Address")
    private String Address;
    @JsonProperty("PhoneNumber")
    private String PhoneNumber;
    @JsonProperty("Average_Km_Run_Per_year")
    private  int Average_Km_Run_Per_year;
    @JsonProperty("Claim_Amount")
    private int Claim_Amount;
    @JsonProperty("Driver_License_Numer")
    private String Driver_License_Numer;
    @JsonProperty("Driver_Name")
    private String Driver_Name;
    @JsonProperty("Is_Vehicle_Under_Loan")
    private boolean Is_Vehicle_Under_Loan;
    @JsonProperty("Milage_Run")
    private String Milage_Run;
    @JsonProperty("Previous_Claim_Numbers")
    private String Previous_Claim_Numbers;
    @JsonProperty("Vehicle_Manufacture")
    private String Vehicle_Manufacture;
    @JsonProperty("Vehicle_Model")
    private String Vehicle_Model;
    @JsonProperty("Vehicle_Usage")
    private String Vehicle_Usage;
    @JsonProperty("SSN_Number")
    private String SSN_Number;
  //  @JsonProperty("files")
//    @ElementCollection
//    private List<String> filePaths;
  @JsonProperty("docs")
  private String document;
}
