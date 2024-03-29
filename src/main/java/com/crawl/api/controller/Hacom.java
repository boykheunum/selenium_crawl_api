package com.crawl.api.controller;

import com.crawl.api.common.Contains;
import com.crawl.api.dto.*;
import com.crawl.api.model.Batch1CrawlResultModel;
import com.crawl.api.model.Batch2CrawlUrl;
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

    @PostMapping("filter/sql-batch2/checkbox")
    public final String filterUrlForBatch2Checkbox(@RequestBody RequestFilterUrlBatch1CheckboxDto dto) {
        batch2Service.deleteAllBatch2CrawlUrls();
        batch1Service.Batch2UrlFilterCheckbox(dto);
        return "Hello world";
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
        model.addAttribute("type", robotId);
        model.addAttribute("executionIds", batch1Service.getBatch1ExecutionId(robotId));
        return new ModelAndView("filter/Batch1HacomResult");
    }

    @PostMapping("/batch1/result/json")
    public final List<ResponseBath1ResultDto> batch1ResultFilterJson(@RequestBody RequestFilterUrlBatch1CheckboxDto dto) {
        return batch1Service.getBatch1ResultFilter(dto);
    }

    @GetMapping("/batch2/crawl-url")
    public final ModelAndView batch2Url(Model model) {
        List<Batch2CrawlUrl> getBatch2CrawlUrl = batch2Service.getAllBatch2CrawlUrls();
        model.addAttribute("batch2CrawlUrl", getBatch2CrawlUrl);
        return new ModelAndView("filter/Batch2CrawlUrl");
    }

    @GetMapping("/batch2/result")
    public final ModelAndView batch2Result(@RequestParam("type") Integer robotId, Model model) {
        List<ResponseBatch2ResultDto> getBatch2Result = batch2Service.getListBatch2Result(robotId);
        model.addAttribute("batch2Result", getBatch2Result);
        model.addAttribute("type", robotId);
        return new ModelAndView("filter/Batch2HacomResult");
    }

    @PostMapping("/batch2/result/json")
    public final List<ResponseBatch2ResultDto> batch2ResultFilterJson(@RequestBody ResponseBatch2ResultDto dto) {
        return batch2Service.getListBatch2ResultFilter(dto);
    }

    @GetMapping("/batch3/result")
    public final ModelAndView batch3Result(@RequestParam("type") Integer robotId, Model model) {
        List<ResponseBatch3ResultDto> getBatch3Result = batch3Service.getListBatch3Result(robotId);
        model.addAttribute("batch3Result", getBatch3Result);
        model.addAttribute("type", robotId);
        return new ModelAndView("filter/Batch3HacomResult");
    }

    @PostMapping("batch3/result/json")
    public final List<ResponseBatch3ResultDto> batch3ResultFilterJson(@RequestBody ResponseBatch3ResultDto dto) {
        return batch3Service.getListBatch3ResultFilter(dto);
    }

    @GetMapping("/chart")
    public ModelAndView chart() {
        return new ModelAndView("statistic/chart");
    }

    @PostMapping("/chart/get_chart_data")
    public List<ResponseBatch3ResultDto> getChartData() {
        return batch3Service.getChartData();
    }

    @PostMapping("/excel_export")
    public void execlExport(@RequestBody RequestDataExportDto data) {
        batch1Service.exportBatch1Excel(data);
    }

    @PostMapping("/csv_export")
    public void csvExport(@RequestBody RequestDataExportDto data) {
        batch1Service.exportBatch1Csv(data);
    }

    @GetMapping("data_input_filter")
    public ModelAndView dataInputFilter() {
        return new ModelAndView("filter/FilterDataInput");
    }

    @PostMapping("/batch2/delete_all_url_crawl")
    public void batch2CrawlUrlDeleteAll() {
        batch2Service.batch2CrawlUrlDeleteAll();
    }

    @PostMapping("/batch2/delete_select_url_crawl")
    public void batch2DeleteSelectUrlCrawl(@RequestBody(required = false) String listId) {
        batch2Service.deleteBatch2DataSelect(listId);
    }

    @PostMapping("get_execution_ids")
    public List<Integer> getExecutionIdsByRobotBatch(@RequestBody RequestAllBatchFilterDto dto) {
        try {
            int batchNum = Integer.parseInt(dto.getBatch());
            String robot = dto.getRobot();
            if (robot.equals("HACOM") || robot.equals("NCPC")) {
                if (batchNum == 2) {
                    robot += "0" + batchNum;
                    return batch2Service.getBatch2ExecutionId(robot);
                } else if (batchNum == 3) {
                    robot += "0" + batchNum;
                    return batch3Service.getBatch3ExecutionId(robot);
                } else {
                    return batch1Service.getBatch1ExecutionId(robot);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    @PostMapping("get_filter_batch_results")
    public Object getResultsByRobotBatchExecutionid(@RequestBody RequestAllBatchFilterDto requestDto) {
        try {
            int batchNum = Integer.parseInt(requestDto.getBatch());
            String robot = requestDto.getRobot();
            if (robot.equals("HACOM") || robot.equals("NCPC")) {
                if (batchNum == 2) {
                    robot += "0" + batchNum;
                    ResponseBatch2ResultDto dto = new ResponseBatch2ResultDto();
                    dto.setRobotId(robot);
                    dto.setExecutionId(requestDto.getExecutionId());
                    return batch2Service.getListBatch2ResultFilter(dto);
                } else if (batchNum == 3) {
                    robot += "0" + batchNum;
                    ResponseBatch3ResultDto dto = new ResponseBatch3ResultDto();
                    dto.setRobotId(robot);
                    dto.setExecutionId(requestDto.getExecutionId() + "");
                    return batch3Service.getListBatch3ResultFilter(dto);
                } else {
                    RequestFilterUrlBatch1CheckboxDto dto = new RequestFilterUrlBatch1CheckboxDto();
                    dto.setRobotId(robot);
                    dto.setExecutionId(requestDto.getExecutionId());
                    List<ResponseBath1ResultDto> result = batch1Service.getBatch1ResultFilter(dto);
                    return result;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}
