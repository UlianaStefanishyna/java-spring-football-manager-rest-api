package com.football.manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() throws InterruptedException {
        Thread.sleep(10000);
        return ResponseEntity.ok("hello");
    }

    @GetMapping(value = "/world")
    public ResponseEntity<String> world() throws InterruptedException {
        Thread.sleep(10000);
        return ResponseEntity.ok("world");
    }
}