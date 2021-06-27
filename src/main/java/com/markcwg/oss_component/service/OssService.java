package com.markcwg.oss_component.service;

import com.markcwg.oss_component.common.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author markcwg
 * @date 2021/6/21 11:40 上午
 */
public interface OssService {
    /**
     * 获取签名
     * @return 签名结果
     */
    CommonResult<Map<String, String>> getMark();

    /**
     * 简单上传本地文件
     * @param file 本地上传的文件
     * @param uploadPath 上传之后的前缀路径
     * @return 上传完成后的结果
     */
    CommonResult<Object> simpleUploadByFile(MultipartFile file, String uploadPath);
}
