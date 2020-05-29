package com.example.demo.controller;

import com.example.demo.sevice.PushResultTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/result")
@RestController
public class PushResultController {

    @Autowired
    private PushResultTempService pushResultTempService;

    @GetMapping("/{index}")
    public Object getResult(@PathVariable int index) {
        return pushResultTempService.getResult(index);
    }
}
