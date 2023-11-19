package com.ademy.XYZSERVICE.controller;

import com.ademy.XYZSERVICE.model.HashedValue;
import com.ademy.XYZSERVICE.model.HashedValueRequest;
import com.ademy.XYZSERVICE.service.HashedValueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("xyz/v0")
public class HashedValueController {

    @Autowired
    private HashedValueServiceImpl service;

    public HashedValueController(HashedValueServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/save")
    public void saveHashedValue(@RequestBody HashedValueRequest request){
        service.addValue(request.getKey());
    }

    @PostMapping("/get")
    public String getHashedValue(@RequestBody HashedValueRequest request){
        return service.getValue(request.getKey());
    }
}
