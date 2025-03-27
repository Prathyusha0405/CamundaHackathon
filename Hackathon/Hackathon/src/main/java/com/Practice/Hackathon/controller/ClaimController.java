package com.Practice.Hackathon.controller;


import com.Practice.Hackathon.model.ClaimDetails;
import com.Practice.Hackathon.service.ClaimDetailsServiceImpl;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/time-entries")
public class ClaimController {
    @Autowired
    private ClaimDetailsServiceImpl claimDetailsService;
    @Autowired
    ZeebeClient zeebeClient;

    @PostMapping(path = "/Enter")
    public ResponseEntity<String> createTimeEntry(
            @RequestBody ClaimDetails claim) throws IOException {
        System.out.println("Inside createTimeEntry: " + claim);

        ClaimDetails savedClaim = claimDetailsService.saveCardDetails(claim);
        // Define file upload path
//        String uploadDir = "C:\\Users\\hackathon\\Documents\\" + savedClaim.getClaim_Id();
//        System.out.println("File will be saved in: " + uploadDir);
//        // Save file to the directory
//        saveFile(uploadDir, documentFileName, file);
//        // Start Camunda Zeebe Process
        ProcessInstanceEvent result = zeebeClient
                .newCreateInstanceCommand()
                .bpmnProcessId("Vehicle_Claim")
                .latestVersion()
                .variables(claim) // Make sure claim is serializable
                .send()
                .join();

        System.out.println("Process Started with Instance ID: " + result.getProcessInstanceKey());

        return new ResponseEntity<>("Submitted Successfully!", HttpStatus.CREATED);
    }
//    private void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
//        File dir = new File(uploadDir);
//        if (!dir.exists()) {
//            dir.mkdirs(); // Create directories if they do not exist
//        }
//
//        File file = new File(uploadDir + File.separator + fileName);
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            fos.write(multipartFile.getBytes());
//        }
//        System.out.println("File saved successfully!");
//    }

}
