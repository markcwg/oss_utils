package com.markcwg.oss_component.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * api 文档配置
 * @author markcwg
 * @date 2021/6/21 7:27 下午
 */
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
public class ApiConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.markcwg.oss_component.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("阿里云 oss 对象存储使用组件接口文档");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("阿里云 oss 对象存储使用组件接口文档")
                .contact(new Contact("markcwg", "https://www.markcwg.xyz", "markcwg7@gmail.com"))
                .version("1.0")
                .build();
    }
}
