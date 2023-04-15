package course.crud.spring.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/download")
public class UploadController {

    @GetMapping("/get")
    public ResponseEntity<Resource> downloadFile() {

        Resource fileResource = new ClassPathResource("file.xlsx");

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
