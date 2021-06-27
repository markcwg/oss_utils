package com.markcwg.oss_component.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.markcwg.oss_component.annotation.LogIntercept;
import com.markcwg.oss_component.common.CommonResult;
import com.markcwg.oss_component.exception.InputStreamFailedException;
import com.markcwg.oss_component.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author markcwg
 * @date 2021/6/21 11:48 上午
 */
@RestController
@Api(value = "API-1 oss文件上传", tags = "API-1 oss文件上传")
@ApiSupport(order = 1)
public class OssController {
    @Resource
    private OssService ossService;

    @GetMapping("mark")
    @ApiOperation(value = "API-1.1 获取oss签名")
    @ApiOperationSupport(order = 1)
    public CommonResult<Map<String, String>> getMark() {
        return ossService.getMark();
    }

    @PostMapping("simpleUploadByFile")
    @ApiOperation(value = "API-1.2 简单上传本地文件")
    @ApiOperationSupport(order = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true,dataType = "__File"),
            @ApiImplicitParam(name = "uploadPath", value = "文件上传后的前缀路径", required = true)
    })
    @LogIntercept(message = "简单上传本地文件")
    public CommonResult<Object> simpleUploadByFile(MultipartFile file, String uploadPath) {
        return ossService.simpleUploadByFile(file, uploadPath);
    }

    @PostMapping("simpleUploadByUrl")
    @ApiOperation(value = "API-1.3 通过网络资源路径上传文件")
    @ApiOperationSupport(order = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "网络资源路径", required = true),
            @ApiImplicitParam(name = "uploadPath", value = "文件上传后的前缀路径", required = true)
    })
    @LogIntercept(message = "通过网络资源路径上传文件")
    public CommonResult<Object> simpleUploadByUrl(String url, String uploadPath) {

        throw new InputStreamFailedException(null);
    }
}
