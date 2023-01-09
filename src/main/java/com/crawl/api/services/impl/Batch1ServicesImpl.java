package com.crawl.api.services.impl;

import com.crawl.api.common.Contains;
import com.crawl.api.common.MappingUntils;
import com.crawl.api.common.untils.ModelMapUntils;
import com.crawl.api.dto.*;
import com.crawl.api.model.Batch1CrawlResultModel;
import com.crawl.api.repository.Batch1Repository;
import com.crawl.api.repository.CustomRepository.Batch1CustomRepository;
import com.crawl.api.services.Batch1Service;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.crawl.api.services.Batch2Service;
import com.crawl.api.services.Batch3Service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.el.stream.Stream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Batch1ServicesImpl implements Batch1Service {
    @Autowired
    private Batch1CustomRepository batch1Custom;
    @Autowired
    private Batch1Repository batch1Repository;
    @Autowired
    private Batch2Service batch2Service;
    @Autowired
    private Batch3Service batch3Service;

    @Override
    public void Batch2UrlFilter(RequestFilterUrlBatch1Dto dto) {
        batch1Custom.filterUrlForBatch2(dto);
    }

    @Override
    public void Batch2UrlFilterCheckbox(RequestFilterUrlBatch1CheckboxDto dto) {
        batch1Custom.filterUrlForBatch2Checkbox(dto);
    }

    @Override
    public List<ResponseBath1ResultDto> Batch1Result(String robotId) {
        // TODO Auto-generated method stub
        if (robotId.equals(String.valueOf(Contains.TypeRobot.TYPE_HACOM_BATCH1))) {
            robotId = "HACOM";
        } else if (robotId.equals(String.valueOf(Contains.TypeRobot.TYPE_NCPC_BATCH1))) {
            robotId = "NCPC";
        } else if (robotId.equals(String.valueOf(Contains.TypeRobot.TYPE_ALL))) {
            List<Batch1CrawlResultModel> lBatch1Result = batch1Repository.findAll();
            return ModelMapUntils.mapAll(lBatch1Result, ResponseBath1ResultDto.class);
        }
        List<Batch1CrawlResultModel> result = batch1Repository.getBatch1Result(robotId);
        List<ResponseBath1ResultDto> data = ModelMapUntils.mapAll(result, ResponseBath1ResultDto.class);
        return data;
    }

    @Override
    public void exportBatch1Excel() {

    }

    @Override
    public void exportBatch1Csv(RequestDataExportDto data) {
        String name = "HACOM_AND_NCPC";
        if (data.getType().equals(String.valueOf(Contains.TypeRobot.TYPE_HACOM_BATCH1))) {
            name = "HACOM";
        } else if (data.getType().equals(String.valueOf(Contains.TypeRobot.TYPE_NCPC_BATCH1))) {
            name = "NCPC";
        }
        Writer writer = null;
        switch (data.getPathname()) {
            case "batch1":
                List<ResponseBath1ResultDto> result = Batch1Result(data.getType());
                try {
                    Field[] fs = RequestDataExportDto.class.getFields();
                    LocalDateTime now = LocalDateTime.now();
                    String time = now.toString().replaceAll("-", "").replaceAll(":", "").replaceAll("\\.", "");
                    String workingDir = System.getProperty("user.dir");
                    Path pathName = Paths.get(workingDir + "\\export");
                    if (!Files.exists(pathName)) {
                        new File(String.valueOf(pathName)).mkdirs();
                    }
                    writer = Files.newBufferedWriter(Paths.get(pathName.toString() + "\\" + name + data.getType() + time + ".csv"));
                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("robot_id", "category_name", "execution_id", "url", "add_date", "upd_date"));
                    for (ResponseBath1ResultDto item : result) {
                        csvPrinter.printRecord(item.getRobotId(), item.getCategoryName(), item.getExecutionId(), item.getUrl(), item.getAddDate(), item.getUpdDate());
                    }
                    csvPrinter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case "batch2":
                List<ResponseBatch2ResultDto> resultBatch2 = batch2Service.getListBatch2Result(Integer.parseInt(data.getType()));
                try {
                    Field[] fs = RequestDataExportDto.class.getFields();
                    LocalDateTime now = LocalDateTime.now();
                    String time = now.toString().replaceAll("-", "").replaceAll(":", "").replaceAll("\\.", "");
                    String workingDir = System.getProperty("user.dir");
                    Path pathName = Paths.get(workingDir + "\\export");
                    if (!Files.exists(pathName)) {
                        new File(String.valueOf(pathName)).mkdirs();
                    }
                    writer = Files.newBufferedWriter(Paths.get(pathName.toString() + "\\" + name + data.getType() + time + ".csv"));
                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("execution_id", "robot_id", "url", "product_name", "product_key", "cpu", "ram", "storage", "vga", "monitor", "orginal_price", "price"));
                    for (ResponseBatch2ResultDto item : resultBatch2) {
                        csvPrinter.printRecord(item.getExecutionId(), item.getRobotId(), item.getUrl(), item.getProductName(), item.getProductKey(), item.getCpu(), item.getRam(), item.getStorage(), item.getVga(), item.getMonitor(), item.getOriginalPrice(), item.getPrice());
                    }
                    csvPrinter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "batch3":
                List<ResponseBatch3ResultDto> resultBatch3 = batch3Service.getListBatch3Result(Integer.parseInt(data.getType()));
                try {
                    Field[] fs = RequestDataExportDto.class.getFields();
                    LocalDateTime now = LocalDateTime.now();
                    String time = now.toString().replaceAll("-", "").replaceAll(":", "").replaceAll("\\.", "");
                    String workingDir = System.getProperty("user.dir");
                    Path pathName = Paths.get(workingDir + "\\export");
                    if (!Files.exists(pathName)) {
                        new File(String.valueOf(pathName)).mkdirs();
                    }
                    writer = Files.newBufferedWriter(Paths.get(pathName.toString() + "\\" + name + data.getType() + time + ".csv"));
                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("execution_id", "robot_id", "status", "view", "category_name_1", "category_name_2", "category_name_3", "url", "product_key", "cpu", "ram", "storage", "vga", "monitor", "orginal_price", "price"));
                    for (ResponseBatch3ResultDto item : resultBatch3) {
                        csvPrinter.printRecord(item.getExecutionId(), item.getRobotId(), item.getStatus(), item.getView(), item.getCategoryName1(), item.getCategoryName2(), item.getCategoryName3(), item.getUrl(), item.getProductKey(), item.getCpu(), item.getRam(), item.getStorage(), item.getVga(), item.getMonitor(), item.getOriginalPrice(), item.getPrice());
                    }
                    csvPrinter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
