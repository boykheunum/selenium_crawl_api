package com.crawl.api.services.impl;

import com.crawl.api.common.Contains;
import com.crawl.api.common.MappingUntils;
import com.crawl.api.common.untils.ModelMapUntils;
import com.crawl.api.dto.*;
import com.crawl.api.model.Batch1CrawlResultModel;
import com.crawl.api.repository.Batch1Repository;
import com.crawl.api.repository.CustomRepository.Batch1CustomRepository;
import com.crawl.api.services.Batch1Service;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.crawl.api.services.Batch2Service;
import com.crawl.api.services.Batch3Service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.el.stream.Stream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    public void exportBatch1Excel(RequestDataExportDto data) {
        String name = "HACOM_AND_NCPC";
        if (data.getType().equals(String.valueOf(Contains.TypeRobot.TYPE_HACOM_BATCH1))) {
            name = "HACOM";
        } else if (data.getType().equals(String.valueOf(Contains.TypeRobot.TYPE_NCPC_BATCH1))) {
            name = "NCPC";
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employee");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

// Create cells
        String[] columns = null;
        if (data.getType().equals(String.valueOf(Contains.TypeRobot.TYPE_HACOM_BATCH1))) {
            columns = new String[]{"robot_id", "category_name", "execution_id", "url", "add_date", "upd_date"};
        } else if (data.getType().equals(String.valueOf(Contains.TypeRobot.TYPE_NCPC_BATCH1))) {
            columns = new String[]{"execution_id", "robot_id", "url", "product_name", "product_key", "cpu", "ram", "storage", "vga", "monitor", "orginal_price", "price"};
        } else if (data.getType().equals(String.valueOf(Contains.TypeRobot.TYPE_ALL))) {
            columns = new String[]{"execution_id", "robot_id", "status", "view", "category_name_1", "category_name_2", "category_name_3", "url", "product_key", "cpu", "ram", "storage", "vga", "monitor", "orginal_price", "price"};
        }
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        int rowNum = 1;
        switch (data.getPathname()) {
            case "batch1":
                List<ResponseBath1ResultDto> result = Batch1Result(data.getType());
                for (ResponseBath1ResultDto item : result) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0)
                            .setCellValue(item.getRobotId());
                    row.createCell(1)
                            .setCellValue(item.getCategoryName());
                    row.createCell(2)
                            .setCellValue(item.getExecutionId());
                    row.createCell(3)
                            .setCellValue(item.getUrl());
                    row.createCell(4)
                            .setCellValue(item.getAddDate().toString());
                    row.createCell(5)
                            .setCellValue(item.getUpdDate().toString());
                }
                break;
            case "batch2":
                List<ResponseBatch2ResultDto> resultBatch2 = batch2Service.getListBatch2Result(Integer.parseInt(data.getType()));
                for (ResponseBatch2ResultDto item : resultBatch2) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0)
                            .setCellValue(item.getExecutionId());
                    row.createCell(1)
                            .setCellValue(item.getRobotId());
                    row.createCell(2)
                            .setCellValue(item.getUrl());
                    row.createCell(3)
                            .setCellValue(item.getProductName());
                    row.createCell(4)
                            .setCellValue(item.getProductKey());
                    row.createCell(5)
                            .setCellValue(item.getCpu());
                    row.createCell(6)
                            .setCellValue(item.getRam());
                    row.createCell(7)
                            .setCellValue(item.getStorage());
                    row.createCell(8)
                            .setCellValue(item.getVga());
                    row.createCell(9)
                            .setCellValue(item.getMonitor());
                    row.createCell(10)
                            .setCellValue(item.getOriginalPrice());
                    row.createCell(11)
                            .setCellValue(item.getPrice());
                }
                break;
            case "batch3":
                List<ResponseBatch3ResultDto> resultBatch3 = batch3Service.getListBatch3Result(Integer.parseInt(data.getType()));
                for (ResponseBatch3ResultDto item : resultBatch3) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0)
                            .setCellValue(item.getExecutionId());
                    row.createCell(1)
                            .setCellValue(item.getRobotId());
                    row.createCell(2)
                            .setCellValue(item.getStatus());
                    row.createCell(3)
                            .setCellValue(item.getView());
                    row.createCell(4)
                            .setCellValue(item.getCategoryName1());
                    row.createCell(5)
                            .setCellValue(item.getCategoryName2());
                    row.createCell(6)
                            .setCellValue(item.getCategoryName3());
                    row.createCell(7)
                            .setCellValue(item.getUrl());
                    row.createCell(8)
                            .setCellValue(item.getProductKey());
                    row.createCell(9)
                            .setCellValue(item.getCpu());
                    row.createCell(10)
                            .setCellValue(item.getRam());
                    row.createCell(11)
                            .setCellValue(item.getStorage());
                    row.createCell(12)
                            .setCellValue(item.getVga());
                    row.createCell(13)
                            .setCellValue(item.getMonitor());
                    row.createCell(14)
                            .setCellValue(item.getOriginalPrice());
                    row.createCell(15)
                            .setCellValue(item.getPrice());
                }
                break;
        }
        FileOutputStream fileOut = null;
        try {
            LocalDateTime now = LocalDateTime.now();
            String time = now.toString().replaceAll("-", "").replaceAll(":", "").replaceAll("\\.", "");
            String workingDir = System.getProperty("user.dir");
            Path pathName = Paths.get(workingDir + "\\export");
            if (!Files.exists(pathName)) {
                new File(String.valueOf(pathName)).mkdirs();
            }
            fileOut = new FileOutputStream(pathName.toString() + "\\" + name + data.getType() + time +".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    @Override
    public List<ResponseBath1ResultDto> getBatch1ResultFilter(RequestFilterUrlBatch1CheckboxDto dto) {
        try {
            List<Batch1CrawlResultModel> result = batch1Custom.getBatch1ResultCheckbox(dto);
            List<ResponseBath1ResultDto> data = ModelMapUntils.mapAll(result, ResponseBath1ResultDto.class);
            return data;
        } catch (Exception e) {
            Logger.getLogger(Batch1ServicesImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public List<Integer> getBatch1ExecutionId(String robotId) {
        return batch1Repository.getDistinctExecutionId(robotId);
    }
}
