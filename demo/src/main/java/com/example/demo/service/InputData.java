package com.example.demo.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputData {

    // Field for the "data" array in the JSON request
    private List<String> data;

    // Field for the optional "file_b64" in the JSON request
    @JsonProperty("file_b64")
    private String fileB64;

    // Default constructor
    public InputData() {
    }

    // Parameterized constructor
    public InputData(List<String> data, String fileB64) {
        this.data = data;
        this.fileB64 = fileB64;
    }

    // Getter for data
    public List<String> getData() {
        return data;
    }

    // Setter for data
    public void setData(List<String> data) {
        this.data = data;
    }

    // Getter for fileB64
    public String getFileB64() {
        return fileB64;
    }

    // Setter for fileB64
    public void setFileB64(String fileB64) {
        this.fileB64 = fileB64;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "data=" + data +
                ", fileB64='" + fileB64 + '\'' +
                '}';
    }
}
