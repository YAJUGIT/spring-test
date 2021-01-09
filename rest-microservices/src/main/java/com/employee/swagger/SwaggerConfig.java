package com.employee.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.employee"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {

        return new ApiInfoBuilder().title("Employee API")
                .description("User's Custom Job List")
                .contact(new Contact("Yajuvendra Singh", "www.yaju.com", "Yujuvendra_Singh@abc.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0-SNAPSHOT")
                .build();
    }

    private ApiInfo getApiInformation(){
        return new ApiInfo("User's Custom Preference Job API",
                "This API is for creating the custom Job list for the User ",
                "1.0",
                "API Terms of Service URL",
                new Contact("Comcast", "www.comcast.com", "Yujuvendra_Singh@comcast.com"),
                "API License",
                "API License URL",
                Collections.emptyList()
        );
    }

}