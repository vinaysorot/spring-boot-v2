package com.example.demo.controller;

import com.example.demo.service.InputData;
import org.apache.tika.Tika;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bfhl")
public class TestContoller {

    @PostMapping
    public ResponseEntity<Map<String, Object>> processPost(@RequestBody InputData inputData) {
        Map<String, Object> response = new HashMap<>();

        String userId = "john_doe_17091999";
        response.put("is_success", true);
        response.put("user_id", userId);
        response.put("email","vinaysorot@gmail.com");
        response.put("roll_number","ABCD123");


        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        String highestLowercase = null;
        boolean isPrimeFound = false;

        for (String item : inputData.getData()) {
            if (item.matches("\\d+")) {
                numbers.add(item);
                if (isPrime(Integer.parseInt(item))) isPrimeFound = true;
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
                if (item.matches("[a-z]") && (highestLowercase == null || item.compareTo(highestLowercase) > 0)) {
                    highestLowercase = item;
                }
            }
        }

        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_lowercase_alphabet", highestLowercase == null ? new ArrayList<>() : List.of(highestLowercase));
        response.put("is_prime_found", isPrimeFound);

        if (inputData.getFileB64() != null) {
            response.put("file_valid", true);
            byte[] decodedBytes = Base64.getDecoder().decode(inputData.getFileB64());
            Tika tika = new Tika();
            String mimeType = tika.detect(decodedBytes);
            System.out.println("MIME Type: " + mimeType);
            double fileSizeInKB = decodedBytes.length / 1024.0;
            response.put("file_mime_type", mimeType);
            response.put("file_size_kb", fileSizeInKB);
        } else {
            response.put("file_valid", false);
        }


        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> processGet() {
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
