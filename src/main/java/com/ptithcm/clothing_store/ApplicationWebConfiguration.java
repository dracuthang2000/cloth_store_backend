package com.ptithcm.clothing_store;

import com.ptithcm.clothing_store.model.exception.ApplicationHandleException;
import com.ptithcm.clothing_store.security.SecurityConfigurer;
import com.ptithcm.clothing_store.service.CustomerService;
import com.ptithcm.clothing_store.mapper.ApplicationProductMapper;
import com.ptithcm.clothing_store.util.JwtUtil;
import com.ptithcm.clothing_store.web.AbstractApplicationController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;
import java.util.*;

/**
 * @author gtn
 */
@SpringBootApplication(scanBasePackages = "com.ptithcm.clothing_store")
@ComponentScan(basePackageClasses = {
        AbstractApplicationController.class,
        ApplicationProductMapper.class,
        SecurityConfigurer.class,
        ApplicationHandleException.class,
        JwtUtil.class,
        CustomerService.class,
})
@EnableJpaRepositories(basePackages = {"com.ptithcm.clothing_store.repository"}, repositoryImplementationPostfix = "CustomImpl")
@PropertySource({"classpath:/application.properties", "classpath:/messages.properties"})
@EnableSwagger2
public class ApplicationWebConfiguration extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationWebConfiguration.class);
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Clothing store API",
                "Some custom description of API.",
                "1.0",
                "Terms of service",
                new Contact("clothing store", "www.clothingstore-ptithcm.link", "nguyenquocthang16022000@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("GET", "POST", "PUT", "DELETE","OPTIONS")
                        .allowedHeaders("X-Auth-Token,Authorization")
                        .allowedHeaders("*")
                        .allowedOrigins("*");
            }
        };
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(1000000);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }

}
