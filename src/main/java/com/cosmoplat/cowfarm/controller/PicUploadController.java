package com.cosmoplat.cowfarm.controller;

import com.cosmoplat.cowfarm.common.response.PicUploadResult;
import com.cosmoplat.cowfarm.service.PicUploadFileSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/pic/upload")
@CrossOrigin
@Controller
@Api(value = "图片上传", description = "图片上传接口")
public class PicUploadController {

    @Autowired
    private PicUploadFileSystemService picUploadService;

    @PostMapping
    @ResponseBody
    @ApiOperation("图片上传")
    public PicUploadResult upload(@RequestParam("file") MultipartFile multipartFile) {
        return this.picUploadService.upload(multipartFile);
    }
}
