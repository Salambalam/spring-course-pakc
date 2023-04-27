package course.crud.spring.controllers;

import course.crud.spring.models.DBtoExcel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.sql.*;

@Controller
@RequestMapping("/download")
public class UploadController {

    private DBtoExcel dBtoExcel;

    public UploadController(DBtoExcel dBtoExcel) {
        this.dBtoExcel = dBtoExcel;
    }

    @GetMapping("/get")
    public ResponseEntity<Resource> downloadFile() {

        dBtoExcel.getTable();
        String filePath = System.getProperty("user.dir") + "/file.xlsx";
        Resource fileResource = new FileSystemResource(filePath);

        if (!fileResource.exists()) {
            throw new RuntimeException("File not found");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileResource.getFilename());

        // возвращаем ответ с файлом
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileResource);
    }



}
