package com.cws.configure;


import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Zjc
 * @since: 2021/5/27
 * @description: knife4j配置
 */
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    private static final String AUTH_HEADER_NAME = "token";

    /*引入Knife4j提供的扩展类*/
    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Bean(value = "createFinanceRestApi")
    public Docket createFinanceRestApi() {
        String groupName = "微信推送-api";
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cws.controller"))
                .paths(PathSelectors.any())
                .build()                //赋予插件体系
                .extensions(openApiExtensionResolver.buildExtensions(groupName))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("微信推送")
                .description("微信推送")
                .termsOfServiceUrl("www.xiaoji.com")
                .version("1.0")
                .build();
    }

    private List<SecurityContext> securityContexts() {

        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext
                .builder()
                .securityReferences(securityReferences())
                .build());
        return securityContexts;
    }

    private List<SecurityReference> securityReferences() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[] {new AuthorizationScope("global", "accessEverything")};
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorize", authorizationScopes));
        return securityReferences;
    }

    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorize", AUTH_HEADER_NAME, In.HEADER.name());
        return Collections.singletonList(apiKey);
    }

}

    /**
     * apiInfo() 增加API相关信息
     * 所有的注解
     * .apis(RequestHandlerSelectors.any())
     * 指定部分注解1.Api.class(@APi),2.ApiOperation.class(@ApiOperation),3.ApiImplicitParam.class(@ApiImplicitParam)
     *.apis(RequestHandlerSelectors.withMethodAnnotation(Api.class))
     * 指定包路径
     * .apis(RequestHandlerSelectors.basePackage("这里填写需要的路径"))
     * .paths() 这个是包路径下的路径,PathSelectors.any()是包下所有路径
     */
/*    @Bean
    public Docket defaultApi() {
        //log.info(""+swaggerEnabled);
        return new Docket(DocumentationType.OAS_30)
                //.useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                //创建
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XXX系统接口说明文档")
                .description("springboot | knife4j")
                // 作者信息
                //.contact("")
                .version("0.0.1")
                .build();
    }*/

/*Api(tags = "xxx模块说明") 作用在模块类上

@ApiOperation("xxx接口说明") 作用在接口方法上

@ApiOperationSupport()：（knife4j增加特性）用于接口方法排序，作者信息描述等。

@ApiImplicitParam()：对单个参数的说明

@ApiParam("xxx参数说明") 作用在参数、方法和字段上，类似@ApiModelProperty

@ApiModel("xxxPOJO说明") 作用在模型类上：如VO、BO

@ApiModelProperty(value = "xxx属性说明",hidden = true) 作用在类方法和属性上，hidden设置为true可以隐藏该属性 */
