package com.markcwg.oss_component.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.markcwg.oss_component.common.CommonResult;
import com.markcwg.oss_component.common.Constants;
import com.markcwg.oss_component.entity.OssEntity;
import com.markcwg.oss_component.enums.ResultCode;
import com.markcwg.oss_component.exception.InputStreamFailedException;
import com.markcwg.oss_component.service.OssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author markcwg
 * @date 2021/6/21 11:41 上午
 */
@Service
public class OssServiceImpl implements OssService {
    @Resource
    private OssEntity ossEntity;
    @Resource
    private OSSClient ossClient;
    @Override
    public CommonResult<Map<String, String>> getMark() {
        // host的格式为 bucket.endpoint
        String host = "https://" + ossEntity.getBucket() + "." + ossEntity.getEndpoint();
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//        String callbackUrl = "http://88.88.88.88:8888";
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // 用户上传文件时指定的前缀。
        String dir = format + "/";
        Map<String, String> respMap = null;
        try {
            long expireTime = 3000;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);
            respMap = new LinkedHashMap<String, String>(8);
            respMap.put("accessid", ossEntity.getAccessId());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return CommonResult.ok(respMap, ResultCode.MARK_SUCCESS);
    }

    @Override
    public CommonResult<Object> simpleUploadByFile(MultipartFile file, String uploadPath) {
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String key = generatePrefixPath(uploadPath) + IdUtil.simpleUUID() + suffix;
        try {
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(ossEntity.getBucket(), key, inputStream);
        } catch (IOException e) {
            throw new InputStreamFailedException(null);
        }
        //设置访问链接过期时间为 20 年
        Date expiration = new Date(System.currentTimeMillis() + Constants.TWENTY_YEARS);
        String url = ossClient.generatePresignedUrl(ossEntity.getBucket(), key, expiration).toString();
        return CommonResult.ok(url, ResultCode.SUCCESS);
    }

    private String generatePrefixPath(String uploadPath) {
        if (StrUtil.isBlank(uploadPath)) {
            return Constants.OSS_PREFIX_PATH;
        }
        String last = uploadPath.substring(uploadPath.length()-1);
        if (Constants.SLASH.equals(last)) {
            return uploadPath;
        }
        if (Constants.BACKSLASH.equals(last)) {
            return uploadPath.replace(Constants.BACKSLASH,Constants.SLASH);
        }
        return uploadPath + Constants.SLASH;
    }
}
