package com.jun.springbootimagetool.controller;

import com.jun.springbootimagetool.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class ImageController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("width") int width,
                              @RequestParam("height") int height) throws IOException {
        if (file.isEmpty()) {
            return "No file uploaded";
        }

        // 确保上传目录存在
        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
            System.out.println("Projects/uploads/ directory has been created!");
        }

        // 生成唯一文件名
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File outputFile = Paths.get(uploadDir, fileName).toFile();

        System.out.println("File name: " + file.getOriginalFilename());
        System.out.println("File size: " + file.getSize());
        System.out.println("Upload directory: " + uploadDir);

        // 保存文件
        file.transferTo(outputFile);

        // 调用 ImageService 进行图片缩放
        imageService.resizeImage(outputFile, outputFile, width, height);

        return "/download/" + fileName;  // 返回下载链接
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<UrlResource> downloadFile(@PathVariable String filename) throws IOException {
        Path path = Paths.get(uploadDir, filename);
        UrlResource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}
