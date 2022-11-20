package com.crawl.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hacom")
public class Hacom {
    @GetMapping("filter/sql-batch2")
    public final String filterUrlForBatch2(){
        return "Hello world";
    }
}
