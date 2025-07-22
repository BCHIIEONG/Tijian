package com.jida.tijian.controller;

import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Autowired
    HealthService healthService;

    @RequestMapping("/listHealth")
    public ResponseObject listHealth() {
        return healthService.listHealth();
    }
}
