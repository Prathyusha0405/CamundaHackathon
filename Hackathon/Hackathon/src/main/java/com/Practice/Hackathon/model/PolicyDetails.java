package com.Practice.Hackathon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.MatchesPattern;
import javax.annotation.RegEx;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Policy")
@Data
public class PolicyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long policy_id;
    private String policy_name;
    private String PolicyNumber;
    private String Vehicle_Model;
    private String Driver_License_Numer;
    private String SSN_Number;
    private String policy_Holder;
}
