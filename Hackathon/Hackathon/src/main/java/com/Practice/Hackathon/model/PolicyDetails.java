package com.Practice.Hackathon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
