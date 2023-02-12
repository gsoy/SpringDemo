package com.gsoy.springdemo.controller;

import com.gsoy.springdemo.kafka.Consumer;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/kafka", consumes = MediaType.ALL_VALUE)
public class KafkaController {

    @Autowired
    Consumer consumer;

    @GetMapping("/folder")
    @Operation(summary = "Klasör silme işlemi")
    public void greeting(@RequestParam(name = "id", required = false, defaultValue = "1") String id) throws IOException {
        consumer.consumer();
    }
}
