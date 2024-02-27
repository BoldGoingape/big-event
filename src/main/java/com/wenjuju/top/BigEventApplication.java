package com.wenjuju.top;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import lombok.extern.slf4j.Slf4j;

@Slf4j//日志注解
@MapperScan(basePackages = "com.wenjuju.top.mapper")
@SpringBootApplication
public class BigEventApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BigEventApplication.class, args);
        ConfigurableEnvironment env = context.getEnvironment();
        String envPort = env.getProperty("server.port");
        String envContext = env.getProperty("server.servlet.context-path");
        String path = (envPort == null ? "8080" : envPort) + (envContext == null ? "" : envContext) + "/doc.html";

        log.info(
                "Access URLs:\n----------------------------------------------------------\n\t"
                        + "swagger: \t\thttp://127.0.0.1:{}\n"
                        + "----------------------------------------------------------",
                path);
    }

}
