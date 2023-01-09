package com.crawl.api.controller;

import com.crawl.api.common.Contains;
import com.crawl.api.dto.*;
import com.crawl.api.model.Batch1CrawlResultModel;
import com.crawl.api.services.Batch1Service;
import com.crawl.api.services.Batch2Service;

import java.util.ArrayList;
import java.util.List;

import com.crawl.api.services.Batch3Service;
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
    @Autowired
    private Batch3Service batch3Service;

    @PostMapping("filter/sql-batch2")
    public final String filterUrlForBatch2(@RequestBody RequestFilterUrlBatch1Dto dto) {
        batch2Service.deleteAllBatch2CrawlUrls();
        batch1Service.Batch2UrlFilter(dto);
        return "Hello world";
    }

    @PostMapping("filter/sql-batch2/checkbox")
    public final String filterUrlForBatch2Checkbox(@RequestBody RequestFilterUrlBatch1CheckboxDto dto) {
        batch2Service.deleteAllBatch2CrawlUrls();
        batch1Service.Batch2UrlFilterCheckbox(dto);
        return "Hello world";
    }

    @PostMapping("filter/sql-batch3")
    public final String filterUrlForBatch3(@RequestBody RequestFilterUrlBatch2Dto dto) {
        batch3Service.deleteAllBatch3CrawlLists();
        batch2Service.Batch3UrlFilter(dto);
        return "complete filter url batch 3";
    }

    @PostMapping("filter/sql-batch3/checkbox")
    public final String filterUrlForBatch3Checkbox(@RequestBody RequestFilterUrlBatch2CheckboxDto dto) {
        batch3Service.deleteAllBatch3CrawlLists();
        batch2Service.Batch3UrlFilterCheckbox(dto);
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
        return new ModelAndView("filter/Batch2HacomResult");
    }

    @PostMapping("/batch2/result/json")
    public final ModelAndView batch2ResultFilterJson(@RequestBody ResponseBatch2ResultDto dto, Model model){
        List<ResponseBatch2ResultDto> getBatch2Result = batch2Service.getListBatch2ResultFilter(dto);
        model.addAttribute("batch2Result", getBatch2Result);
        return new ModelAndView("filter/Batch2HacomResult");
    }

    @GetMapping("/batch3/result")
    public final ModelAndView batch3Result(@RequestParam("type") Integer robotId, Model model){
        List<ResponseBatch3ResultDto> getBatch3Result = batch3Service.getListBatch3Result(robotId);
        model.addAttribute("batch3Result", getBatch3Result);
        return new ModelAndView("filter/Batch3HacomResult");
    }

    @GetMapping("/chart")
    public ModelAndView chart(){
        return new ModelAndView("statistic/chart");
    }

    @PostMapping("/chart/get_chart_data")
    public List<ResponseBatch3ResultDto> getChartData(){
        return batch3Service.getChartData();
    }

    @PostMapping("/excel_export")
    public void execlExport(@RequestBody RequestDataExportDto data){
        batch1Service.exportBatch1Excel(data);
    }

    @PostMapping("/csv_export")
    public void csvExport(@RequestBody RequestDataExportDto data){
        batch1Service.exportBatch1Csv(data);
    }
}
