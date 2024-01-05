package com.ademy.ABCSERVICE.controller;

import com.ademy.ABCSERVICE.ResponseModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("abc/v0")
public class AbcController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_ABC = "serviceABC";

    @GetMapping("/{value}")
    @CircuitBreaker(name = SERVICE_ABC, fallbackMethod = "serviceABCFallback")
    public ResponseEntity<ResponseModel> serviceABC(@PathVariable String value) {
        log.info("api request start");
        String url = "http://localhost:8085/xyz/v0/get";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, String> req = new HashMap<>();
        req.put("key", value);
        HttpEntity<HashMap> request = new HttpEntity<>(req, headers);
        String response = restTemplate.postForObject(
                url,
                request,
                String.class
        );

        ResponseModel responseModel = new ResponseModel(response);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
