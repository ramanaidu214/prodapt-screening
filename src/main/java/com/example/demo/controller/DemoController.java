package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //http://localhost:9999/remove?inputStr=555
    @GetMapping("/remove")
    public ResponseEntity<String> removeFirstAndLastChars(@RequestParam String inputStr) {

        if (inputStr.length() < 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Input string must have at least 2 characters");
        } else if (inputStr.length() == 2) {
            return ResponseEntity.ok("");
        } else {
            String result = inputStr.substring(1, inputStr.length() - 1);
            return ResponseEntity.ok(result);
        }
    }
}