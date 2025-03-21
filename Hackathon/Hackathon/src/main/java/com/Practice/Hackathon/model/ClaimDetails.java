package com.Practice.Hackathon.model;

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
    private String PolicyNumber;
    private String Name;
    private String Address;
    private String PhoneNumber;
    private  int Average_Km_Run_Per_year;
    private int Claim_Amount;
    private String Driver_License_Numer;
    private String Driver_Name;
    private boolean Is_Vehicle_Under_Loan;
    private String Milage_Run;
    private String Previous_Claim_Numbers;
    private String Vehicle_Manufacture;
    private String Vehicle_Model;
    private String Vehicle_Usage;
    private String SSN_Number;
}
