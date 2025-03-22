package com.Practice.Hackathon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Approver")
@Data
public class ApproverDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sap_id;
    private String email;
    private String role;
}
