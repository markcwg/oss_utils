package com.markcwg.oss_component.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author  markcwg
 * @date 2021/4/25
 * version 1.0
 */
@Configuration
public class FileSizeConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大大小(KB,MB)
        factory.setMaxFileSize(DataSize.parse("1024MB"));
        // 上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("1024MB"));
        return factory.createMultipartConfig();
    }
}
