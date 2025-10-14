package com.example.controller;

import cn.hutool.core.io.FileUtil;
import com.example.common.Result;
import com.example.exception.CustomerException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

// 文件上传下载相关的接口
@RestController
@RequestMapping("/file")
public class FileController {

    // 文件上传
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        //找到文件位置
        String filePath = System.getProperty("user.dir") + "/files/";
        if (FileUtil.isDirectory(filePath)) {
            FileUtil.mkdir(filePath);
        }
        byte[] bytes = file.getBytes();
        String filename = System.currentTimeMillis()+ "-" + file.getOriginalFilename(); // 文件的原始名称
        // 写出文件
        FileUtil.writeBytes(bytes,filePath + filename);
        String url = "http://localhost:8080/file/download/" + filename;
        return Result.success(url);
    }


    // 文件下载
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName , HttpServletResponse response) throws Exception{
        // 找到文件的位置
        String filePath = System.getProperty("user.dir") + "/files/"; // 获取当前项目的路径
        fileName = filePath + fileName; // 拼接文件名
        if (!FileUtil.exist(fileName)) {
            throw new CustomerException("文件不存在");
        }
        // 读取文件的字节流
        byte[] bytes = FileUtil.readBytes(fileName);
        ServletOutputStream os = response.getOutputStream();
        // 输出流对象把文件写到客户端
        os.write(bytes);
        os.flush();
        os.close();
    }
}
