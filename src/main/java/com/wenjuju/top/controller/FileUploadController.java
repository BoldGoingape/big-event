package com.wenjuju.top.controller;

import com.wenjuju.top.bean.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //存储本地磁盘
        file.transferTo(new File("U:\\eCloudINsight\\后端\\big-event\\image\\"+filename));
        return Result.success("url访问地址。。。");
    }
}
