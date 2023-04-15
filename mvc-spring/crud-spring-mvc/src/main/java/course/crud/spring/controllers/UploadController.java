package course.crud.spring.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

@Controller
@RequestMapping("/download")
public class UploadController {

    @GetMapping("/get")
    public ResponseEntity<Resource> downloadFile() throws SQLException, IOException {

        Resource fileResource = new ClassPathResource("file.xlsx");

        if (!fileResource.exists()) {
            throw new RuntimeException("File not found");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileResource.getFilename());

        getBd();
        // возвращаем ответ с файлом
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileResource);
    }

    private static void getBd() throws SQLException, IOException {
        String url = "jdbc:postgresql://localhost:5432/first_db";
        String username = "postgres";
        String password = "123";
        Connection connection = DriverManager.getConnection(url, username, password);

        // Получение данных из таблицы
        String sql = "SELECT * FROM person";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // Создание нового документа Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("My Sheet");


        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(1).setCellValue("name");
        headerRow.createCell(2).setCellValue("age");
        headerRow.createCell(3).setCellValue("email");


        String filename = "mydata.xlsx";
        FileOutputStream outputStream = new FileOutputStream(filename);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
