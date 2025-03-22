package com.Practice.Hackathon.controller;


import com.Practice.Hackathon.model.ClaimDetails;
import com.Practice.Hackathon.service.ClaimDetailsServiceImpl;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/time-entries")
public class ClaimController {
    @Autowired
    private ClaimDetailsServiceImpl claimDetailsService;
    @Autowired
    ZeebeClient zeebeClient;

    @PostMapping("/Enter")
    public ResponseEntity<String> createTimeEntry(@RequestBody ClaimDetails claim) {
System.out.println("Inside createTimeEntry"+claim);
        claimDetailsService.saveCardDetails(claim);
        ProcessInstanceEvent result= zeebeClient.newCreateInstanceCommand().bpmnProcessId("Vehicle_Claim")
                .latestVersion()
                .variables(claim).send().join();
        System.out.println("outside createTimeEntry"+claim);

        return new ResponseEntity<>("Submited", HttpStatus.CREATED);
    }

}
