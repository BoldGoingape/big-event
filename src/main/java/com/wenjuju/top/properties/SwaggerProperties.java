package com.wenjuju.top.properties;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "swagger")

public class SwaggerProperties {
    private String title;//标题
    private String version;//版本

    private String description;//描述
    private List<SecurityScheme> securitySchemes= Lists.newArrayList();//安全信息 token Authorization
}
