package com.crawl.api.controller;

import com.crawl.api.dto.RequestFilterUrlBatch1Dto;
import com.crawl.api.dto.RequestFilterUrlBatch2Dto;
import com.crawl.api.dto.ResponseBatch2ResultDto;
import com.crawl.api.dto.ResponseBath1ResultDto;
import com.crawl.api.model.Batch1CrawlResultModel;
import com.crawl.api.services.Batch1Service;
import com.crawl.api.services.Batch2Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("hacom")
public class Hacom {
    @Autowired
    private Batch1Service batch1Service;
    @Autowired
    private Batch2Service batch2Service;

    @PostMapping("filter/sql-batch2")
    public final String filterUrlForBatch2(@RequestBody RequestFilterUrlBatch1Dto dto) {
        batch1Service.Batch2UrlFilter(dto);
        return "Hello world";
    }

    @PostMapping("filter/sql-batch3")
    public final String filterUrlForBatch3(@RequestBody RequestFilterUrlBatch2Dto dto) {
        batch2Service.Batch3UrlFilter(dto);
        return "complete filter url batch 3";
    }

    @GetMapping("filter/hello")
    public final ModelAndView hello() {
        return new ModelAndView("filter/Batch2Filter");
    }

    @GetMapping("/batch1/result")
    public final ModelAndView batch1Result(@RequestParam("type") String robotId, Model model) {
        List<ResponseBath1ResultDto> getBatch1Result = batch1Service.Batch1Result(robotId);
        model.addAttribute("batch1Result", getBatch1Result);
        return new ModelAndView("filter/Batch1HacomResult");
    }

    @GetMapping("/batch2/result")
    public final ModelAndView batch2Result(@RequestParam("type") Integer robotId, Model model){
        List<ResponseBatch2ResultDto> getBatch2Result = batch2Service.getListBatch2Result(robotId);
        model.addAttribute("batch2Result", getBatch2Result);
        return new ModelAndView("filter/Batch2Result");
    }

}
