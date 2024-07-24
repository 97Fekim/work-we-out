package com.fekim.workweout.online;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HealthCheckController {

    @GetMapping("")
    ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

}